package cn.com.yusys.console.po;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
*  整改措施
* @author meisw 2020-03-05
*/
@Entity
@Data
@Table(name="t_rectific_measure")
public class TRectificMeasure implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue
    /**
    * 主键id
    */
    private Integer id;

    /**
    * 整改id
    */
    private Integer rectificMeasureId;

    /**
    * 计划完成时间
    */
    private Date planCompletTime;

    /**
    * 整改牵头部门名称
    */
    private String rectificDeptName;

    /**
    * 整改牵头部门id
    */
    private Integer rectificDeptId;

    /**
    * 整改牵头人
    */
    private String rectificLeaderName;

    /**
    * 整改配合部门
    */
    private String rectificCooperatDept;

    /**
    * 整改状态 1：已整改: 2：未整改、3：无法整改: 4：整改中
    */
    private String rectificStatus;

    /**
    * 整理类型：1：短期，2：长期
    */
    private String rectificType;

    public TRectificMeasure() {
    }

}