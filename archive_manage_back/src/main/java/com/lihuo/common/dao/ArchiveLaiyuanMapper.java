package com.lihuo.common.dao;

import com.lihuo.common.po.ArchiveLaiyuan;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArchiveLaiyuanMapper extends BaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insert(ArchiveLaiyuan record);

    int insertSelective(ArchiveLaiyuan record);

    ArchiveLaiyuan selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArchiveLaiyuan record);

    int updateByPrimaryKey(ArchiveLaiyuan record);

    @Select("${sql}")
    List<ArchiveLaiyuan> selArchiveLaiyuanList(@Param("sql") String sql);
}