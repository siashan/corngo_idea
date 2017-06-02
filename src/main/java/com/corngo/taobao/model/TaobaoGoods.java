package com.corngo.taobao.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 淘宝联盟商品表
 * </p>
 *
 * @author hanfc
 * @since 2017-06-02
 */
@TableName("TAOBAO_GOODS")
public class TaobaoGoods extends Model<TaobaoGoods> {

    private static final long serialVersionUID = 1L;

    /**
     * id
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
     * 结束时间
     */
	@TableField("END_TIME")
	private Date endTime;
    /**
     * 原价
     */
	@TableField("ORI_PRICE")
	private Double oriPrice;
    /**
     * 优惠券价值
     */
	@TableField("RED_PRICE")
	private Integer redPrice;
    /**
     * 折后价
     */
	@TableField("REAL_PRICE")
	private Double realPrice;
    /**
     * 商品链接
     */
	@TableField("GOODS_URL")
	private String goodsUrl;
    /**
     * 优惠券链接
     */
	@TableField("RED_URL")
	private String redUrl;
    /**
     * 平台   1 淘宝  2 天猫
     */
	@TableField("PLATE_TYPE")
	private String plateType;
    /**
     * 图片地址
     */
	@TableField("IMG_URL")
	private String imgUrl;
    /**
     * 状态
     */
	@TableField("STATUS")
	private String status;
    /**
     * 商品类型
     */
	@TableField("TYPE")
	private String type;


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

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Double getOriPrice() {
		return oriPrice;
	}

	public void setOriPrice(Double oriPrice) {
		this.oriPrice = oriPrice;
	}

	public Integer getRedPrice() {
		return redPrice;
	}

	public void setRedPrice(Integer redPrice) {
		this.redPrice = redPrice;
	}

	public Double getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
	}

	public String getGoodsUrl() {
		return goodsUrl;
	}

	public void setGoodsUrl(String goodsUrl) {
		this.goodsUrl = goodsUrl;
	}

	public String getRedUrl() {
		return redUrl;
	}

	public void setRedUrl(String redUrl) {
		this.redUrl = redUrl;
	}

	public String getPlateType() {
		return plateType;
	}

	public void setPlateType(String plateType) {
		this.plateType = plateType;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
