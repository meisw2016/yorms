package cn.com.yusys.oca.controller.rest;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import cn.com.yusys.file.util.OutputData;
import cn.com.yusys.oca.dto.GroupRequest;
import cn.com.yusys.oca.dto.OrganizationRequest;
import cn.com.yusys.oca.po.Group;
import cn.com.yusys.oca.po.Organization;
import cn.com.yusys.oca.service.OrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/Organization")
@Api(value = "/Organization")
public class OrganizationController {
	
	@Resource
	OrganizationService organizationService;
	
	private static final Logger log = LoggerFactory.getLogger(OrganizationController.class);
	
	/**
	 * Controller 处理组织结构树请求
	 * 
	 * @author Crescent
	 */
	@RequestMapping(value = "/organizationTreeIncludeDepartment", method = RequestMethod.GET)
	@ApiOperation(value = "/organizationTreeIncludeDepartment",notes = "Get organizationTree include department")
	public OutputData organizationJson() {
		OutputData out = new OutputData().returnSuccess();
		out.setData(organizationService.getOrgsTreeviewJsonIncludeDepartment());
		return out;
	}
	
	
	
	/**
	 * Controller 处理组织结构树请求
	 * 
	 * @author Crescent
	 */
	@RequestMapping(value = "/organizationTreeExcludeDepartment", method = RequestMethod.GET)
	@ApiOperation(value = "/organizationTreeExcludeDepartment",notes = "Get organizationTree Exclude department")
	public OutputData organizationJsonExcludeDepartment() {
		OutputData out = new OutputData().returnSuccess();
		out.setData(organizationService.getOrgsTreeviewJsonExcludeDepartment());
		return out;
	}
	
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/nodeAdd", method = RequestMethod.POST)
	@ApiOperation(value = "/nodeAdd",notes = "Add organization or department")
	public OutputData orgAdd(@RequestBody OrganizationRequest request,Long parentId) {
		
		OutputData out = new OutputData().returnSuccess();
		
		Organization organizationForAddd = new Organization();
		
		Organization parent = organizationService.findById(parentId);
		
		try {
			try {
				BeanUtils.copyProperties(organizationForAddd, request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				log.error("Request convert Exception {}",e);
	        	return out.returnFail(e.getMessage());
			}
			organizationService.addChildOrg(parent, organizationForAddd);
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Add Organization Exception{}",e);
			out.returnFail(e.getMessage());
		}
		return out;
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/nodeUpdate", method = RequestMethod.POST)
	@ApiOperation(value = "/nodeUpdate",notes = "Update organization or department")
	public OutputData organizationUpdate(@RequestBody OrganizationRequest request){
		
		OutputData out = new OutputData().returnSuccess();
		
		try {
			Organization orgForEdit = new Organization();
			
			BeanUtils.copyProperties(orgForEdit, request);
			
			Organization organizationForSave = organizationService.findById(orgForEdit.getId());
			
			organizationForSave.setName(orgForEdit.getName());
			organizationForSave.setOrganization_code(orgForEdit.getOrganization_code());
			organizationForSave.setOrgDesc(orgForEdit.getOrgDesc());
			
			organizationService.save(organizationForSave);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Update Organization Exception{}",e);
			out.returnFail(e.getMessage());
		}
		
		return out;
		
	}
	
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getNodeById", method = RequestMethod.GET)
	@ApiOperation(value = "/getNodeById",notes = "Get node by NodeId")
	public OutputData getNodeById(Long id){
		
		OutputData out = new OutputData().returnSuccess();
		
		try {
			Organization node = organizationService.findById(id);
			out.setData(node);
			
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Get node by NodeId Exception{}",e);
			out.returnFail(e.getMessage());
		}
		
		return out;
		
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/nodeDel", method = RequestMethod.POST)
	@ApiOperation(value = "/nodeDel",notes = "Delete organization or department")
	public OutputData organizationDel(Long id){
		
		OutputData out = new OutputData().returnSuccess();
		
		try {
			organizationService.deleteOrg(id);
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Delete Organization Exception{}",e);
			out.returnFail(e.getMessage());
		}
		
		return out;
		
	}
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/organizationGenealogyPath", method = RequestMethod.GET)
	@ApiOperation(value = "/organizationGenealogyPath",notes = "Get node gennealogy path")
	public OutputData getNodeGenealogyPath(Long id) {
		
		OutputData out = new OutputData().returnSuccess();
		
		try {
			Organization nodeSelected = organizationService.findById(id);
			List<Organization> nodeList = organizationService.getOrgPath(nodeSelected);
			nodeList.add(nodeSelected);
			
			out.setData(nodeList);
		} catch (Exception e) {
			// TODO: handle exception
			log.info("Get node Gennealogy path Exception{}",e);
			out.returnFail(e.getMessage());
		}
		return out;
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getChildDepartment", method = RequestMethod.GET)
	@ApiOperation(value = "/getChildDepartment",notes = "Get child departments")
	public OutputData getChildDepartment(Long id) {
		
		OutputData out = new OutputData().returnSuccess();
		
		Organization nodeSelected = organizationService.findById(id);
		List<Organization> nodeList = organizationService.getChildDepartment(nodeSelected);
		
		out.setData(nodeList);
		
		return out;
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/getChildOrgs", method = RequestMethod.GET)
	@ApiOperation(value = "/getChildOrgs",notes = "Get child orgs")
	public OutputData getChildOrgs(Long id) {
		
		OutputData out = new OutputData().returnSuccess();
		
		Organization nodeSelected = organizationService.findById(id);
		
		List<Organization> nodeList = organizationService.getChildOrgs(nodeSelected);
		
		out.setData(nodeList);
		
		return out;
	}
	

}
