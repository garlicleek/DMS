package com.lihuo.common.dao;

import com.lihuo.common.po.ArchiveJieyue;
import com.lihuo.common.vo.JieyueVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ArchiveJieyueMapper extends BaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insert(ArchiveJieyue record);

    int insertSelective(ArchiveJieyue record);

    ArchiveJieyue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArchiveJieyue record);

    int updateByPrimaryKey(ArchiveJieyue record);

    @Select("${sql}")
    List<JieyueVo> selJieyueVoList(@Param("sql") String sql);

    @Select("${sql}")
    JieyueVo selJIeyueVo(@Param("sql") String sql);
}