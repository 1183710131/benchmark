package com.alibaba.edas.generator;

import java.io.Serializable;
import java.util.Date;

/**
 * prize_detail
 * @author 
 */
public class PrizeDetail implements Serializable {
    /**
     * 奖品流水id
     */
    private Long detailId;

    /**
     * 奖品流水详情
     */
    private String detailContent;

    /**
     * 奖品发放时间
     */
    private Date gmtCreate;

    /**
     * 奖品id
     */
    private Long prizeId;

    /**
     * 获得奖品的用户id
     */
    private Long userId;

    private Long activityId;

    private static final long serialVersionUID = 1L;

    public Long getDetailId() {
        return detailId;
    }

    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }

    public String getDetailContent() {
        return detailContent;
    }

    public void setDetailContent(String detailContent) {
        this.detailContent = detailContent;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(Long prizeId) {
        this.prizeId = prizeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
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
        PrizeDetail other = (PrizeDetail) that;
        return (this.getDetailId() == null ? other.getDetailId() == null : this.getDetailId().equals(other.getDetailId()))
            && (this.getDetailContent() == null ? other.getDetailContent() == null : this.getDetailContent().equals(other.getDetailContent()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getPrizeId() == null ? other.getPrizeId() == null : this.getPrizeId().equals(other.getPrizeId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getActivityId() == null ? other.getActivityId() == null : this.getActivityId().equals(other.getActivityId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDetailId() == null) ? 0 : getDetailId().hashCode());
        result = prime * result + ((getDetailContent() == null) ? 0 : getDetailContent().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getPrizeId() == null) ? 0 : getPrizeId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getActivityId() == null) ? 0 : getActivityId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", detailId=").append(detailId);
        sb.append(", detailContent=").append(detailContent);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", prizeId=").append(prizeId);
        sb.append(", userId=").append(userId);
        sb.append(", activityId=").append(activityId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}