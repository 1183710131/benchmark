package com.alibaba.edas.generator;

import org.apache.ibatis.annotations.Param;

import java.util.List;

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