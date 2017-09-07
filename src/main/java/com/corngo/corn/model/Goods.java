package com.corngo.corn.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author hanfc
 * @since 2017-06-27
 */
public class Goods extends Model<Goods> {

    private static final long serialVersionUID = 1L;

    /**
     * 是否能够发布商品 （0 能够  1 已有正在筹资的商品不能发布）
     */
	@TableField("PUBLISH_STATUS")
	private String publishStatus;
    /**
     * 最新一期标的编号
     */
	@TableField("LAST_BID_NO")
	private Integer lastBidNo;
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
     * 商品详细
     */
	@TableField("CONTENT")
	private String content;
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


	public String getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(String publishStatus) {
		this.publishStatus = publishStatus;
	}

	public Integer getLastBidNo() {
		return lastBidNo;
	}

	public void setLastBidNo(Integer lastBidNo) {
		this.lastBidNo = lastBidNo;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
