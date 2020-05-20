package cn.com.yusys.oca.dto;
import java.io.Serializable;
import io.swagger.annotations.ApiModelProperty;

public class UserRequest implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	@ApiModelProperty(value = "主键ID",required = false)
    private long id;
	
	

	@ApiModelProperty(value = "用户名",required = true)
    private String userName;
	

	@ApiModelProperty(value = "用户编码",required = false)
    private String userCode;
	
	/**
	 * 密码，密文存储
	 */
	@ApiModelProperty(value = "密码",required = true)
    private String password;
	
	/**
	 *用户描述
	 */
	@ApiModelProperty(value = "用户描述",required = false)
	private String userDesc;
	
	/**
	 *用户过期时间
	 */
	@ApiModelProperty(value = "用户过期时间",required = false)
    private String expiredDate;
    
	/**
	 *密码过期时间
	 */
	@ApiModelProperty(value = "密码过期时间",required = false)
    private String credentialExpiredDate;
    
	/**
	 *锁定状态
	 */
	@ApiModelProperty(value = "锁定状态",required = false)
    private boolean locked;
    
	/**
	 *密码过期状态
	 */
	@ApiModelProperty(value = "密码过期状态",required = false)
    private boolean credentialExpired;
	
	/**
	 *邮箱
	 */
	@ApiModelProperty(value = "邮箱",required = false)
    private String email;
	
	
	/**
	 *电话
	 */
	@ApiModelProperty(value = "电话",required = false)
    private String phoneNumber;
	
	
	/**
	 *部门编号
	 */
	@ApiModelProperty(value = "部门编号",required = true)
    private Long dptId;
	
	
	
	/**
	 *组织编号
	 */
	@ApiModelProperty(value = "单位编号",required = true)
    private Long orgId;


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
	
	
}
