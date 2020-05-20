package cn.com.yusys.console.po;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
*  问题表
* @author meisw 2020-03-05
*/
@Entity
@Data
@Table(name="t_question_bank")
public class TQuestionBank implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    /**
    * 主键
    */
    private Integer id;

    /**
    * 整改序号
    */
    private String rectificId;

    /**
    * 问题分类
    */
    private Integer questionType;

    /**
    * 问题描述
    */
    private String description;

    /**
    * 整改措施id
    */
    private Integer rectificMeasureId;

    /**
    * 发现方式
    */
    private String discoveryMode;

    /**
    * 原因分析
    */
    private String causeAnalysis;

    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRectificId() {
		return rectificId;
	}

	public void setRectificId(String rectificId) {
		this.rectificId = rectificId;
	}

	public Integer getQuestionType() {
		return questionType;
	}

	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getRectificMeasureId() {
		return rectificMeasureId;
	}

	public void setRectificMeasureId(Integer rectificMeasureId) {
		this.rectificMeasureId = rectificMeasureId;
	}

	public String getDiscoveryMode() {
		return discoveryMode;
	}

	public void setDiscoveryMode(String discoveryMode) {
		this.discoveryMode = discoveryMode;
	}

	public String getCauseAnalysis() {
		return causeAnalysis;
	}

	public void setCauseAnalysis(String causeAnalysis) {
		this.causeAnalysis = causeAnalysis;
	}

	public String getQuestionName() {
		return questionName;
	}

	public void setQuestionName(String questionName) {
		this.questionName = questionName;
	}

	public String getQuestionNo() {
		return questionNo;
	}

	public void setQuestionNo(String questionNo) {
		this.questionNo = questionNo;
	}

	/**
    * 问题名称
    */
    private String questionName;

    /**
    * 问题编号
    */
    private String questionNo;

    public TQuestionBank() {
    }

}