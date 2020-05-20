package cn.com.yusys.oca.controller.rest;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import cn.com.yusys.file.util.OutputData;
import cn.com.yusys.oca.dto.UserAuthoityRelRequest;
import cn.com.yusys.oca.dto.UserCheck;
import cn.com.yusys.oca.dto.UserGroupRelRequest;
import cn.com.yusys.oca.dto.UserLoginRequest;
import cn.com.yusys.oca.dto.UserRequest;
import cn.com.yusys.oca.dto.UserResponse;
import cn.com.yusys.oca.po.Authority;
import cn.com.yusys.oca.po.Group;
import cn.com.yusys.oca.po.User;
import cn.com.yusys.oca.service.OrganizationService;
import cn.com.yusys.oca.service.UserService;
import cn.com.yusys.oca.util.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * @项目名称 yorms-oca 
 * @类名称 UserController 
 * @类描述 
 * @功能描述:
 * @创建人：zhangal@yusys.com.cn
 * @创建时间：2020/3/10
 * @修改备注：
 * @修改日期 修改人员 修改原因 
 * @version 1.0.0
 * @Copyright (c) 
 */

@RestController
@RequestMapping("/User")
@Api(value = "/User")
public class UserController {
	
	@Resource
	UserService userService;
	
	@Resource
	OrganizationService organizationService;
	
	
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	
	
