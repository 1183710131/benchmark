package com.alibaba.edas;

import com.alibaba.boot.hsf.annotation.HSFProvider;
import com.alibaba.edas.generator.*;

import javax.annotation.Resource;
import java.util.List;

@HSFProvider(serviceInterface = TaskService.class, serviceVersion = "1.0.0")
public class TaskServiceImpl implements TaskService{

    @Resource
    private TaskcontentDao taskcontentDao;

    @Resource
    private TaskdetailDao taskdetailDao;

    @Override
    public boolean insertTaskContent(Taskcontent taskcontent) {
        taskcontentDao.insert(taskcontent);
        return true;
    }

    @Override
    public boolean deleteTaskContent(long taskId) {
        taskcontentDao.deleteByPrimaryKey(taskId);
        return true;
    }

    @Override
    public boolean updateTaskContent(Taskcontent taskcontent) {
        taskcontentDao.updateByPrimaryKey(taskcontent);
        return true;
    }

    @Override
    public List<Taskcontent> selectByActivityId(long activityId) {
        TaskcontentExample taskcontentExample = new TaskcontentExample();
        TaskcontentExample.Criteria criteria = taskcontentExample.createCriteria();
        criteria.andActivityIdEqualTo(activityId);
        return taskcontentDao.selectByExample(taskcontentExample);
    }

    @Override
    public Taskcontent selectByTaskId(long taskId) {
        return taskcontentDao.selectByPrimaryKey(taskId);
    }

    @Override
    public boolean insertTaskDetail(Taskdetail taskdetail) {
        taskdetailDao.insert(taskdetail);
        return true;
    }

    @Override
    public List<Taskdetail> selectTaskDetailByUserId(long userId) {
        TaskdetailExample taskdetailExample = new TaskdetailExample();
        TaskdetailExample.Criteria criteria = taskdetailExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return taskdetailDao.selectByExample(taskdetailExample);
    }

    @Override
    public List<Taskdetail> selectTaskDetailByActivityId(long activityId) {
        TaskdetailExample taskdetailExample = new TaskdetailExample();
        TaskdetailExample.Criteria criteria = taskdetailExample.createCriteria();
        criteria.andAcitivityIdEqualTo(activityId);
        return taskdetailDao.selectByExample(taskdetailExample);
    }
}
