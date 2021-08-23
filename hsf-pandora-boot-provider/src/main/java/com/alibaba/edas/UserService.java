package com.alibaba.edas;

import com.alibaba.edas.generator.Userinfo;

public interface UserService {

    /*
    �����û���¼
     */
    boolean insertUser (Userinfo userinfo);

    /*
    �����û���Ϣ by id
     */
    Userinfo selectUserinfoById(long id);

    /*
    ɾ���û� by id
     */
    boolean deleteUserById(long id);

    /*
    �޸��û���Ϣ
     */
    boolean updateUserinfo(Userinfo userinfo);
}
