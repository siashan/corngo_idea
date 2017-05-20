package com.corngo.admin.model;

import java.io.Serializable;
import java.util.Date;


import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.corngo.base.support.PUBConstants;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * ${table.comment}
 * </p>
 *
 * @author hanfc
 * @since 2017-05-04
 */
@JSONType(ignores = "")
@TableName("SYS_ROLE")
public class SysRole extends Model<SysRole> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键 自增
	 */
	@TableId("ID")
	private Long id;
	/**
	 * 角色名称
	 */
	@TableField("NAME")
	private String name;
	/**
	 * 创建时间
	 */
	@JSONField(format = PUBConstants.DEFAULT_FORMAT_DATE_JDK)
	@TableField("CREATE_TIME")
	private Date createTime;
	/**
	 * 备注
	 */
	@TableField("REMARK")
	private String remark;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
