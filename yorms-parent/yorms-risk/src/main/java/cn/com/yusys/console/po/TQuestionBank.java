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