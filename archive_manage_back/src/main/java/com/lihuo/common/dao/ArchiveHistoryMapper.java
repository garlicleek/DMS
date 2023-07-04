package com.lihuo.common.dao;

import com.lihuo.common.po.ArchiveHistory;
import com.lihuo.common.vo.ArchiveHistoryVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArchiveHistoryMapper extends BaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insert(ArchiveHistory record);

    int insertSelective(ArchiveHistory record);

    ArchiveHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArchiveHistory record);

    int updateByPrimaryKey(ArchiveHistory record);

    @Select("${sql}")
    List<ArchiveHistoryVo> selArchiveHistoryVoList(@Param("sql") String sql);
}