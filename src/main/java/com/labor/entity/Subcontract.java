package com.labor.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * @author BoCong
 * @date 2022/5/3
 */
@TableName("t_subcontrac")
public class Subcontract {
    private static final long serialVersionUID = 1L;
    /**
     *  主键标识
     */
    private Long ID;
    /**
     * 文件名称
     */
    private String contractName;

    /**
     *合同类型
     */
    private Integer contractType;
    /**
     * 创建人
     */
    private String creater;
    /**
     * 创建时间
     */
    private Date createAt;
    /**
     * 修改时间
     */
    private Date updateAt;
}
