package cn.com.yusys.oca.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.transaction.Transactional;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cn.com.yusys.oca.dao.GroupAuthorityRelRepository;
import cn.com.yusys.oca.dao.GroupRepository;
import cn.com.yusys.oca.po.Authority;
import cn.com.yusys.oca.po.Group;
import cn.com.yusys.oca.po.GroupAuthorityRel;
import cn.com.yusys.oca.service.AuthorityService;
import cn.com.yusys.oca.service.GroupService;


@Service
@Transactional
public class GroupServiceImpl implements GroupService{
	
	@Autowired
    private GroupRepository groupRepository;
	
	@Autowired
	private AuthorityService authorityService;
	
	@Autowired
    private GroupAuthorityRelRepository groupAuthorityRelRepository;

	@Override
	public List<Group> getAllGroupList() {
		// TODO Auto-generated method stub
		return groupRepository.findAll();
	}
	
    @Override
	public Group findGroupById(long id) {
		// TODO Auto-generated method stub
		return groupRepository.findById(id);
	}
    
    @Override
	public void save(Group Group) {
    	groupRepository.save(Group);
	}
    
    @Override
	public void edit(Group group) {
    	groupRepository.save(group);
	}
    
	@Override
	public void deleteGroup(long id) {
		groupRepository.deleteById(id);
	}
	
	@Override
	public Page<Group> findAllGroupPage(Pageable pageable) {
		return groupRepository.findAll(pageable);
	}

	@Override
	public void dealGroupAuthorityGrant(Map<Long, Long> groupAuthorityRelMap) {
		// TODO Auto-generated method stub
		Set<Entry<Long, Long>> entrySet = groupAuthorityRelMap.entrySet();
		
		for (Entry<Long, Long> entry : entrySet) {
			
			GroupAuthorityRel groupAuthorityRel = new GroupAuthorityRel();

			groupAuthorityRel.setGroupId(entry.getKey());
			groupAuthorityRel.setAuthorityId(entry.getValue());
			
			Example<GroupAuthorityRel> example = Example.of(groupAuthorityRel);
			if (!groupAuthorityRelRepository.exists(example)) {
				groupAuthorityRelRepository.save(groupAuthorityRel);
			}

		}
		
	}

	@Override
	public List<Authority> findGroupAuthorities(Long groupId) {
		// TODO Auto-generated method stub
		List<GroupAuthorityRel> groupAuthorityRels = groupAuthorityRelRepository.findByGroupId(groupId);
		List<Authority> groupAuthorities = new ArrayList<Authority>();
		for (int i = 0; i < groupAuthorityRels.size(); i++) {
			groupAuthorities.add(authorityService.findAuthorityById(groupAuthorityRels.get(i).getAuthorityId()));
		}
		return groupAuthorities;
	}
	
	

}
