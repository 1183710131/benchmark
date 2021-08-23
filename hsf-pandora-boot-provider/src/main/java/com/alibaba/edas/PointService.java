package com.alibaba.edas;

import com.alibaba.edas.generator.Pointaccount;
import com.alibaba.edas.generator.Pointdetail;

import java.util.*;

public interface PointService {

    /*
    插入积分流水记录
     */
    boolean insertPointDetail(Pointdetail record);

    /*
    查找积分流水记录
     */
    List<Pointdetail> selectDetailByUserId(long userId);


    /*
    查询积分by UserId
     */
    List<Pointaccount> selectByUserId(long userId);


    /*
    增加账号积分信息
     */
    boolean insertPointAccount(Pointaccount pointaccount);

}
