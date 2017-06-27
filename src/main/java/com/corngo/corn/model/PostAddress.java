package com.corngo.corn.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 邮寄地址
 * </p>
 *
 * @author hanfc
 * @since 2017-06-27
 */
@TableName("POST_ADDRESS")
public class PostAddress extends Model<PostAddress> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("ID")
	private Long id;
    /**
     * 用户id
     */
	@TableField("USER_ID")
	private Long userId;
    /**
     * 邮编
     */
	@TableField("ZIP_CODE")
	private String zipCode;
    /**
     * 邮寄地址
     */
	@TableField("ADDRESS")
	private String address;
    /**
     * 是否默认地址
     */
	@TableField("IS_DEFAULT")
	private String isDefault;
    /**
     * 收件人手机号
     */
	@TableField("MOBILE")
	private String mobile;
    /**
     * 收件人
     */
	@TableField("ADDRESSEE")
	private String addressee;
    /**
     * 创建时间
     */
	@TableField("CREATE_TIME")
	private Date createTime;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddressee() {
		return addressee;
	}

	public void setAddressee(String addressee) {
		this.addressee = addressee;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
