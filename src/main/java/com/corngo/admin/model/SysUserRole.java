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
@TableName("SYS_USER_ROLE")
public class SysUserRole implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键 自增 */
	@TableId
	private Long id;

	/** 角色id */
	@TableField(value = "ROLE_ID")
	private Long roleId;

	/** 用户id */
	@TableField(value = "USER_ID")
	private Long userId;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getRoleId() {
		return this.roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getUserId() {
		return this.userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
