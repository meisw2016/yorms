package cn.com.yusys.oca.po;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_duty")
public class Duty implements Serializable{

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
	
	
	@Column(name = "duty_name")
	private String dutyName;
	
	@Column(name = "duty_desc")
	private String dutyDesc;
	
	
	@Column(name = "duty_sts")
	private String dutyStatus;
	
	@Column(name = "dpt_id")
	private Long dptId;
	


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getDutyName() {
		return dutyName;
	}


	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}


	public String getDutyDesc() {
		return dutyDesc;
	}


	public void setDutyDesc(String dutyDesc) {
		this.dutyDesc = dutyDesc;
	}


	public String getDutyStatus() {
		return dutyStatus;
	}


	public void setDutyStatus(String dutyStatus) {
		this.dutyStatus = dutyStatus;
	}


	public Long getDptId() {
		return dptId;
	}


	public void setDptId(Long dptId) {
		this.dptId = dptId;
	}
	
	
	
	
	

}
