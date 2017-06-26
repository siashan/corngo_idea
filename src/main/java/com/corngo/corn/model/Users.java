package com.corngo.corn.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author hanfc
 * @since 2017-06-26
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
