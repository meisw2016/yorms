package cn.com.yusys.oca.dao;

import java.util.List;

import cn.com.yusys.oca.po.Organization;


public interface OrganizationRepositorySweeper {
	
	/**
	 * 查询所有子节点，包括子单位及子部门*/
	public List<Organization> getChildNodesByPreorderTraversal(Organization organization);
	
	/**
	 * 查询所有子单位*/
	public List<Organization> getChildOrgsByPreorderTraversal(Organization organization);
	
	public void addChildOrg(Organization parent,Organization child);
	
	public void deleteOrg(Long id);
}
