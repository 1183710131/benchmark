package com.alibaba.edas;

import com.alibaba.edas.generator.Pointaccount;
import com.alibaba.edas.generator.Pointdetail;

import java.util.*;

public interface PointService {

    /*
    ���������ˮ��¼
     */
    boolean insertPointDetail(Pointdetail record);

    /*
    ���һ�����ˮ��¼
     */
    List<Pointdetail> selectDetailByUserId(long userId);


    /*
    ��ѯ����by UserId
     */
    List<Pointaccount> selectByUserId(long userId);


    /*
    �����˺Ż�����Ϣ
     */
    boolean insertPointAccount(Pointaccount pointaccount);

}
