package com.alibaba.edas.generator;

import java.io.Serializable;
import java.util.Date;

/**
 * prize_content
 * @author 
 */
public class PrizeContent implements Serializable {
    /**
     * 奖品id
     */
    private Long prizeId;

    /**
     * 奖品类型
     */
    private Long prizeType;

    /**
     * 奖品创建时间
     */
    private Date gmtCreate;

    /**
     * 奖品数量
     */
    private Long prizeNum;

    /**
     * 所属活动id
     */
    private Long activityId;

    /**
     * 奖品金额
     */
    private Long prizeMoney;

    private static final long serialVersionUID = 1L;

    public Long getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(Long prizeId) {
        this.prizeId = prizeId;
    }

    public Long getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(Long prizeType) {
        this.prizeType = prizeType;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getPrizeNum() {
        return prizeNum;
    }

    public void setPrizeNum(Long prizeNum) {
        this.prizeNum = prizeNum;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getPrizeMoney() {
        return prizeMoney;
    }

    public void setPrizeMoney(Long prizeMoney) {
        this.prizeMoney = prizeMoney;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PrizeContent other = (PrizeContent) that;
        return (this.getPrizeId() == null ? other.getPrizeId() == null : this.getPrizeId().equals(other.getPrizeId()))
            && (this.getPrizeType() == null ? other.getPrizeType() == null : this.getPrizeType().equals(other.getPrizeType()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getPrizeNum() == null ? other.getPrizeNum() == null : this.getPrizeNum().equals(other.getPrizeNum()))
            && (this.getActivityId() == null ? other.getActivityId() == null : this.getActivityId().equals(other.getActivityId()))
            && (this.getPrizeMoney() == null ? other.getPrizeMoney() == null : this.getPrizeMoney().equals(other.getPrizeMoney()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPrizeId() == null) ? 0 : getPrizeId().hashCode());
        result = prime * result + ((getPrizeType() == null) ? 0 : getPrizeType().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getPrizeNum() == null) ? 0 : getPrizeNum().hashCode());
        result = prime * result + ((getActivityId() == null) ? 0 : getActivityId().hashCode());
        result = prime * result + ((getPrizeMoney() == null) ? 0 : getPrizeMoney().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", prizeId=").append(prizeId);
        sb.append(", prizeType=").append(prizeType);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", prizeNum=").append(prizeNum);
        sb.append(", activityId=").append(activityId);
        sb.append(", prizeMoney=").append(prizeMoney);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}