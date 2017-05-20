package com.corngo.admin.model;

import java.io.Serializable;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 *
 * null
 *
 */
@TableName("SYS_DICT")
public class SysDict implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** id */
	@TableId
	private Long id;

	/** 组别 */
	@TableField(value = "DICT_GROUP")
	private String dictGroup;

	/** code值 */
	@TableField(value = "DICT_CODE")
	private String dictCode;

	/** 名称 */
	@TableField(value = "DICT_NAME")
	private String dictName;

	/** 描述 */
	@TableField(value = "DICT_DESP")
	private String dictDesp;

	/** 排序 */
	@TableField(value = "ORDER_BY")
	private Integer orderBy;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDictGroup() {
		return this.dictGroup;
	}

	public void setDictGroup(String dictGroup) {
		this.dictGroup = dictGroup;
	}

	public String getDictCode() {
		return this.dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getDictName() {
		return this.dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDictDesp() {
		return this.dictDesp;
	}

	public void setDictDesp(String dictDesp) {
		this.dictDesp = dictDesp;
	}

	public Integer getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

}
