package com.alibaba.edas;

import com.alibaba.edas.generator.Userinfo;

public interface UserService {


    boolean insertUser (Userinfo userinfo);


    Userinfo selectUserinfoById(long id);


    boolean deleteUserById(long id);


    boolean updateUserinfo(Userinfo userinfo);
}
