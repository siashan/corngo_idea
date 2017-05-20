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
@TableName("SYS_ROLE_MENU")
public class SysRoleMenu implements Serializable {

	@TableField(exist = false)
	private static final long serialVersionUID = 1L;

	/** 主键 自增 */
	@TableId
	private Long id;

	/** 角色id */
	@TableField(value = "ROLE_ID")
	private Long roleId;

	/** 菜单id */
	@TableField(value = "MENU_ID")
	private Long menuId;


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

	public Long getMenuId() {
		return this.menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

}
