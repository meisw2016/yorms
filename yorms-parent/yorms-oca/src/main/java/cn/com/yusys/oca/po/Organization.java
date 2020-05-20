package cn.com.yusys.oca.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "t_organization")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Organization implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 唯一标识
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
	
	@Column(name = "organization_code",nullable = false, unique = true)
	private String organization_code;
	
	@Column(name = "name",nullable = false, unique = true)
	private String name;
	
	@Column(name = "org_desc")
	private String orgDesc;
	
	@Column(name = "left_id")
	private long leftId;
	
	@Column(name = "right_id")
	private long rightId;
	
	@Column(name = "layer")
	private long layer;
	
	@Column(name = "department")
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

	public long getLeftId() {
		return leftId;
	}

	public void setLeftId(long leftId) {
		this.leftId = leftId;
	}

	public long getRightId() {
		return rightId;
	}

	public void setRightId(long rightId) {
		this.rightId = rightId;
	}

	public long getLayer() {
		return layer;
	}

	public void setLayer(long layer) {
		this.layer = layer;
	}

	public boolean isDepartment() {
		return department;
	}

	public void setDepartment(boolean department) {
		this.department = department;
	}



	
	
}


