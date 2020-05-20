package cn.com.yusys.oca.controller.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.yusys.file.util.OutputData;
import cn.com.yusys.oca.dto.GroupAuthoityRelRequest;
import cn.com.yusys.oca.dto.GroupRequest;
import cn.com.yusys.oca.po.Group;
import cn.com.yusys.oca.service.GroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @项目名称 yorms-oca 
 * @类名称 GroupController 
 * @类描述 
 * @功能描述:
 * @创建人：zhangal@yusys.com.cn
 * @创建时间：2020/3/6
 * @修改备注：
 * @修改日期 修改人员 修改原因 
 * @version 1.0.0
 * @Copyright (c) 
 */


@RestController
@RequestMapping("/Group")
@Api(value = "/Group")
public class GroupController {
	
	@Resource
	GroupService groupService;
	
	private static final Logger log = LoggerFactory.getLogger(GroupController.class);
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/groupAdd", method = RequestMethod.POST)
	@ApiOperation(value = "/groupAdd",notes = "Add group")
	public OutputData groupAdd(@RequestBody GroupRequest request) {
		
		OutputData out = new OutputData().returnSuccess();
		
		Group group = new Group();
		
		try {
			try {
				BeanUtils.copyProperties(group, request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("Request convert Exception {}",e);
	        	return out.returnFail(e.getMessage());
			}
			groupService.save(group);
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Add group Exception{}",e);
			out.returnFail(e.getMessage());
		}
		return out;
	}
	
	
	@SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/queryAll",method = RequestMethod.GET)
	@ApiOperation(value = "/queryAll",notes = "Query all groups")
	public OutputData queryAll(){
		OutputData out = new OutputData().returnSuccess();
		
		List<Group> groupList = new ArrayList<Group>();
		try{
			groupList = groupService.getAllGroupList();
			
			out.setData(groupList);
		}catch(Exception e){
			log.info("Query all group Exception {}",e);
			out.returnFail(e.getMessage());
		}
		return out;
	}
	
	
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	@RequestMapping(value = "/groupManage", method = RequestMethod.GET)
	@ApiOperation(value = "/queryAllByPage",notes = "Query groups by page option")
	public OutputData groupManage(Integer pageSize,Integer page) {
		
		OutputData out = new OutputData().returnSuccess();
		
		if(pageSize == null || pageSize<1){
			pageSize = 10;
		}
		if(page == null || page<1){
			page = 0;
		}
		
		@SuppressWarnings("deprecation")
		Pageable pageable = new PageRequest(page,pageSize);
		
		Page<Group> pageGroups = groupService.findAllGroupPage(pageable);
		
		out.setData(pageGroups);
		
		return out;
	}
	
	
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	@RequestMapping(value = "/groupUpdate", method = RequestMethod.POST)
	@ApiOperation(value = "/groupUpdate",notes = "Update group")
	public OutputData groupUpdate(@RequestBody GroupRequest request,Integer pageSize,Integer page) {
		
		OutputData out = new OutputData().returnSuccess();
		
		Group group = new Group();
		
		try {
			try {
				BeanUtils.copyProperties(group, request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("Request convert Exception {}",e);
	        	return out.returnFail(e.getMessage());
			}
			groupService.save(group);
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Update group Exception{}",e);
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
		
		Page<Group> pageGroup = groupService.findAllGroupPage(pageable);
		
		out.setData(pageGroup);
		
		return out;
	}
	
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	@RequestMapping(value = "/groupDelete", method = RequestMethod.POST)
	@ApiOperation(value = "/groupDelete",notes = "Delete group")
	public OutputData groupDelete(@RequestBody GroupRequest request,Integer pageSize,Integer page) {
		
		OutputData out = new OutputData().returnSuccess();
		
		Group group = new Group();
		
		try {
			try {
				BeanUtils.copyProperties(group, request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("Request convert Exception {}",e);
	        	return out.returnFail(e.getMessage());
			}
			groupService.deleteGroup(group.getId());
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Delete group Exception{}",e);
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
		
		Page<Group> pageGroup = groupService.findAllGroupPage(pageable);
		
		out.setData(pageGroup);
		
		return out;
	}
	
	
	
	@SuppressWarnings({"rawtypes"})
	@ApiOperation(value = "/groupAuthorityManage",notes = "Manage authorities for selected group")
	@RequestMapping(value = "/groupAuthorityManage", method = RequestMethod.POST)
	public OutputData groupAuthorityGrant(@RequestBody GroupAuthoityRelRequest request) {
		
		OutputData out = new OutputData().returnSuccess();
		
		Map<Long, Long> groupAuthorityRelMap = new HashMap<Long, Long>();
		
		List<Long> grantedAuthorityIds = request.getAuthorityIds();
		
		for (int i = 0; i < grantedAuthorityIds.size(); i++) {
			groupAuthorityRelMap.put(request.getGroupId(), grantedAuthorityIds.get(i));
		}
		
		try {
			groupService.dealGroupAuthorityGrant(groupAuthorityRelMap);
		} catch (Exception e) {
			// TODO: handle exception
			out.returnFail(e.getMessage());
		}
		return out;
		
	}
	
	
	
}
