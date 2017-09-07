package com.corngo.corn.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author hanfc
 * @since 2017-09-07
 */
public class Users extends Model<Users> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("ID")
	private Long id;
    /**
     * 手机号
     */
	@TableField("MOBILE")
	private String mobile;
    /**
     * 微信号
     */
	@TableField("OPEN_ID")
	private String openId;
    /**
     * 密码
     */
	@TableField("PASSWORD")
	private String password;
    /**
     * 加密盐
     */
	@TableField("SALT")
	private String salt;
    /**
     * 用户名
     */
	@TableField("NAME")
	private String name;
    /**
     * 创建时间
     */
	@TableField("CREATE_TIME")
	private Date createTime;
    /**
     * 推荐人id
     */
	@TableField("RECOMMEND_USER_ID")
	private Long recommendUserId;
    /**
     * 推荐时间
     */
	@TableField("RECOMMEND_TIME")
	private Date recommendTime;
    /**
     * 性别
     */
	@TableField("GENDER")
	private String gender;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
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

	public Long getRecommendUserId() {
		return recommendUserId;
	}

	public void setRecommendUserId(Long recommendUserId) {
		this.recommendUserId = recommendUserId;
	}

	public Date getRecommendTime() {
		return recommendTime;
	}

	public void setRecommendTime(Date recommendTime) {
		this.recommendTime = recommendTime;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
