package com.alibaba.benchmark;

import com.alibaba.boot.hsf.annotation.HSFProvider;
import com.alibaba.edas.PointService;
import com.alibaba.edas.PrizeService;
import com.alibaba.edas.TaskService;
import com.alibaba.edas.UserService;
import com.alibaba.edas.generator.*;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;


@Service
public class InviteAndDrawActivityImpl implements InviteAndDrawActivity {

    private long point = 10;

    private int needTaskNum = 3;

    private int needInviteNum = 3;

    @Resource
    private TaskService taskService;

    @Resource
    private PrizeService prizeService;

    @Resource
    private PointService pointService;

    @Resource
    private UserService userService;

    @Override
    public String completeTask(long userId, long taskId) {
        Userinfo userinfo = userService.selectUserinfoById(userId);
        if(taskId != 1) {
            Taskcontent taskcontent = taskService.selectByTaskId(taskId);

            if (userinfo.equals(null) || taskcontent.equals(null)) {
                return "用户或任务不存在!";
            } else {
                long current = System.currentTimeMillis();//当前时间毫秒数
                long zero = current - (System.currentTimeMillis() + TimeZone.getDefault().getRawOffset()) % (1000 * 3600 * 24);

                Taskdetail taskdetail = new Taskdetail();

                Date date = new Date(System.currentTimeMillis());
                long activityId = taskcontent.getActivityId();

                taskdetail.setUserId(userId);
                taskdetail.setTaskId(taskId);
                taskdetail.setGmtCreate(date);
                taskdetail.setAcitivityId(activityId);

                List<Taskdetail> taskDetails = taskService.selectTaskDetailByUserId(userId);
                for (int i = 0; i < taskDetails.size(); i++) {

                    if (taskDetails.get(i).getGmtCreate().after(new Timestamp(zero)) && taskDetails.get(i).getTaskId() == taskId) {
                        return "每日只能完成任务一次!";
                    }
                }
                taskdetail.setDetailContent(userId + "完成任务" + taskId);
                taskService.insertTaskDetail(taskdetail);
                return "任务成功完成!";
            }
        }
        return "异常任务";
    }

    @Override
    public List<Taskdetail> searchTask(long userId, long activityId) {
        Userinfo userinfo = userService.selectUserinfoById(userId);
        List<Taskdetail> taskDetails = taskService.selectTaskDetailByActivityId(activityId);
        List<Taskdetail> result = new ArrayList<>();
        if(userinfo.equals(null) || taskDetails.equals(null)){
            System.out.println("用户或活动不存在!");
            return null;
        }
        else{
            for(int i = 0; i < taskDetails.size(); i++){
                if(taskDetails.get(i).getUserId() == userId && taskDetails.get(i).getTaskId() != 1){
                    result.add(taskDetails.get(i));
                }
            }
            return result;
        }
    }

