package com.lihuo.common.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface BaseMapper {
    @Select("${sql}")
    int selInt(@Param("sql") String sql);

    @Update("${sql}")
    int updData(@Param("sql") String sql);

    @Select("${sql}")
    String selString(@Param("sql") String sql);

    @Select("${sql}")
    List<Date> selDateList(@Param("sql") String riqiSql);
}
