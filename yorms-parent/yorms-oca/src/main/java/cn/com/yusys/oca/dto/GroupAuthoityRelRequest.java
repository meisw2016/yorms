package cn.com.yusys.oca.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class GroupAuthoityRelRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "GrounId",required = true)
	private Long groupId;
	
	@ApiModelProperty(value = "AuthorityIds",required = true)
	private List<Long> AuthorityIds;

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public List<Long> getAuthorityIds() {
		return AuthorityIds;
	}

	public void setAuthorityIds(List<Long> authorityIds) {
		AuthorityIds = authorityIds;
	}
	
	
}
