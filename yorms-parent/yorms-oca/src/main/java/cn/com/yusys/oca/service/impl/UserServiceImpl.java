package cn.com.yusys.oca.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.com.yusys.file.util.Constants;
import cn.com.yusys.oca.dao.GroupAuthorityRelRepository;
import cn.com.yusys.oca.dao.UserAuthorityRelRepository;
import cn.com.yusys.oca.dao.UserGroupRelRepository;
import cn.com.yusys.oca.dao.UserRepository;
import cn.com.yusys.oca.po.Authority;
import cn.com.yusys.oca.po.Group;
import cn.com.yusys.oca.po.GroupAuthorityRel;
import cn.com.yusys.oca.po.User;
import cn.com.yusys.oca.po.UserAuthorityRel;
import cn.com.yusys.oca.po.UserGroupRel;
import cn.com.yusys.oca.service.AuthorityService;
import cn.com.yusys.oca.service.GroupService;
import cn.com.yusys.oca.service.UserService;
import cn.com.yusys.oca.util.PageUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.Map.Entry;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private GroupService groupService;
	
	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private UserAuthorityRelRepository userAuthorityRelRepository;

	@Autowired
	private UserGroupRelRepository userGroupRelRepository;
	
	@Autowired
	private GroupAuthorityRelRepository groupAuthorityRelRepository;

	@Override
	@Transactional
	public List<User> getUserList() {
		return userRepository.findAll();
	}

	@Override
	@Transactional
	public User findUserById(long id) {
		return userRepository.findById(id);
	}

	@Override
	@Transactional
	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		User user = userRepository.findByUserName(username);

		return userRepository.findByUserName(username);
	}

	@Override
	@Transactional
	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	@Transactional
	public void edit(User user) {
		userRepository.save(user);
	}

	@Override
	@Transactional
	public void deleteUser(long id) {
		userRepository.deleteById(id);
	}

	@Override
	@Transactional
	public Page<User> findAllUserPage(Pageable pageable) {
		return userRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public Page<User> findUserListByFilterCriteria(Long orgId,Long dptId,Long groupId, Long roleId,Boolean locked,Pageable pageable) {
		// TODO Auto-generated method stub
		log.info("多条件查询用户  start ...");
		User userExample = new User();
		userExample.setOrgId(orgId);
		userExample.setDptId(dptId);
		ExampleMatcher exampleMatcher;
		
		if (locked == null) {
			exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id").withIgnorePaths("credentialExpired").withIgnorePaths("locked");
		}else {
			userExample.setLocked(locked);
			exampleMatcher = ExampleMatcher.matching().withIgnorePaths("id").withIgnorePaths("credentialExpired");
		}
		
		Example<User> example = Example.of(userExample,exampleMatcher);
		
		List<User> users = userRepository.findAll(example);
		List<User> filteredUsers = new ArrayList<User>();
		
		for (User user : users) {
			
			boolean groupFlag = false;
			boolean roleFlag = false;
			
			if (groupId == null) {
				groupFlag = true;
			}
			if (roleId == null) {
				roleFlag = true;
			}
			
			List<UserGroupRel> userGroupRels = userGroupRelRepository.findByUserId(user.getId());
			for (UserGroupRel userGroupRel : userGroupRels) {
				if (userGroupRel.getGroupId() == groupId) {
					groupFlag = true;
				}
			}
			
			
			List<UserAuthorityRel> userAuthorityRels = userAuthorityRelRepository.findByUserId(user.getId());
			for (UserAuthorityRel userAuthorityRel : userAuthorityRels) {
				if (userAuthorityRel.getAuthorityId() == roleId) {
					roleFlag = true;
				}
			}
			
		
			
			if (groupFlag&&roleFlag) {
				filteredUsers.add(user);
			}
			

		}
		
		Collections.reverse(filteredUsers);
		
		
		log.info("查询结果条数：{}",filteredUsers.size());
		
		//手动分页
		PageImpl<User> usersPage = PageUtil.convertToPages(filteredUsers, pageable);
	    
		return usersPage;
	}

	@Override
	public boolean dealUserGroupManage(Long userId,List<Long> groupIds) {
		
		// 取消所有原有用户组,仅保留默认用户组
		if (userId != null && groupIds.size() > 0) {
			UserGroupRel userGroupRel = new UserGroupRel();
			userGroupRel.setUserId(userId);
			userGroupRelRepository.deleteByUserId(userId);

			UserGroupRel userGroupRelDefault = new UserGroupRel();
			userGroupRelDefault.setUserId(userId);
			userGroupRelDefault.setGroupId(Constants.DEFAULT_GROUP_ID);
			userGroupRelRepository.save(userGroupRelDefault);

		} else {
			log.error("dealUserGroupManage parameter error");
			return false;
		}
		
		// 根据参数重新分配用户组
		for (Long groupId : groupIds) {
			UserGroupRel userGroupRel = new UserGroupRel();

			userGroupRel.setUserId(userId);
			userGroupRel.setGroupId(groupId);

			Example<UserGroupRel> example = Example.of(userGroupRel);
			if (!userGroupRelRepository.exists(example)) {
				userGroupRelRepository.save(userGroupRel);
			}
		}

		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean dealUserAuthorityManage(Long userId,List<Long> authorityIds) {
		// TODO Auto-generated method stub
		
		//取消所有原有角色,仅保留默认角色
		if (userId != null && authorityIds.size() > 0) {
			UserAuthorityRel userAuthorityRel = new UserAuthorityRel();
			userAuthorityRel.setUserId(userId);
			userAuthorityRelRepository.deleteByUserId(userId);
			
			UserAuthorityRel userAuthorityRelDefault = new UserAuthorityRel();
			userAuthorityRelDefault.setUserId(userId);
			userAuthorityRelDefault.setAuthorityId(Constants.DEFAULT_AUTHORITY_ID);
			userAuthorityRelRepository.save(userAuthorityRelDefault);
			
		}else {
			log.error("dealUserAuthorityManage parameter error");
			return true;
		}
		
		
		//根据参数重新分配角色
		for (Long authorityId : authorityIds) {
			UserAuthorityRel userAuthorityRel = new UserAuthorityRel();

			userAuthorityRel.setUserId(userId);
			userAuthorityRel.setAuthorityId(authorityId);
			
			Example<UserAuthorityRel> example = Example.of(userAuthorityRel);
			if (!userAuthorityRelRepository.exists(example)) {
				userAuthorityRelRepository.save(userAuthorityRel);
			}
		}
		
		return true;
	}

	@Override
	public List<Group> findUserGroups(Long userId) {
		// TODO Auto-generated method stub
		List<UserGroupRel> userGroupRels = userGroupRelRepository.findByUserId(userId);
		List<Group> groups = new ArrayList<Group>();
		for (int i = 0; i < userGroupRels.size(); i++) {
			groups.add(groupService.findGroupById(userGroupRels.get(i).getGroupId()));
		}
		return groups;
	}

	@Override
	public List<Authority> findUserAuthoritys(Long userId) {
		// TODO Auto-generated method stub
		//先获取用户的属组 然后取得属组所赋予的权限
		List<UserGroupRel> userGroupRels = userGroupRelRepository.findByUserId(userId);
		List<Authority> groupAuthorities = new ArrayList<Authority>();
		
		for (int i = 0; i < userGroupRels.size(); i++) {
			groupAuthorities.addAll(groupService.findGroupAuthorities(userGroupRels.get(i).getGroupId()));
		}
		
		//用户自身的权限
		List<UserAuthorityRel> userAuthorityRels = userAuthorityRelRepository.findByUserId(userId);
		List<Authority> userAuthorities = new ArrayList<Authority>();
		
		for (int i = 0; i < userAuthorityRels.size(); i++) {
			userAuthorities.add(authorityService.findAuthorityById(userAuthorityRels.get(i).getAuthorityId()));
		}
		
		
		//权限List合并去重
		List<Authority> resultAuthorities = groupAuthorities;
		resultAuthorities.addAll(userAuthorities);
		resultAuthorities = resultAuthorities.stream().distinct().collect(Collectors.toList());
		return resultAuthorities;
	}

	@Override
	public Page<User> findUsersByGroup(Long groupId,Pageable pageable) {
		// TODO Auto-generated method stub
		List<UserGroupRel> userGroupRels = userGroupRelRepository.findByGroupId(groupId);
		List<User> users = new ArrayList<User>();
		for (int i = 0; i < userGroupRels.size(); i++) {
			users.add(this.findUserById(userGroupRels.get(i).getUserId()));
		}
		
		return PageUtil.convertToPages(users, pageable);
	}

	@Override
	public Page<User> findUsersByAuthority(Long authorityId,Pageable pageable) {
		// TODO Auto-generated method stub
		//先获取拥有该角色的用户组 获取这些用户组下的所有用户
		List<GroupAuthorityRel> groupAuthorityRels = groupAuthorityRelRepository.findByAuthorityId(authorityId);
		
		List<User> groupUsers = new ArrayList<User>();
		for (int i = 0; i < groupAuthorityRels.size(); i++) {
			groupUsers.addAll(this.findUsersByGroup(groupAuthorityRels.get(i).getGroupId()));
		}
		
		//再获取直接拥有该角色的用户
		List<User> authorityUsers = new ArrayList<User>();
		List<UserAuthorityRel> userAuthorityRels = userAuthorityRelRepository.findByAuthorityId(authorityId);
		for (int i = 0; i < userAuthorityRels.size(); i++) {
			authorityUsers.add(this.findUserById(userAuthorityRels.get(i).getUserId()));
		}
		List<User> resultUsers = new ArrayList<User>();
		resultUsers.addAll(groupUsers);
		resultUsers.addAll(groupUsers);
		
		resultUsers = resultUsers.stream().distinct().collect(Collectors.toList());
		
		
		return PageUtil.convertToPages(resultUsers, pageable);
	}

	@Override
	public List<User> findUsersByGroup(Long groupId) {
		// TODO Auto-generated method stub
		List<UserGroupRel> userGroupRels = userGroupRelRepository.findByGroupId(groupId);
		List<User> users = new ArrayList<User>();
		for (int i = 0; i < userGroupRels.size(); i++) {
			users.add(this.findUserById(userGroupRels.get(i).getUserId()));
		}
		return users;
	}

	@Override
	public List<User> findUsersByAuthority(Long authorityId) {
		// TODO Auto-generated method stub
		// 先获取拥有该角色的用户组 获取这些用户组下的所有用户
		List<GroupAuthorityRel> groupAuthorityRels = groupAuthorityRelRepository.findByAuthorityId(authorityId);

		List<User> groupUsers = new ArrayList<User>();
		for (int i = 0; i < groupAuthorityRels.size(); i++) {
			groupUsers.addAll(this.findUsersByGroup(groupAuthorityRels.get(i).getGroupId()));
		}

		// 再获取直接拥有该角色的用户
		List<User> authorityUsers = new ArrayList<User>();
		List<UserAuthorityRel> userAuthorityRels = userAuthorityRelRepository.findByAuthorityId(authorityId);
		for (int i = 0; i < userAuthorityRels.size(); i++) {
			authorityUsers.add(this.findUserById(userAuthorityRels.get(i).getUserId()));
		}
		List<User> resultUsers = new ArrayList<User>();
		resultUsers.addAll(groupUsers);
		resultUsers.addAll(groupUsers);

		resultUsers = resultUsers.stream().distinct().collect(Collectors.toList());
		
		return resultUsers;
	}

}
