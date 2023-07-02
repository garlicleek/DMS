package com.lihuo.common.dao;

import com.lihuo.common.po.Quanxian;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface QuanxianMapper extends BaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insert(Quanxian record);

    int insertSelective(Quanxian record);

    Quanxian selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Quanxian record);

    int updateByPrimaryKey(Quanxian record);

    @Select("${sql}")
    List<Quanxian> selQuanxianList(@Param("sql") String sql);
}