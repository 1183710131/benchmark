package com.alibaba.edas.generator;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserinfoDao {
    long countByExample(UserinfoExample example);

    int deleteByExample(UserinfoExample example);

    int deleteByPrimaryKey(Long userId);

    int insert(Userinfo record);

    int insertSelective(Userinfo record);

    List<Userinfo> selectByExample(UserinfoExample example);

    Userinfo selectByPrimaryKey(Long userId);

    int updateByExampleSelective(@Param("record") Userinfo record, @Param("example") UserinfoExample example);

    int updateByExample(@Param("record") Userinfo record, @Param("example") UserinfoExample example);

    int updateByPrimaryKeySelective(Userinfo record);

    int updateByPrimaryKey(Userinfo record);
}