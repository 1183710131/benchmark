package com.alibaba.edas.generator;

import com.alibaba.edas.generator.PrizeContent;
import com.alibaba.edas.generator.PrizeContentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PrizeContentDao {
    long countByExample(PrizeContentExample example);

    int deleteByExample(PrizeContentExample example);

    int deleteByPrimaryKey(Long prizeId);

    int insert(PrizeContent record);

    int insertSelective(PrizeContent record);

    List<PrizeContent> selectByExample(PrizeContentExample example);

    PrizeContent selectByPrimaryKey(Long prizeId);

    int updateByExampleSelective(@Param("record") PrizeContent record, @Param("example") PrizeContentExample example);

    int updateByExample(@Param("record") PrizeContent record, @Param("example") PrizeContentExample example);

    int updateByPrimaryKeySelective(PrizeContent record);

    int updateByPrimaryKey(PrizeContent record);
}