    @RequestMapping(value="/testToken",method = RequestMethod.POST)
    @SuppressWarnings("rawtypes")
    @ApiOperation(value = "/testToken",notes = "testToken")
    //验证token要传入相关参数Header，正常要将Header封装为json对象
    public String coupon(@RequestHeader("token") String token,@RequestHeader("userId") String userID) throws Exception{
        System.out.println("token----->"+token+",user level----->"+userID);
        return "-----get coupon!-----";
    }
	
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "/login",notes = "Used for gateway user check")
	public UserCheck login(@RequestBody UserLoginRequest loginRequest) {
		
		UserCheck userCheck = new UserCheck();
		User user = userService.findByUserName(loginRequest.getUserName());
		if (user != null) {
			if (user.getPassword().equals(loginRequest.getPassword())) {
				userCheck.setUserId(user.getId());
				userCheck.setCheckResult("验证成功");
			}else {
				userCheck.setCheckResult("密码错误");
			}
			
		}else {
			userCheck.setCheckResult("用户名不存在");
		}
		
		return userCheck;
		
	}
	
	
	
	
	
	
	@RequestMapping(value = "/userAdd", method = RequestMethod.POST)
	@SuppressWarnings("rawtypes")
	@ApiOperation(value = "/userAdd",notes = "Add user")
	public OutputData userAdd(@RequestBody UserRequest request) {
		
		OutputData out = new OutputData().returnSuccess();
		User user = new User();
		
		try {
			try {
				BeanUtils.copyProperties(user, request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("Request convert Exception {}",e);
	        	return out.returnFail(e.getMessage());
			}
			userService.save(user);
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Add user Exception{}",e);
			out.returnFail(e.getMessage());
		}
		return out;
		
	}
	
	
	
	@SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/queryAll",method = RequestMethod.GET)
	@ApiOperation(value = "/queryAll",notes = "Query all users")
	public OutputData queryAll(){
		OutputData out = new OutputData().returnSuccess();
		
		List<User> userList = new ArrayList<User>();
		try{
			userList = userService.getUserList();
			
			out.setData(userList);
			
		}catch(Exception e){
			log.info("Query all user Exception {}",e);
			out.returnFail(e.getMessage());
		}
		return out;
	}
	
	
	@SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/queryUserById",method = RequestMethod.GET)
	@ApiOperation(value = "/queryUserById",notes = "Query User By Id")
	public OutputData queryUserById(Long userId){
		OutputData out = new OutputData().returnSuccess();
		
		
		try{
			User user = userService.findUserById(userId);
			
			out.setData(user);
			
		}catch(Exception e){
			log.info("Query user by id Exception {}",e);
			out.returnFail(e.getMessage());
		}
		return out;
	}
	
	
	
	
	@SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/queryUserGroups",method = RequestMethod.GET)
	@ApiOperation(value = "/queryUserGroups",notes = "Query user groups")
	public OutputData queryUserGroups(Long userId){
		OutputData out = new OutputData().returnSuccess();
		
		List<Group> groupList = new ArrayList<Group>();
		try{
			groupList = userService.findUserGroups(userId);
			
			out.setData(groupList);
			
		}catch(Exception e){
			log.info("Query user groups Exception {}",e);
			out.returnFail(e.getMessage());
		}
		return out;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/feignQueryUserGroups",method = RequestMethod.GET)
	@ApiOperation(value = "/feignQueryUserGroups",notes = "Query user groups")
	public List<Group> feignQueryUserGroups(Long userId){
		
		List<Group> groupList = new ArrayList<Group>();
		try{
			groupList = userService.findUserGroups(userId);
		}catch(Exception e){
			log.info("Query user groups Exception {}",e);
			return groupList;
		}
		return groupList;
	}
	
	
	@SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/queryUserAuthoritys",method = RequestMethod.GET)
	@ApiOperation(value = "/queryUserAuthoritys",notes = "Feign query user authoritys")
	public OutputData queryUserAuthoritys(Long userId){
		OutputData out = new OutputData().returnSuccess();
		
		List<Authority> authorities = new ArrayList<Authority>();
		try{
			authorities = userService.findUserAuthoritys(userId);
			out.setData(authorities);
			
		}catch(Exception e){
			log.info("Query user authoritys Exception {}",e);
			out.returnFail(e.getMessage());
		}
		return out;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/feignQueryUserAuthoritys",method = RequestMethod.GET)
	@ApiOperation(value = "/feignQueryUserAuthoritys",notes = "Feign query user authoritys")
	public List<Authority> feignQueryUserAuthoritys(Long userId){
		
		List<Authority> authorities = new ArrayList<Authority>();
		try{
			authorities = userService.findUserAuthoritys(userId);
			
		}catch(Exception e){
			log.info("Query user authoritys Exception {}",e);
			return authorities;
		}
		return authorities;
	}
	
	
	
	@SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/queryUsersByGroupId",method = RequestMethod.GET)
	@ApiOperation(value = "/queryUsersByGroupId",notes = "Query users by groupId")
	public OutputData queryUsersByGroupId(Long groupId){
		OutputData out = new OutputData().returnSuccess();
		
		List<User> userList = new ArrayList<User>();
		try{
			userList = userService.findUsersByGroup(groupId);
			
			out.setData(userList);
		}catch(Exception e){
			log.info("Query users by groupId Exception {}",e);
			out.returnFail(e.getMessage());
		}
		return out;
	}
	
	
	
	@SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/queryUsersByAuthorityId",method = RequestMethod.GET)
	@ApiOperation(value = "/queryUsersByAuthorityId",notes = "Query users by authorityId")
	public OutputData queryUsersByAuthorityId(Long authorityId){
		OutputData out = new OutputData().returnSuccess();
		
		List<User> userList = new ArrayList<User>();
		try{
			userList = userService.findUsersByAuthority(authorityId);
			
			out.setData(userList);
		}catch(Exception e){
			log.info("Query users by authorityId Exception {}",e);
			out.returnFail(e.getMessage());
		}
		return out;
	}
	
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	@RequestMapping(value = "/userManage", method = RequestMethod.GET)
	@ApiOperation(value = "/userManage",notes = "Query users by page option")
	public OutputData userManage(Integer pageSize,Integer page) {
		
		OutputData out = new OutputData().returnSuccess();
		
		if(pageSize == null || pageSize<1){
			pageSize = 10;
		}
		if(page == null || page<1){
			page = 0;
		}
		
		@SuppressWarnings("deprecation")
		Pageable pageable = new PageRequest(page,pageSize);
		
		List<User> users = userService.getUserList();
		
		List<UserResponse> userResponses = new ArrayList<UserResponse>();
		//Page<User> pageUsers = userService.findAllUserPage(pageable);
		
		
		for (int i = 0; i < users.size(); i++) {
			
			User user = users.get(i);
			
			Long orgId = user.getOrgId();
			Long dptId = user.getDptId();
			Long userId = user.getId();
			
			UserResponse userResponse = new UserResponse();
			userResponse.setId(user.getId());
			userResponse.setUserName(user.getUserName());
			userResponse.setPassword(user.getPassword());
			userResponse.setEmail(user.getEmail());
			userResponse.setUserCode(user.getUserCode());
			userResponse.setUserDesc(user.getUserDesc());
			userResponse.setLocked(user.isLocked());
			userResponse.setExpiredDate(user.getExpiredDate());
			userResponse.setCredentialExpired(user.isCredentialExpired());
			userResponse.setCredentialExpiredDate(user.getCredentialExpiredDate());
			userResponse.setPhoneNumber(user.getPhoneNumber());
			
			
			List<Group> groups = userService.findUserGroups(userId);
			String groupString = "";
			for (int j = 0; j < groups.size(); j++) {
				if (groupString.equals("")) {
					groupString = groups.get(j).getGroupName();
				}else {
					groupString = groupString + "\n" + groups.get(j).getGroupName();
				}
				
			}
			userResponse.setGroups(groupString);
			
			
			List<Authority> authorities = userService.findUserAuthoritys(userId);
			String authorityString = "";
			for (int j = 0; j < authorities.size(); j++) {
				if (authorityString.equals("")) {
					authorityString = authorities.get(j).getAuthorityName();
				}else {
					authorityString = authorityString + "\n" + authorities.get(j).getAuthorityName();
				}
			}
			userResponse.setAuthorities(authorityString);
			
			
			String orgName = organizationService.findById(orgId).getName();
			userResponse.setOrgName(orgName);
			
			String dptName = organizationService.findById(dptId).getName();
			userResponse.setDptName(dptName);
			
			userResponses.add(userResponse);
		}
		
		Page<UserResponse> pageUsers = PageUtil.convertToPages(userResponses, pageable);
		
		out.setData(pageUsers);
		
		return out;
	}
	
	
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	@RequestMapping(value = "/userUpdate", method = RequestMethod.POST)
	@ApiOperation(value = "/userUpdate",notes = "Update user")
	public OutputData userUpdate(@RequestBody UserRequest request,Integer pageSize,Integer page) {
		
		OutputData out = new OutputData().returnSuccess();
		
		User user = new User();
		
		try {
			try {
				BeanUtils.copyProperties(user, request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("Request convert Exception {}",e);
	        	return out.returnFail(e.getMessage());
			}
			userService.save(user);
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Update user Exception{}",e);
			out.returnFail(e.getMessage());
		}
		
		if(pageSize == null || pageSize<1){
			pageSize = 10;
		}
		if(page == null || page<1){
			page = 0;
		}
		@SuppressWarnings("deprecation")
		Pageable pageable = new PageRequest(page,pageSize);
		
		Page<User> pageUser = userService.findAllUserPage(pageable);
		
		out.setData(pageUser);
		
		return out;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	@RequestMapping(value = "/userDelete", method = RequestMethod.POST)
	@ApiOperation(value = "/userDelete",notes = "Delete user")
	public OutputData userDelete(@RequestBody UserRequest request,Integer pageSize,Integer page) {
		
		OutputData out = new OutputData().returnSuccess();
		
		User user = new User();
		
		try {
			try {
				BeanUtils.copyProperties(user, request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("Request convert Exception {}",e);
	        	return out.returnFail(e.getMessage());
			}
			userService.deleteUser(user.getId());
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Delete user Exception{}",e);
			out.returnFail(e.getMessage());
		}
		
		if(pageSize == null || pageSize<1){
			pageSize = 10;
		}
		if(page == null || page<1){
			page = 0;
		}
		@SuppressWarnings("deprecation")
		Pageable pageable = new PageRequest(page,pageSize);
		
		Page<User> pageUser = userService.findAllUserPage(pageable);
		
		out.setData(pageUser);
		
		return out;
	}
	
	
	
	@SuppressWarnings({"rawtypes"})
	@ApiOperation(value = "/userAuthorityManage",notes = "Manage authorities for selected user include grant and revoke")
	@RequestMapping(value = "/userAuthorityManage", method = RequestMethod.POST)
	public OutputData userAuthorityManage(@RequestBody UserAuthoityRelRequest request) {
		
		OutputData out = new OutputData().returnSuccess();
		
		List<Long> grantedAuthorityIds = request.getAuthorityIds();
		
		try {
			
			if (userService.dealUserAuthorityManage(request.getUserId(),grantedAuthorityIds)) {
				return out;
			} else {
				return out.returnFail("UserService 处理失败");
			}
		} catch (Exception e) {
			// TODO: handle exception
			return out.returnFail(e.getMessage());
		}
		
	}
	
	
	
	
	@SuppressWarnings({"rawtypes"})
	@ApiOperation(value = "/userGroupManage",notes = "Manage groups for selected user include grant and revoke")
	@RequestMapping(value = "/userGroupManage", method = RequestMethod.POST)
	public OutputData userGroupManage(@RequestBody UserGroupRelRequest request) {
		
		OutputData out = new OutputData().returnSuccess();
		
		List<Long> grantedGroupIds = request.getGroupIds();
		
		try {
			if (userService.dealUserGroupManage(request.getUserId(),grantedGroupIds)) {
				return out;
			} else {
				return out.returnFail("UserService 处理失败");
			}

		} catch (Exception e) {
			// TODO: handle exception
			return out.returnFail(e.getMessage());
		}
		
	}
	
	
	@SuppressWarnings({"rawtypes"})
	@RequestMapping(value = "/userFilter", method = RequestMethod.GET)
	@ApiOperation(value = "/userFilter",notes = "Filter Users")
	public OutputData userFilter(Long orgId,Long dptId,Long groupId, Long roleId, Boolean locked,Integer pageSize,Integer page) {
		
		OutputData out = new OutputData().returnSuccess();
		
		if(pageSize == null || pageSize<1){
			pageSize = 10;
		}
		if(page == null || page<1){
			page = 0;
		}
		@SuppressWarnings("deprecation")
		Pageable pageable = new PageRequest(page,pageSize);
		
		Page<User> pageUsers = userService.findUserListByFilterCriteria(orgId, dptId, groupId, roleId, locked, pageable);
		
		out.setData(pageUsers);
		
		return out;
	}
	
	
	
	
	
	
}
