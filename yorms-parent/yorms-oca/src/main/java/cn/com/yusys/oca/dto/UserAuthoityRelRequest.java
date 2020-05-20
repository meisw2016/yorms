package cn.com.yusys.oca.dto;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class UserAuthoityRelRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户ID
	 */
	@ApiModelProperty(value = "UserId",required = true)
	private Long userId;
	
	
	/**
	 * 角色ID
	 */
	@ApiModelProperty(value = "AuthorityIds",required = true)
	private List<Long> AuthorityIds;


	public Long getUserId() {
		return userId;
	}


	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public List<Long> getAuthorityIds() {
		return AuthorityIds;
	}


	public void setAuthorityIds(List<Long> authorityIds) {
		AuthorityIds = authorityIds;
	}
	
	
	
	
}
