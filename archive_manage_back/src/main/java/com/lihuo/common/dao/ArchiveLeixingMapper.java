package com.lihuo.common.dao;

import com.lihuo.common.po.ArchiveLeixing;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArchiveLeixingMapper extends BaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insert(ArchiveLeixing record);

    int insertSelective(ArchiveLeixing record);

    ArchiveLeixing selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArchiveLeixing record);

    int updateByPrimaryKey(ArchiveLeixing record);

    @Select("${sql}")
    List<ArchiveLeixing> selArchiveLeixingList(@Param("sql") String sql);
}