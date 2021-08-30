package com.alibaba.edas.generator;

import com.alibaba.edas.generator.Taskcontent;
import com.alibaba.edas.generator.TaskcontentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskcontentDao {
    long countByExample(TaskcontentExample example);

    int deleteByExample(TaskcontentExample example);

    int deleteByPrimaryKey(Long taskId);

    int insert(Taskcontent record);

    int insertSelective(Taskcontent record);

    List<Taskcontent> selectByExample(TaskcontentExample example);

    Taskcontent selectByPrimaryKey(Long taskId);

    int updateByExampleSelective(@Param("record") Taskcontent record, @Param("example") TaskcontentExample example);

    int updateByExample(@Param("record") Taskcontent record, @Param("example") TaskcontentExample example);

    int updateByPrimaryKeySelective(Taskcontent record);

    int updateByPrimaryKey(Taskcontent record);
}