package cn.com.yusys.oca.po;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_user")
public class User implements Serializable{

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
	
	
	/**
	 *用户名
	 */
	@Column(name = "user_name",nullable = false, unique = true)
    private String userName;
	
	/**
	 *用户编码
	 */
	@Column(name = "user_code",nullable = false, unique = true)
    private String userCode;
	
	/**
	 * 密码，密文存储
	 */
	@Column(name = "password",nullable = false)
    private String password;
	
	/**
	 *用户描述
	 */
	@Column(name = "user_desc")
	private String userDesc;
	
	/**
	 *用户过期时间
	 */
	@Column(name = "expired_date")
    private String expiredDate;
    
	/**
	 *密码过期时间
	 */
	@Column(name = "credential_expired_date")
    private String credentialExpiredDate;
    
	/**
	 *锁定状态
	 */
	@Column(name = "locked")
    private boolean locked;
    
	/**
	 *密码过期状态
	 */
	@Column(name = "credential_expired")
    private boolean credentialExpired;
	
	/**
	 *邮箱
	 */
	@Column(name = "email")
    private String email;
	
	
	/**
	 *电话
	 */
	@Column(name = "phone_number")
    private String phoneNumber;
	
	
	
	
	/**
	 *部门编号
	 */
	@Column(name = "dpt_id")
    private Long dptId;
	
	
	
	/**
	 *组织编号
	 */
	@Column(name = "org_id")
    private Long orgId;
	
	


	public Long getDptId() {
		return dptId;
	}


	public void setDptId(Long dptId) {
		this.dptId = dptId;
	}


	public Long getOrgId() {
		return orgId;
	}


	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserCode() {
		return userCode;
	}


	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getUserDesc() {
		return userDesc;
	}


	public void setUserDesc(String userDesc) {
		this.userDesc = userDesc;
	}


	public String getExpiredDate() {
		return expiredDate;
	}


	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}


	public String getCredentialExpiredDate() {
		return credentialExpiredDate;
	}


	public void setCredentialExpiredDate(String credentialExpiredDate) {
		this.credentialExpiredDate = credentialExpiredDate;
	}


	public boolean isLocked() {
		return locked;
	}


	public void setLocked(boolean locked) {
		this.locked = locked;
	}


	public boolean isCredentialExpired() {
		return credentialExpired;
	}


	public void setCredentialExpired(boolean credentialExpired) {
		this.credentialExpired = credentialExpired;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
    

}
