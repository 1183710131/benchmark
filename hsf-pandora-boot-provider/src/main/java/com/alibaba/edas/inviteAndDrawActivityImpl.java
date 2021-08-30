package com.alibaba.edas;

import com.alibaba.boot.hsf.annotation.HSFProvider;
import com.alibaba.edas.generator.*;


import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;


@HSFProvider(serviceInterface = inviteAndDrawActivity.class, serviceVersion = "1.0.0")
public class inviteAndDrawActivityImpl implements inviteAndDrawActivity{

    private long point = 10;

    @Resource
    private UserinfoDao userinfoDao;

    @Resource
    private TaskcontentDao taskcontentDao;

    @Resource
    private TaskdetailDao taskdetailDao;

    @Resource
    private PrizeContentDao prizeContentDao;

    @Resource
    private PrizeDetailDao prizeDetailDao;

    @Resource
    private PointaccountDao pointaccountDao;

    @Resource
    private PointdetailDao pointdetailDao;

    @Resource
    private TaskService taskService;

    @Resource
    private PrizeService prizeService;

    @Resource
    private PointService pointService;

    @Override
    public String completeTask(long userId, long taskId) {
        Userinfo userinfo =  userinfoDao.selectByPrimaryKey(userId);
        Taskcontent taskcontent = taskcontentDao.selectByPrimaryKey(taskId);
        if(userinfo.equals(null) || taskcontent.equals(null)){
            return "用户或任务不存在!";
        }
        else{
            long current=System.currentTimeMillis();//当前时间毫秒数
            long zero = current - ( System.currentTimeMillis()+ TimeZone.getDefault().getRawOffset()) % (1000 * 3600 * 24);

            Taskdetail taskdetail = new Taskdetail();

            Date date =new Date(System.currentTimeMillis());
            long activityId =  taskcontent.getActivityId();

            taskdetail.setUserId(userId);
            taskdetail.setTaskId(taskId);
            taskdetail.setGmtCreate(date);
            taskdetail.setAcitivityId(activityId);

            List<Taskdetail> taskDetails = taskService.selectTaskDetailByUserId(userId);
            for(int i = 0; i < taskDetails.size(); i++){

                if(taskDetails.get(i).getGmtCreate().after(new Timestamp(zero)) && taskDetails.get(i).getTaskId() == taskId){
                    return "每日只能完成任务一次!";
                }
                else{
                    taskdetail.setDetailContent(userId + "完成任务" + taskId);
                    taskService.insertTaskDetail(taskdetail);
                }
            }
            return "任务成功完成!";
        }
    }

    @Override
    public List<Taskdetail> searchTask(long userId, long activityId) {
        Userinfo userinfo =  userinfoDao.selectByPrimaryKey(userId);
        List<Taskdetail> taskDetails = taskService.selectTaskDetailByActivityId(activityId);
        List<Taskdetail> result = new ArrayList<>();
        if(userinfo.equals(null) || taskDetails.equals(null)){
            System.out.println("用户或活动不存在!");
            return null;
        }
        else{
            for(int i = 0; i < taskDetails.size(); i++){
                if(taskDetails.get(i).getUserId() == userId){
                    result.add(taskDetails.get(i));
                }
            }
            return result;
        }
    }

    @Override
    public String shareActivity(long userId1, long userId2, long activityId) {
        Userinfo userinfo1 =  userinfoDao.selectByPrimaryKey(userId1);
        Userinfo userinfo2 =  userinfoDao.selectByPrimaryKey(userId2);
        if(userinfo1.equals(null) || userinfo2.equals(null)) {
            return ("用户不存在!");
        }
        List<Taskdetail> taskDetails = taskService.selectTaskDetailByUserId(userId1);
        List<PrizeDetail> prizeDetails = prizeService.selectPrizeDetailById(userId1);
        List<Taskdetail> fitDetails = new ArrayList<>();
        Date date = (Date) prizeDetails.get(0).getGmtCreate();
        for(int i = 0; i < prizeDetails.size(); i++){
            if(prizeDetails.get(i).getActivityId() == activityId){
                if(date.after(prizeDetails.get(i).getGmtCreate())){
                    date = (Date) prizeDetails.get(i).getGmtCreate();
                }
            }
        }
        for(int i = 0; i < taskDetails.size(); i++){
            if(taskDetails.get(i).getAcitivityId() == activityId && taskDetails.get(i).getGmtCreate().after(date)){
                fitDetails.add(taskDetails.get(i));
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
            pointdetail.setGmtModify(dateNow);
            pointdetail.setChangeReason(userId1 + "参与拉人活动，成功邀请" + userId2 +",获得积分数" + point);
            pointService.insertPointDetail(pointdetail);
            return userId1 + "参与拉人活动，成功邀请" + userId2;
        }
    }

    @Override
    public PrizeDetail luckDraw(long userId, long activityId) {
        PrizeContent prizeContent =  prizeService.luckDraw();
        prizeContent.setPrizeNum(prizeContent.getPrizeNum() - 1);
        PrizeDetail prizeDetail = new PrizeDetail();
        prizeDetail.setActivityId(activityId);
        Date date =new Date(System.currentTimeMillis());
        prizeDetail.setGmtCreate(date);
        prizeDetail.setPrizeId(prizeContent.getPrizeId());
        prizeDetail.setUserId(userId);
        prizeDetail.setDetailContent(userId + "获得奖品" + prizeContent.getPrizeType() + prizeContent.getPrizeMoney());
        prizeService.updatePrizeContent(prizeContent);
        prizeService.insertPrizeDetail(prizeDetail);
        return prizeDetail;

    }

    @Override
    public void initPoint(long userId, long activityId) {
        Pointaccount pointaccount = new Pointaccount();
        pointaccount.setPointNumber((long)0);
        Date date =new Date(System.currentTimeMillis());
        pointaccount.setGmtCreate(date);
        pointaccount.setGmtModify(date);
        pointaccount.setUserId(userId);
        pointaccount.setPointActivity(activityId);
        System.out.println(pointaccount.toString());
        pointService.insertPointAccount(pointaccount);
    }

    @Override
    public List<Taskcontent> showTaskList(long activityId) {
        List<Taskcontent> taskContents =  taskService.selectByActivityId(activityId);
        for(int i = 0; i < taskContents.size(); i++) {
            System.out.println(taskContents.get(i).toString());
        }
        return taskService.selectByActivityId(activityId);
    }
}
