package cn.com.yusys.oca.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;

import cn.com.yusys.oca.dao.OrganizationRepository;
import cn.com.yusys.oca.po.Organization;
import cn.com.yusys.oca.service.OrganizationService;


@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {
	
	@Autowired
    private OrganizationRepository organizationRepository;

	@Override
	public void save(Organization organization) {
		// TODO Auto-generated method stub
		organizationRepository.save(organization);
	}

	@Override
	public void deleteOrg(Long id) {
		// TODO Auto-generated method stub
		organizationRepository.deleteOrg(id);
	}
	
	@Override
	public Organization findById(long id) {
		// TODO Auto-generated method stub
		return organizationRepository.getOne(id);
	}

	@Override
	public void addChildOrg(Organization parent, Organization child) {
		// TODO Auto-generated method stub
		organizationRepository.addChildOrg(parent, child);
	}

	@Override
	public JSONArray getOrgsTreeviewJsonIncludeDepartment() {
		// TODO Auto-generated method stub
		List<Organization> organizations = organizationRepository.getOrganizationsByPreorderTraversal();		
		
		String jSONString = "[";
		
		Organization lastOrganization = new Organization();
		lastOrganization.setLayer(0);
		
		for (int i = 0; i < organizations.size(); i++) {
			Organization organization = organizations.get(i);
			
			if (organization.getLayer() < lastOrganization.getLayer()) {
				// 如果当前节点的层级已经小于上个节点的层级 说明当前节点已经离开该子树
				
				// 层级差为要加的关闭符的数量
				long layerdiff = lastOrganization.getLayer() - organization.getLayer();
				for (int j = 0; j < layerdiff; j++) {
					jSONString = jSONString.substring(0,jSONString.length() - 1);
					jSONString += "]},";
				}
			}

			// 当前节点有子节点
			if (this.getchildrenCount(organization) > 0) {
				jSONString += "{text:'" + organization.getName() + "',id:'"+ organization.getId() +"',nodes:[";
			}
			// 当前节点已经是叶子节点
			else {
				jSONString += "{text:'" + organization.getName() + "',id:'"+ organization.getId() +"'},";
			}
			
			//已经到了最后一个节点
			if (i == organizations.size() - 1) {
				
				// 获取当前节点与根节点的层级差
				long layerdiff = organization.getLayer() - 1;
				for (int j = 0; j < layerdiff; j++) {
					jSONString = jSONString.substring(0,jSONString.length() - 1);
					jSONString += "]},";
				}
				
				jSONString = jSONString.substring(0,jSONString.length() - 1);
				jSONString += "]";
			}
			lastOrganization = organization;

		}
		return JSONArray.parseArray(jSONString);  
	}
	
	
	@Override
	public JSONArray getOrgsTreeviewJsonExcludeDepartment() {
		// TODO Auto-generated method stub
		List<Organization> organizations = organizationRepository.getOrganizationsExcludeDepartmentByPreorderTraversal();		
		
		String jSONString = "[";
		
		Organization lastOrganization = new Organization();
		lastOrganization.setLayer(0);
		
		for (int i = 0; i < organizations.size(); i++) {
			Organization organization = organizations.get(i);
			
			if (organization.getLayer() < lastOrganization.getLayer()) {
				// 如果当前节点的层级已经小于上个节点的层级 说明当前节点已经离开该子树
				
				// 层级差为要加的关闭符的数量
				long layerdiff = lastOrganization.getLayer() - organization.getLayer();
				for (int j = 0; j < layerdiff; j++) {
					jSONString = jSONString.substring(0,jSONString.length() - 1);
					jSONString += "]},";
				}
			}

			// 当前节点有子组织结构
			if (this.getChildOrgs(organization).size() > 0) {
				jSONString += "{text:'" + organization.getName() + "',id:'"+ organization.getId() +"',nodes:[";
			}
			// 当前节点已经是叶子节点
			else {
				jSONString += "{text:'" + organization.getName() + "',id:'"+ organization.getId() +"'},";
			}
			
			//已经到了最后一个节点
			if (i == organizations.size() - 1) {
				
				// 获取当前节点与根节点的层级差
				long layerdiff = organization.getLayer() - 1;
				for (int j = 0; j < layerdiff; j++) {
					jSONString = jSONString.substring(0,jSONString.length() - 1);
					jSONString += "]},";
				}
				
				jSONString = jSONString.substring(0,jSONString.length() - 1);
				jSONString += "]";
			}
			lastOrganization = organization;

		}
		return JSONArray.parseArray(jSONString);  
	}
	

	@Override
	public long getchildrenCount(Organization organization) {
		// TODO Auto-generated method stub
		long count = (organization.getRightId() - organization.getLeftId() - 1)/2;
		return count;
	}

	@Override
	public long getOrganizationTreeDepth(Organization organization) {
		// TODO Auto-generated method stub
		List<Organization> organizations = organizationRepository.getChildNodesByPreorderTraversal(organization);
		
		long depth = 1;
		
		for (Organization organizationElement : organizations) {
			if (organizationElement.getLayer() >= depth) {
				depth = organizationElement.getLayer();
			}
		}
		return depth;
	}

	@Override
	public boolean isChildNode(Organization org1, Organization org2) {
		// TODO Auto-generated method stub		
		return (org1.getLeftId() > org2.getLeftId() && org1.getRightId() < org2.getRightId());
	}

	@Override
	public boolean isParentNode(Organization org1, Organization org2) {
		// TODO Auto-generated method stub
		return (org1.getLeftId() < org2.getLeftId() && org1.getRightId() > org2.getRightId());
	}

	@Override
	public List<Organization> getOrgPath(Organization organization) {
		// TODO Auto-generated method stub
		return organizationRepository.getOrgPath(organization.getLeftId(), organization.getRightId());
	}

	@Override
	public List<Organization> getAllDepartment() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public List<Organization> getChildNodes(Organization organization) {
		// TODO Auto-generated method stub
		return organizationRepository.getChildNodesByPreorderTraversal(organization);
	}
	
	@Override
	public List<Organization> getChildOrgs(Organization organization) {
		// TODO Auto-generated method stub
		return organizationRepository.getChildOrgsByPreorderTraversal(organization);
	}

	@Override
	public List<Organization> getChildDepartment(Organization organization) {
		// TODO Auto-generated method stub
		List<Organization> allNodeList = this.getChildNodes(organization);
		
		List<Organization> childOrgList = this.getChildOrgs(organization);
		
		List<Organization> excludedNodeList = new ArrayList<Organization>();
		
		for (int i = 0; i < childOrgList.size(); i++) {
			excludedNodeList.addAll(organizationRepository.getChildNodesByPreorderTraversal(childOrgList.get(i)));
		}
		
		allNodeList.removeAll(excludedNodeList);
		allNodeList.removeAll(childOrgList);
		
		return allNodeList;
		
	}

	



	
	
	

}
