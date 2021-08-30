package com.alibaba.edas.generator;

import java.io.Serializable;
import java.util.Date;

/**
 * taskdetail
 * @author 
 */
public class Taskdetail implements Serializable {
    /**
     * 任务流水id
     */
    private Long detailId;

    /**
     * 完成任务内容
     */
    private String detailContent;

    /**
     * 任务流水形成时间
     */
    private Date gmtCreate;

    /**
     * 任务流水修改时间
     */
    private Date gmtModify;

    private Long taskId;

    private Long userId;

    /**
     * 所属活动id
     */
    private Long acitivityId;

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

    public Date getGmtModify() {
        return gmtModify;
    }

    public void setGmtModify(Date gmtModify) {
        this.gmtModify = gmtModify;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAcitivityId() {
        return acitivityId;
    }

    public void setAcitivityId(Long acitivityId) {
        this.acitivityId = acitivityId;
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
        Taskdetail other = (Taskdetail) that;
        return (this.getDetailId() == null ? other.getDetailId() == null : this.getDetailId().equals(other.getDetailId()))
            && (this.getDetailContent() == null ? other.getDetailContent() == null : this.getDetailContent().equals(other.getDetailContent()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getGmtModify() == null ? other.getGmtModify() == null : this.getGmtModify().equals(other.getGmtModify()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getAcitivityId() == null ? other.getAcitivityId() == null : this.getAcitivityId().equals(other.getAcitivityId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getDetailId() == null) ? 0 : getDetailId().hashCode());
        result = prime * result + ((getDetailContent() == null) ? 0 : getDetailContent().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getGmtModify() == null) ? 0 : getGmtModify().hashCode());
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getAcitivityId() == null) ? 0 : getAcitivityId().hashCode());
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
        sb.append(", gmtModify=").append(gmtModify);
        sb.append(", taskId=").append(taskId);
        sb.append(", userId=").append(userId);
        sb.append(", acitivityId=").append(acitivityId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}