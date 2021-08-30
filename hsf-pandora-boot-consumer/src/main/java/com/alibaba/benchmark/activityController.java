package com.alibaba.benchmark;

import com.alibaba.edas.PointService;
import com.alibaba.edas.PrizeService;
import com.alibaba.edas.TaskService;
import com.alibaba.edas.UserService;
import com.alibaba.edas.inviteAndDrawActivity;
import com.alibaba.edas.generator.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;


@RestController
public class activityController {
    @Resource
    private UserService userService;

    @Resource
    private PointService pointService;

    @Resource
    private TaskService taskService;

    @Resource
    private PrizeService prizeService;

    @Resource
    private inviteAndDrawActivity inviteAndDrawActivity;

    private int needTaskNum = 3;

    private int needInviteNum = 3;

    @RequestMapping(value="/hsf-activity/draw")
    public void drawActivityInit(){
        long activityId = 1;
        System.out.println("请输入用户id");
        long userId = -1;
        Scanner sc = new Scanner(System.in);
        try {
            userId = Long.parseLong(sc.nextLine());
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        Userinfo userinfo = userService.selectUserinfoById(userId);
        System.out.println("输入1进入互动玩法");
        sc = new Scanner(System.in);
        System.out.println(userinfo.toString());
        if(Integer.parseInt(sc.nextLine()) == 1) {
            inviteAndDrawActivity.initPoint(userId, activityId);
            System.out.println("欢迎用户 "+userinfo.getUserId()+userinfo.getUserName()+userinfo.getUserAvatar());
            System.out.println("您的积分数量为:"+pointService.selectByUserId(userId,activityId).getPointNumber());
            doTask(userId,activityId);
            inviteOthers(userId,activityId);
            inviteAndDrawActivity.luckDraw(userId,activityId);
        }
        else{
            System.out.println("请输入有效值!");
        }

    }

    public void doTask(long userId, long activityId){

        while(true){
            inviteAndDrawActivity.showTaskList(activityId);
            int completedTaskNum = inviteAndDrawActivity.searchTask(userId,activityId).size();
            System.out.println("您还需完成"+ (needTaskNum - completedTaskNum) + "个任务,才能获得活动资格");
            System.out.println("请输入任务id选择完成任务");
            Scanner sc = new Scanner(System.in);
            inviteAndDrawActivity.completeTask(userId,Long.parseLong(sc.nextLine()));

            if(completedTaskNum == needTaskNum) break;
        }
        System.out.println("已达到目标任务数，成功获得活动资格!");
    }

    public void inviteOthers(long userId, long activityId){
        while(true){
            long point = pointService.selectByUserId(userId,activityId).getPointNumber();
            System.out.println("您只需邀请"+ (needInviteNum - (point / 10)) + "个玩家,就能获得活动资格");
            System.out.println("请输入用户信息选择分享");
            Scanner sc = new Scanner(System.in);
            inviteAndDrawActivity.shareActivity(userId,Long.parseLong(sc.nextLine()),activityId);

            if(point == needInviteNum * 10) break;
        }
        System.out.println("恭喜您:您已完成助力活动，可以开启抽奖");
    }
}
