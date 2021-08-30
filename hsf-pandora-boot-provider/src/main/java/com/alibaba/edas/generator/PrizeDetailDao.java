package com.alibaba.edas.generator;

import com.alibaba.edas.generator.PrizeDetail;
import com.alibaba.edas.generator.PrizeDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PrizeDetailDao {
    long countByExample(PrizeDetailExample example);

    int deleteByExample(PrizeDetailExample example);

    int deleteByPrimaryKey(Long detailId);

    int insert(PrizeDetail record);

    int insertSelective(PrizeDetail record);

    List<PrizeDetail> selectByExample(PrizeDetailExample example);

    PrizeDetail selectByPrimaryKey(Long detailId);

    int updateByExampleSelective(@Param("record") PrizeDetail record, @Param("example") PrizeDetailExample example);

    int updateByExample(@Param("record") PrizeDetail record, @Param("example") PrizeDetailExample example);

    int updateByPrimaryKeySelective(PrizeDetail record);

    int updateByPrimaryKey(PrizeDetail record);
}