package cn.com.yusys.oca.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class GroupRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "主键ID",required = false)
	private Long id;
	
	@ApiModelProperty(value = "用户组名称",required = true)
	private String groupName;
	
	@ApiModelProperty(value = "用户组描述",required = true)
	private String groupDesc;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDesc() {
		return groupDesc;
	}

	public void setGroupDesc(String groupDesc) {
		this.groupDesc = groupDesc;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
