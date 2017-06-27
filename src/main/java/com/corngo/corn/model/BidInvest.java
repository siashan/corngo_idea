package com.corngo.corn.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 标的投资记录
 * </p>
 *
 * @author hanfc
 * @since 2017-06-27
 */
@TableName("BID_INVEST")
public class BidInvest extends Model<BidInvest> {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId("ID")
	private Long id;
    /**
     * 标的id
     */
	@TableField("BID_ID")
	private Long bidId;
    /**
     * 用户id
     */
	@TableField("USER_ID")
	private Long userId;
    /**
     * 投资金额
     */
	@TableField("MONEY")
	private Double money;
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

	public Long getBidId() {
		return bidId;
	}

	public void setBidId(Long bidId) {
		this.bidId = bidId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
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
