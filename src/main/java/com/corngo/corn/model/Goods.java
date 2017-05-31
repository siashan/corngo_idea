package com.corngo.corn.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.corngo.base.support.PUBConstants;

import java.io.Serializable;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author hanfc
 * @since 2017-05-26
 */
@JSONType(ignores = "")
public class Goods extends Model<Goods> {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId("ID")
	private Long id;
    /**
     * 商品名称
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
     * 发布人
     */
	@TableField("USER_ID")
	private Long userId;
    /**
     * 价格
     */
	@TableField("PRICE")
	private Double price;
    /**
     * 类型
     */
	@TableField("TYPE")
	private String type;
    /**
     * 状态
     */
	@TableField("STATUS")
	private String status;
    /**
     * 图片存放地址
     */
	@TableField("IMG_URL")
	private String imgUrl;


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

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
