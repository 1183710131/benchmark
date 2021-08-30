package com.alibaba.edas;

import com.alibaba.edas.generator.*;

import java.util.List;

public interface TaskService {
    /**
     * 新增一条任务内容
     * @param taskcontent
     * @return
     */
    boolean insertTaskContent(Taskcontent taskcontent);

    /**
     * 删除一条任务内容
     * @param taskId
     * @return
     */
    boolean deleteTaskContent(long taskId );

    /**
     * 修改任务内容
     * @param taskcontent
     * @return
     */
    boolean updateTaskContent(Taskcontent taskcontent);

    /**
     * 查找任务 by activityId
     * @param activityId
     * @return
     */
    List<Taskcontent> selectByActivityId(long activityId);

    /**
     * 查找任务 by taskId
     * @param taskId
     * @return
     */
    Taskcontent selectByTaskId(long taskId);

    /**
     * 新增一条任务流水记录
     * @param taskdetail
     * @return
     */
    boolean insertTaskDetail(Taskdetail taskdetail);

    /**
     * 查找任务详情 by UserId
     * @param userId
     * @return
     */
    List<Taskdetail> selectTaskDetailByUserId(long userId);

    /**
     * 查找任务详情 by activityId
     * @param activityId
     * @return
     */
    List<Taskdetail> selectTaskDetailByActivityId(long activityId);

}
