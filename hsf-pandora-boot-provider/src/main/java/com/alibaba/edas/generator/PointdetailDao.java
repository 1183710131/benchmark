package com.alibaba.edas.generator;

import com.alibaba.edas.generator.Pointdetail;
import com.alibaba.edas.generator.PointdetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PointdetailDao {
    long countByExample(PointdetailExample example);

    int deleteByExample(PointdetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Pointdetail record);

    int insertSelective(Pointdetail record);

    List<Pointdetail> selectByExample(PointdetailExample example);

    Pointdetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Pointdetail record, @Param("example") PointdetailExample example);

    int updateByExample(@Param("record") Pointdetail record, @Param("example") PointdetailExample example);

    int updateByPrimaryKeySelective(Pointdetail record);

    int updateByPrimaryKey(Pointdetail record);
}