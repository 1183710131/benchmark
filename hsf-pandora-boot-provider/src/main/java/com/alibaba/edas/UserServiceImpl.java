package com.alibaba.edas;

import com.alibaba.boot.hsf.annotation.HSFProvider;
import com.alibaba.edas.generator.*;

import javax.annotation.Resource;

@HSFProvider(serviceInterface = UserService.class, serviceVersion = "1.0.0")
public class UserServiceImpl implements UserService{
    @Resource
    private UserinfoDao userinfoDao;

    @Override
    public boolean insertUser(Userinfo userinfo) {
        userinfoDao.insert(userinfo);
        return true;
    }

    @Override
    public Userinfo selectUserinfoById(long id) {
        return userinfoDao.selectByPrimaryKey(id);

    }

    @Override
    public boolean deleteUserById(long id) {
        userinfoDao.deleteByPrimaryKey(id);
        return true;
    }

    @Override
    public boolean updateUserinfo(Userinfo userinfo) {
        userinfoDao.updateByPrimaryKeySelective(userinfo);
        return true;
    }
}
