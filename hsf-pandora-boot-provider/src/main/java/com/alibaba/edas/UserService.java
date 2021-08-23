package com.alibaba.edas;

import com.alibaba.edas.generator.Userinfo;

public interface UserService {

    /*
    增加用户记录
     */
    boolean insertUser (Userinfo userinfo);

    /*
    查找用户信息 by id
     */
    Userinfo selectUserinfoById(long id);

    /*
    删除用户 by id
     */
    boolean deleteUserById(long id);

    /*
    修改用户信息
     */
    boolean updateUserinfo(Userinfo userinfo);
}
