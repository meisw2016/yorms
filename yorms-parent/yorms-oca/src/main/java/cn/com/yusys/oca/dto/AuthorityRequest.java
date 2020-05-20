package cn.com.yusys.oca.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class AuthorityRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键ID",required = false)
	private Long id;
	
	@ApiModelProperty(value = "角色名称",required = true)
	private String authorityName;
	
	@ApiModelProperty(value = "角色描述",required = true)
	private String authorityDesc;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAuthorityName() {
		return authorityName;
	}
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	public String getAuthorityDesc() {
		return authorityDesc;
	}
	public void setAuthorityDesc(String authorityDesc) {
		this.authorityDesc = authorityDesc;
	}
}