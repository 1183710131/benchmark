package com.alibaba.edas;

import com.alibaba.boot.hsf.annotation.HSFProvider;
import com.alibaba.edas.generator.*;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;

@HSFProvider(serviceInterface = PointService.class, serviceVersion = "1.0.0")
public class PointServiceImpl implements PointService{
    @Resource
    private PointaccountDao pointaccountDao;

    @Resource
    private PointdetailDao pointdetailDao;

    @Override
    public boolean insertPointDetail(Pointdetail record) {
        pointdetailDao.insert(record);
        long pointId = record.getPointId();
        long userId = record.getUserId();
        long pointChange = record.getPointChange();
        List<Pointaccount> pointAccounts = (List<Pointaccount>) pointaccountDao.selectByPrimaryKey(pointId);
        //List<Pointaccount> pointaccounts = selectByUserId(userId);
        for(int i = 0; i < pointAccounts.size(); i++){
            if(pointAccounts.get(i).getUserId() == userId){
                long num = pointAccounts.get(i).getPointNumber() + pointChange;
                pointAccounts.get(i).setPointNumber(num);
                Date date =new Date(System.currentTimeMillis());
                pointAccounts.get(i).setGmtModify(date);
                pointaccountDao.updateByPrimaryKey(pointAccounts.get(i));
            }
        }
        return true;
    }

    @Override
    public List<Pointdetail> selectDetailByUserId(long userId) {
        PointdetailExample pointdetailExample = new PointdetailExample();
        PointdetailExample.Criteria criteria =  pointdetailExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        return pointdetailDao.selectByExample(pointdetailExample);
    }

    @Override
    public Pointaccount selectByUserId(long userId,long activityId) {
        PointaccountExample pointaccountExample = new PointaccountExample();
        PointaccountExample.Criteria criteria = pointaccountExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        criteria.andPointActivityEqualTo(activityId);
        return pointaccountDao.selectByExample(pointaccountExample).get(0);
    }

    @Override
    public boolean insertPointAccount(Pointaccount pointaccount) {
        pointaccountDao.insert(pointaccount);
        return true;
    }
}
