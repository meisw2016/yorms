package cn.com.yusys.oca.dto;

import java.io.Serializable;

import javax.persistence.Column;

import io.swagger.annotations.ApiModelProperty;

public class OrganizationRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 唯一标识
	 */
	@ApiModelProperty(value = "主键ID",required = false)
    private long id;
	
	@ApiModelProperty(value = "组织代码",required = false)
	private String organization_code;
	
	@ApiModelProperty(value = "机构名称",required = true)
	private String name;
	
	@ApiModelProperty(value = "机构描述",required = false)
	private String orgDesc;
	
	
	@ApiModelProperty(value = "是否部门",required = true)
	private boolean department;
	

	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getOrganization_code() {
		return organization_code;
	}


	public void setOrganization_code(String organization_code) {
		this.organization_code = organization_code;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getOrgDesc() {
		return orgDesc;
	}


	public void setOrgDesc(String orgDesc) {
		this.orgDesc = orgDesc;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public boolean isDepartment() {
		return department;
	}


	public void setDepartment(boolean department) {
		this.department = department;
	}


	
	
	
}
