package com.alibaba.benchmark;

import com.alibaba.edas.generator.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


public interface InviteAndDrawActivity {

    /**
     * 调用任务服务，完成成功则增加一条任务流水，失败则打印原因。
     * @param userId
     * @param taskId
     * @return
     */
    String completeTask(long userId,long taskId);

    /**
     * 调用任务服务 ,查询某个用户在某个活动中做了几次任务并返回活动流水详情
     * @param userId
     * @param activityId
     * @return
     */
    List<Taskdetail> searchTask(long userId, long activityId);

    /**
     * 调用积分服务，user1分享给user2,完成分享活动，user1获得积分，返回分享成功与否
     * @param userId1
     * @param userId2
     * @param activityId
     * @return
     */
    String shareActivity(long userId1, long userId2, long activityId);

    /**
     * 调用奖品服务和积分服务，随机函数进行抽奖，增加一条奖品流水，扣减用户相应积分
     * @param userId
     * @param activityId
     * @return
     */
    PrizeDetail luckDraw(long userId, long activityId);

    /**
     * 初始化用户某活动的的积分账户
     * @param userId
     * @param activityId
     */
    void initPoint(long userId,long activityId);

    /**
     * 显示某个活动的所有任务
     * @param activityId
     * @return
     */
    List<Taskcontent> showTaskList(long activityId);


    List<Taskdetail> searchInviteDetail(long userId, long activityId);
}
