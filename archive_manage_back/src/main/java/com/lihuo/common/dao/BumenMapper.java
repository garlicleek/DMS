package com.lihuo.common.dao;

import com.lihuo.common.po.Bumen;
import com.lihuo.common.vo.BumenVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BumenMapper extends BaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insert(Bumen record);

    int insertSelective(Bumen record);

    Bumen selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Bumen record);

    int updateByPrimaryKey(Bumen record);

    @Select("${sql}")
    List<BumenVo> selBumenVoList(@Param("sql") String sql);

    @Select("${sql}")
    BumenVo selBumenVo(@Param("sql") String sql);
}