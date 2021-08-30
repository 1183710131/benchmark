package com.alibaba.edas;

import com.alibaba.edas.generator.Pointaccount;
import com.alibaba.edas.generator.Pointdetail;

import java.util.*;

public interface PointService {


    boolean insertPointDetail(Pointdetail record);


    List<Pointdetail> selectDetailByUserId(long userId);



    Pointaccount selectByUserId(long userId,long activityId);



    boolean insertPointAccount(Pointaccount pointaccount);

}
