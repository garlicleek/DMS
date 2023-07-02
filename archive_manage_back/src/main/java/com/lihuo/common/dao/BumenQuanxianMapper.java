package com.lihuo.common.dao;

import com.lihuo.common.po.BumenQuanxian;
import com.lihuo.common.vo.BmqxVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BumenQuanxianMapper extends BaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insert(BumenQuanxian record);

    int insertSelective(BumenQuanxian record);

    BumenQuanxian selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BumenQuanxian record);

    int updateByPrimaryKey(BumenQuanxian record);

    @Select("${sql}")
    List<BmqxVo> selBmqxVoList(@Param("sql") String sql);

    @Select("${sql}")
    BmqxVo selBmqxVo(@Param("sql") String sql);

    @Select("${sql}")
    List<BumenQuanxian> selBmqxList(@Param("sql") String sql);
}