package com.corngo.admin.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.corngo.base.support.PUBConstants;

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
@TableName("SYS_USER")
public class SysUser extends Model<SysUser> {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键 自增
	 */
	@TableId("ID")
	private Long id;
	/**
	 * 真实姓名
	 */
	@TableField("REAL_NAME")
	private String realName;
	/**
	 * 加密盐
	 */
	@TableField("SALT")
	private String salt;
	/**
	 * 性别
	 */
	@TableField("GENDER")
	private String gender;
	/**
	 * 登录密码
	 */
	@TableField("PASSWORD")
	private String password;
	/**
	 * 联系电话
	 */
	@TableField("PHONE_NO")
	private String phoneNo;
	/**
	 * 邮箱
	 */
	@TableField("EMAIL")
	private String email;
	/**
	 * 创建日期
	 */
	@JSONField(format = PUBConstants.DEFAULT_FORMAT_DATE_JDK)
	@TableField("CREATE_TIME")
	private Date createTime;
	/**
	 * 最近登录时间
	 */
	@JSONField(format = PUBConstants.DEFAULT_FORMAT_DATE_JDK)
	@TableField("LAST_LOGIN_DATE")
	private Date lastLoginDate;
	/**
	 * 用户名
	 */
	@TableField("USER_NAME")
	private String userName;
	/**
	 * 微信号
	 */
	@TableField("WECHAT_NO")
	private String wechatNo;
	/**
	 * openID
	 */
	@TableField("OPEN_ID")
	private String openId;
	/**
	 * 微信关注状态
	 */
	@TableField("WECHAT_ATTENTION_STATUS")
	private String wechatAttentionStatus;
	/**
	 * 所属部门id
	 */
	@TableField("DEPT_ID")
	private Long deptId;
	@TableField("EXT1")
	private String ext1;
	@TableField("EXT2")
	private String ext2;
	@TableField("EXT3")
	private String ext3;
	@TableField("STATUS")
	private String status;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getWechatNo() {
		return wechatNo;
	}

	public void setWechatNo(String wechatNo) {
		this.wechatNo = wechatNo;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getWechatAttentionStatus() {
		return wechatAttentionStatus;
	}

	public void setWechatAttentionStatus(String wechatAttentionStatus) {
		this.wechatAttentionStatus = wechatAttentionStatus;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public String getExt3() {
		return ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}