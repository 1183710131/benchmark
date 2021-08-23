package com.alibaba.edas.generator;

import com.alibaba.edas.generator.Pointaccount;
import com.alibaba.edas.generator.PointaccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PointaccountDao {
    long countByExample(PointaccountExample example);

    int deleteByExample(PointaccountExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Pointaccount record);

    int insertSelective(Pointaccount record);

    List<Pointaccount> selectByExample(PointaccountExample example);

    Pointaccount selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Pointaccount record, @Param("example") PointaccountExample example);

    int updateByExample(@Param("record") Pointaccount record, @Param("example") PointaccountExample example);

    int updateByPrimaryKeySelective(Pointaccount record);

    int updateByPrimaryKey(Pointaccount record);
}