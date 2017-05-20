package com.corngo.admin.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;

import java.util.ArrayList;
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
 * @since 2017-05-11
 */
@TableName("SYS_MENU")
public class SysMenu extends Model<SysMenu> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("ID")
	private Long id;
    /**
     * 菜单名称
     */
	@TableField("NAME")
	private String name;
    /**
     * 菜单类型
     */
	@TableField("TYPE")
	private String type;
    /**
     * 菜单url
     */
	@TableField("URL")
	private String url;
    /**
     * 图标
     */
	@TableField("ICON")
	private String icon;
    /**
     * 权限
     */
	@TableField("PERMISSIONS")
	private String permissions;
    /**
     * 父id
     */
	@TableField("PARENT_ID")
	private Long parentId;
    /**
     * 备注
     */
	@TableField("REMARK")
	private String remark;
    /**
     * 创建时间
     */
	@TableField("CREATE_TIME")
	private Date createTime;
    /**
     * 排序字段
     */
	@TableField("ORDER_BY")
	private Integer orderBy;

	@TableField(exist = false)
	private ArrayList<SysMenu> children;

	public ArrayList<SysMenu> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<SysMenu> children) {
		this.children = children;
	}
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(Integer orderBy) {
		this.orderBy = orderBy;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
