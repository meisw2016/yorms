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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.com.yusys.file.util.OutputData;
import cn.com.yusys.oca.dto.AuthorityRequest;
import cn.com.yusys.oca.po.Authority;
import cn.com.yusys.oca.service.AuthorityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @项目名称 yorms-oca 
 * @类名称 AuthorityController 
 * @类描述 
 * @功能描述:
 * @创建人：zhangal@yusys.com.cn
 * @创建时间：2020/3/5 
 * @修改备注：
 * @修改日期 修改人员 修改原因 
 * @version 1.0.0
 * @Copyright (c) 
 */

@RestController
@RequestMapping("/Authority")
@Api(value = "/Authority")
public class AuthorityController {
	
	@Resource
	AuthorityService authorityService;
	
	private static final Logger log = LoggerFactory.getLogger(AuthorityController.class);
	
	
	
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/authorityAdd", method = RequestMethod.POST)
	@ApiOperation(value = "/authorityAdd",notes = "Add authority")
	public OutputData authorityAdd(@RequestBody AuthorityRequest request) {
		
		OutputData out = new OutputData().returnSuccess();
		
		Authority authority = new Authority();
		
		try {
			try {
				BeanUtils.copyProperties(authority, request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("Request convert Exception {}",e);
	        	return out.returnFail(e.getMessage());
			}
			authorityService.save(authority);
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Add authority Exception{}",e);
			out.returnFail(e.getMessage());
		}
		return out;
	}
	
	
	
	@SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/queryAll",method = RequestMethod.GET)
	@ApiOperation(value = "/queryAll",notes = "Query all authorities")
	public OutputData queryAll(){
		OutputData out = new OutputData().returnSuccess();
		
		List<Authority> authorityList = new ArrayList<Authority>();
		try{
			authorityList = authorityService.getAllAuthoriytList();
			
			out.setData(authorityList);
		}catch(Exception e){
			log.info("Query all authority Exception {}",e);
			out.returnFail(e.getMessage());
		}
		return out;
	}
	
	
	@SuppressWarnings({"rawtypes", "unchecked"})
    @RequestMapping(value = "/testFeign",method = RequestMethod.GET)
	@ApiOperation(value = "/testFeign",notes = "testFeign")
	public String testFeign(){
		return "testFeign";
	}
	
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	@RequestMapping(value = "/authorityManage", method = RequestMethod.GET)
	@ApiOperation(value = "/queryAllByPage",notes = "Query authorities by page option")
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
		
		Page<Authority> pageAuthorities = authorityService.findAllAuthorityPage(pageable);
		
		out.setData(pageAuthorities);
		
		return out;
	}
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	@RequestMapping(value = "/authorityUpdate", method = RequestMethod.POST)
	@ApiOperation(value = "/authorityUpdate",notes = "Update authority")
	public OutputData authorityUpdate(@RequestBody AuthorityRequest request,Integer pageSize,Integer page) {
		
		OutputData out = new OutputData().returnSuccess();
		
		Authority authority = new Authority();
		
		try {
			try {
				BeanUtils.copyProperties(authority, request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("Request convert Exception {}",e);
	        	return out.returnFail(e.getMessage());
			}
			authorityService.save(authority);
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Update authority Exception{}",e);
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
		
		Page<Authority> pageAuthorities = authorityService.findAllAuthorityPage(pageable);
		
		out.setData(pageAuthorities);
		
		return out;
	}
	
	
	@SuppressWarnings({"rawtypes", "unchecked"})
	@RequestMapping(value = "/authorityDelete", method = RequestMethod.POST)
	@ApiOperation(value = "/authorityDelete",notes = "Delete authority")
	public OutputData authorityDelete(@RequestBody AuthorityRequest request,Integer pageSize,Integer page) {
		
		OutputData out = new OutputData().returnSuccess();
		
		Authority authority = new Authority();
		
		try {
			try {
				BeanUtils.copyProperties(authority, request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("Request convert Exception {}",e);
	        	return out.returnFail(e.getMessage());
			}
			authorityService.deleteAuthority(authority.getId());
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Delete authority Exception{}",e);
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
		
		Page<Authority> pageAuthorities = authorityService.findAllAuthorityPage(pageable);
		
		out.setData(pageAuthorities);
		
		return out;
	}
	
	
}
