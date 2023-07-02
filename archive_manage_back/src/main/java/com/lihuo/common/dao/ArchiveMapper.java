package com.lihuo.common.dao;

import com.lihuo.common.po.Archive;
import com.lihuo.common.vo.ArchiveVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArchiveMapper extends BaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insert(Archive record);

    int insertSelective(Archive record);

    Archive selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Archive record);

    int updateByPrimaryKey(Archive record);

    @Select("${sql}")
    List<ArchiveVo> selArchiveVoList(@Param("sql") String sql);

    @Select("${sql}")
    ArchiveVo selArchiveVo(@Param("sql") String sql);
}