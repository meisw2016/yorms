package cn.com.yusys.oca.service;

import java.util.List;

import com.alibaba.fastjson.JSONArray;

import cn.com.yusys.oca.po.Organization;


public interface OrganizationService {
	
	public void save(Organization organization);
	
	public void deleteOrg(Long id);
	
	public Organization findById(long id);
	
	/**
	 *为parent添加子节点child
	 * */
	public void addChildOrg(Organization parent,Organization child);
	
	
	
	/**
	 * 获取所有子节点包括子单位及子部门*/
	public List<Organization> getChildNodes(Organization organization);
	
	/**
	 * 获取某个单位下的部门，不包括子单位的部门*/
	public List<Organization> getChildDepartment(Organization organization);
	
	/**
	 * 获取某个单位下子单位，不包括部门*/
	public List<Organization> getChildOrgs(Organization organization);
	
	public long getchildrenCount(Organization organization);
	
	public long getOrganizationTreeDepth(Organization organization);
	
	/**
	 * 判断org1是否是org2的子节点*/
	public boolean isChildNode(Organization org1,Organization org2);
	
	/**
	 * 判断org1是否是org2的父节点*/
	public boolean isParentNode(Organization org1,Organization org2);
	
	
	/**
	 * 获取组织结构树，包含部门*/
	public JSONArray getOrgsTreeviewJsonIncludeDepartment();
	
	/**
	 * 获取组织结构树，不包含部门*/
	public JSONArray getOrgsTreeviewJsonExcludeDepartment();
	
	/**
	 * 获取指定节点的族谱路径，以正序保存在列表中*/
	public List<Organization> getOrgPath(Organization organization);
	
	
	/**
	 * 获取所有标记为部门的节点*/
	public List<Organization> getAllDepartment();
	
	
	
}
