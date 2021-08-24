package com.alibaba.edas;

import com.alibaba.boot.hsf.annotation.HSFProvider;
import com.alibaba.edas.generator.*;

import javax.annotation.Resource;
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
        List<Pointaccount> pointaccounts = selectByUserId(userId);
        for(int i = 0; i < pointaccounts.size(); i++){
            if(pointaccounts.get(i).getId() == pointId){
                long num = pointaccounts.get(i).getPointNumber() + pointChange;
                pointaccounts.get(i).setPointNumber(num);
                pointaccountDao.updateByPrimaryKey(pointaccounts.get(i));
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
    public List<Pointaccount> selectByUserId(long userId) {
        PointaccountExample pointaccountExample = new PointaccountExample();
        PointaccountExample.Criteria criteria = pointaccountExample.createCriteria();
        criteria.andUserIdEqualTo(userId);
        //criteria.andPointActivityEqualTo(activityId);
        return pointaccountDao.selectByExample(pointaccountExample);
    }

    @Override
    public boolean insertPointAccount(Pointaccount pointaccount) {
        pointaccountDao.insert(pointaccount);
        return true;
    }
}
