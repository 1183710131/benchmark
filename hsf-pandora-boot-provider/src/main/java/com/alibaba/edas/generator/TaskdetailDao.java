package com.alibaba.edas.generator;

import com.alibaba.edas.generator.Taskdetail;
import com.alibaba.edas.generator.TaskdetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskdetailDao {
    long countByExample(TaskdetailExample example);

    int deleteByExample(TaskdetailExample example);

    int deleteByPrimaryKey(Long detailId);

    int insert(Taskdetail record);

    int insertSelective(Taskdetail record);

    List<Taskdetail> selectByExample(TaskdetailExample example);

    Taskdetail selectByPrimaryKey(Long detailId);

    int updateByExampleSelective(@Param("record") Taskdetail record, @Param("example") TaskdetailExample example);

    int updateByExample(@Param("record") Taskdetail record, @Param("example") TaskdetailExample example);

    int updateByPrimaryKeySelective(Taskdetail record);

    int updateByPrimaryKey(Taskdetail record);
}