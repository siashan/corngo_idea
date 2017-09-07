package com.corngo.corn.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 标的
 * </p>
 *
 * @author hanfc
 * @since 2017-06-27
 */
public class Bid extends Model<Bid> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("ID")
	private Long id;
    /**
     * 创建时间
     */
	@TableField("CREATE_TIME")
	private Date createTime;
    /**
     * 创建人
     */
	@TableField("USER_ID")
	private Long userId;
    /**
     * 商品id
     */
	@TableField("GOODS_ID")
	private Long goodsId;
    /**
     * 开始时间
     */
	@TableField("START_TIME")
	private Date startTime;
    /**
     * 结束时间
     */
	@TableField("END_TIME")
	private Date endTime;
    /**
     * 状态
     */
	@TableField("STATUS")
	private String status;
    /**
     * 标的编号
     */
	@TableField("BID_NO")
	private String bidNo;
    /**
     * 版本号
     */
	@TableField("VERSION")
	private Integer version;
    /**
     * 满标时间
     */
	@TableField("OVER_TIME")
	private Date overTime;
    /**
     * 总金额
     */
	@TableField("AMOUNT")
	private Double amount;
    /**
     * 已投金额
     */
	@TableField("RECEIVE_AMOUNT")
	private Double receiveAmount;
    /**
     * 标题
     */
	@TableField("TITLE")
	private String title;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBidNo() {
		return bidNo;
	}

	public void setBidNo(String bidNo) {
		this.bidNo = bidNo;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getOverTime() {
		return overTime;
	}

	public void setOverTime(Date overTime) {
		this.overTime = overTime;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getReceiveAmount() {
		return receiveAmount;
	}

	public void setReceiveAmount(Double receiveAmount) {
		this.receiveAmount = receiveAmount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