    @Override
    public String shareActivity(long userId1, long userId2, long activityId) {
        Userinfo userinfo1 = userService.selectUserinfoById(userId1);
        Userinfo userinfo2 = userService.selectUserinfoById(userId2);
        if(userinfo1.equals(null) || userinfo2.equals(null)) {
            return ("用户不存在!");
        }
        List<Taskdetail> taskDetails = taskService.selectTaskDetailByUserId(userId1);
        List<PrizeDetail> prizeDetails = prizeService.selectPrizeDetailById(userId1);
        List<Taskdetail> fitDetails = new ArrayList<>();
        Date date = new Date(System.currentTimeMillis());
        if (prizeDetails.size() > 0) {
            date = (Date) prizeDetails.get(0).getGmtCreate();
            for (int i = 0; i < prizeDetails.size(); i++) {
                if (prizeDetails.get(i).getActivityId() == activityId) {
                    if (date.after(prizeDetails.get(i).getGmtCreate())) {
                        date = (Date) prizeDetails.get(i).getGmtCreate();
                    }
                }
            }
            for(int i = 0; i < taskDetails.size(); i++){
                if(taskDetails.get(i).getAcitivityId() == activityId && taskDetails.get(i).getGmtCreate().after(date)){
                    fitDetails.add(taskDetails.get(i));
                }
            }
        }
        else {
            for (int i = 0; i < taskDetails.size(); i++) {
                if (taskDetails.get(i).getAcitivityId() == activityId) {
                    fitDetails.add(taskDetails.get(i));
                }
            }
        }
        if (fitDetails.size() < 3){
            return ("未达到分享要求，请先完成3个任务");
        }
        else{
            Pointdetail pointdetail = new Pointdetail();
            pointdetail.setUserId(userId1);
            pointdetail.setPointChange(point);
            Date dateNow =new Date(System.currentTimeMillis());
            pointdetail.setGmtCreate(dateNow);
            pointdetail.setGmtModify(dateNow);
            pointdetail.setPointId(pointService.selectByUserId(userId1,activityId).getId());
            pointdetail.setChangeReason(userId1 + "参与拉人活动，成功邀请" + userId2 +",获得积分数" + point);
            pointService.insertPointDetail(pointdetail);

            Taskdetail taskdetail = new Taskdetail();
            taskdetail.setDetailContent(String.valueOf(userId2));
            taskdetail.setAcitivityId(activityId);
            taskdetail.setGmtCreate(dateNow);
            taskdetail.setTaskId((long)1);
            taskdetail.setUserId(userId1);
            taskdetail.setGmtModify(dateNow);
            taskService.insertTaskDetail(taskdetail);

            return userId1 + "参与拉人活动，成功邀请" + userId2;
        }
    }

    @Override
    public PrizeDetail luckDraw(long userId, long activityId) {
        if(pointService.selectByUserId(userId,activityId).getPointNumber() >= needInviteNum * 10) {
            PrizeContent prizeContent = prizeService.luckDraw();
            prizeContent.setPrizeNum(prizeContent.getPrizeNum() - 1);
            PrizeDetail prizeDetail = new PrizeDetail();
            prizeDetail.setActivityId(activityId);
            Date date = new Date(System.currentTimeMillis());
            prizeDetail.setGmtCreate(date);
            prizeDetail.setPrizeId(prizeContent.getPrizeId());
            prizeDetail.setUserId(userId);
            prizeDetail.setDetailContent(userId + "获得奖品" + prizeContent.getPrizeType() + "金额为" + prizeContent.getPrizeMoney());
            prizeService.updatePrizeContent(prizeContent);
            prizeService.insertPrizeDetail(prizeDetail);

            Pointdetail pointdetail = new Pointdetail();
            pointdetail.setGmtCreate(date);
            pointdetail.setChangeReason("");
            pointdetail.setGmtModify(date);
            pointdetail.setPointId(pointService.selectByUserId(userId, activityId).getId());
            pointdetail.setPointChange(point * needInviteNum);
            pointdetail.setUserId(userId);
            pointService.insertPointDetail(pointdetail);

            return prizeDetail;
        }
        else{
            return null;
        }

    }

    @Override
    public void initPoint(long userId, long activityId) {

        if (null != pointService.selectByUserId(userId,activityId)) return;
        else {
            Pointaccount pointaccount = new Pointaccount();
            pointaccount.setPointNumber((long) 0);
            Date date = new Date(System.currentTimeMillis());
            pointaccount.setGmtCreate(date);
            pointaccount.setGmtModify(date);
            pointaccount.setUserId(userId);
            pointaccount.setPointActivity(activityId);
            System.out.println(pointaccount.toString());

            pointService.insertPointAccount(pointaccount);
        }
    }

    @Override
    public List<Taskcontent> showTaskList(long activityId) {
        List<Taskcontent> taskContents =  taskService.selectByActivityId(activityId);
        for(int i = 0; i < taskContents.size(); i++) {
            System.out.println(taskContents.get(i).toString());
        }
        return taskService.selectByActivityId(activityId);
    }

    @Override
    public List<Taskdetail> searchInviteDetail(long userId, long activityId) {
        List<Taskdetail> taskdetails = taskService.selectTaskDetailByUserId(userId);
        List<Taskdetail> result = new ArrayList<>();
        for(int i=0;i<taskdetails.size();i++){
            if(taskdetails.get(i).getTaskId() == 1){
                result.add(taskdetails.get(i));
            }
        }
        return result;
    }
}
