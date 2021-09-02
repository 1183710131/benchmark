package com.alibaba.benchmark;

import com.alibaba.edas.PointService;
import com.alibaba.edas.PrizeService;
import com.alibaba.edas.TaskService;
import com.alibaba.edas.UserService;
import com.alibaba.edas.generator.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;


@RestController
public class ActivityController {
    @Resource
    private UserService userService;

    @Resource
    private PointService pointService;

    @Resource
    private TaskService taskService;

    @Resource
    private PrizeService prizeService;

    @Resource
    private InviteAndDrawActivity inviteAndDrawActivity;

    private int needTaskNum = 3;

    private int needInviteNum = 3;

    @RequestMapping(value = "/hsf-activity/init")
    public ResultDTO<Map<String,Object>> activityInit(@RequestParam Long userId, @RequestParam Long activityId) {
        ResultDTO<Map<String,Object>> ret = new ResultDTO<>();
        Map<String,Object> data = new HashMap<>();

        Userinfo userinfo = userService.selectUserinfoById(userId);

        inviteAndDrawActivity.initPoint(userId, activityId);
        System.out.println("欢迎用户 " + userinfo.getUserId() + userinfo.getUserName() + userinfo.getUserAvatar());
        System.out.println("您的积分数量为:" + pointService.selectByUserId(userId, activityId).getPointNumber());
        data.put("userInfo",userinfo);
        data.put("pointInfo",pointService.selectByUserId(userId, activityId).getPointNumber());
        data.put("prizeList",prizeService.getAllPrize());
        int completedTaskNum = inviteAndDrawActivity.searchTask(userId,activityId).size();
        data.put("taskProcess",inviteAndDrawActivity.searchTask(userId,activityId));
        data.put("taskWarn","您还需完成"+ (needTaskNum - completedTaskNum) + "个任务,才能获得活动资格");
        data.put("inviteProcess",inviteAndDrawActivity.searchInviteDetail(userId,activityId));
        ret.setResult(data);
        return ret;
    }

    @RequestMapping(value = "/hsf-activity/queryTask")
    public ResultDTO<Map<String,Object>> queryTask(@RequestParam long userId, @RequestParam long activityId){
        ResultDTO<Map<String,Object>> ret = new ResultDTO<>();
        Map<String,Object> data = new HashMap<>();

        Userinfo userinfo = userService.selectUserinfoById(userId);

        inviteAndDrawActivity.showTaskList(activityId);
        int completedTaskNum = inviteAndDrawActivity.searchTask(userId, activityId).size();
        if (completedTaskNum < needTaskNum) {
            data.put("taskWarn","您还需完成"+ (needTaskNum - completedTaskNum) + "个任务,才能获得活动资格");
        }
        else{
            data.put("taskWarn","已达到目标任务数，成功获得活动资格!");
        }
        //System.out.println("请输入任务id选择完成任务");

        data.put("userInfo",userinfo);
        data.put("pointInfo",pointService.selectByUserId(userId, activityId).getPointNumber());
        data.put("prizeList",prizeService.getAllPrize());

        data.put("taskProcess",inviteAndDrawActivity.searchTask(userId,activityId));
        //data.put("taskWarn","您还需完成"+ (needTaskNum - completedTaskNum) + "个任务,才能获得活动资格");
        data.put("inviteProcess",inviteAndDrawActivity.searchInviteDetail(userId,activityId));
        ret.setResult(data);
        return ret;
    }

    @RequestMapping(value = "/hsf-activity/completeTask")
    public ResultDTO<Map<String,Object>> completeTask(@RequestParam long userId, @RequestParam long activityId, @RequestParam long taskId){
        ResultDTO<Map<String,Object>> ret = new ResultDTO<>();
        Map<String,Object> data = new HashMap<>();

        Userinfo userinfo = userService.selectUserinfoById(userId);


        data.put("userInfo",userinfo);
        data.put("pointInfo",pointService.selectByUserId(userId, activityId).getPointNumber());
        data.put("prizeList",prizeService.getAllPrize());

        data.put("taskProcess",inviteAndDrawActivity.searchTask(userId,activityId));
        data.put("taskWarn",inviteAndDrawActivity.completeTask(userId,taskId));
        data.put("inviteProcess",inviteAndDrawActivity.searchInviteDetail(userId,activityId));
        ret.setResult(data);
        return ret;
    }

    @RequestMapping(value = "/hsf-activity/queryInvite")
    public ResultDTO<Map<String,Object>> inviteOthers(@RequestParam long userId, @RequestParam long activityId){
        ResultDTO<Map<String,Object>> ret = new ResultDTO<>();
        Map<String,Object> data = new HashMap<>();

        Userinfo userinfo = userService.selectUserinfoById(userId);

        if(pointService.selectByUserId(userId,activityId).getPointNumber() >= needInviteNum * 10){
            data.put("shareWarn","恭喜您:您已完成助力活动，可以开启抽奖");
        }
        else{
            long point = pointService.selectByUserId(userId,activityId).getPointNumber();
            data.put("shareWarn","您只需邀请"+ (needInviteNum - (point / 10)) + "个玩家,就能获得活动资格");
        }
        data.put("userInfo",userinfo);
        data.put("pointInfo",pointService.selectByUserId(userId, activityId).getPointNumber());
        data.put("prizeList",prizeService.getAllPrize());

        data.put("taskProcess",inviteAndDrawActivity.searchTask(userId,activityId));

        data.put("inviteProcess",inviteAndDrawActivity.searchInviteDetail(userId,activityId));
        ret.setResult(data);
        return ret;
    }

    @RequestMapping(value = "/hsf-activity/share")
    public ResultDTO<Map<String,Object>> share(@RequestParam long userId1, @RequestParam long userId2, @RequestParam long activityId){
        ResultDTO<Map<String,Object>> ret = new ResultDTO<>();
        Map<String,Object> data = new HashMap<>();

        Userinfo userinfo = userService.selectUserinfoById(userId1);

        data.put("userInfo",userinfo);
        data.put("pointInfo",pointService.selectByUserId(userId1, activityId).getPointNumber());
        data.put("prizeList",prizeService.getAllPrize());

        data.put("taskProcess",inviteAndDrawActivity.searchTask(userId1,activityId));
        data.put("inviteWarn",inviteAndDrawActivity.shareActivity(userId1,userId2,activityId));
        data.put("inviteProcess",inviteAndDrawActivity.searchInviteDetail(userId1,activityId));
        ret.setResult(data);
        return ret;
    }

    @RequestMapping(value = "/hsf-activity/draw")
    public ResultDTO<Map<String,Object>> luckDraw(@RequestParam long userId,@RequestParam long activityId){
        ResultDTO<Map<String,Object>> ret = new ResultDTO<>();
        Map<String,Object> data = new HashMap<>();

        Userinfo userinfo = userService.selectUserinfoById(userId);

        PrizeDetail prizeDetail =  inviteAndDrawActivity.luckDraw(userId, activityId);

        data.put("userInfo",userinfo);
        data.put("pointInfo",pointService.selectByUserId(userId, activityId).getPointNumber());
        data.put("prizeList",prizeService.getAllPrize());
        data.put("taskProcess",inviteAndDrawActivity.searchTask(userId,activityId));
        data.put("inviteProcess",inviteAndDrawActivity.searchInviteDetail(userId,activityId));
        if(null != prizeDetail) {
            data.put("prizeWarn","成功获得奖品!");
            data.put("prizeDetail",prizeDetail);
        }
        else{
            data.put("prizeWarn","抱歉，您未获得抽奖资格!");
        }
        ret.setResult(data);
        return ret;
    }
    

}
