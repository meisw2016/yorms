package cn.com.yusys.oca.po;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_group_authorities")
public class GroupAuthorityRel {
	
	/**
	 * 唯一标识
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * 用户组ID
	 */
	@Column(name = "group_id",nullable = false)
	private Long groupId;
	
	
	/**
	 * 角色ID
	 */
	@Column(name = "authority_id",nullable = false)
	private Long authorityId;



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Long getGroupId() {
		return groupId;
	}


	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}


	public Long getAuthorityId() {
		return authorityId;
	}


	public void setAuthorityId(Long authorityId) {
		this.authorityId = authorityId;
	}
	
	
	
}
