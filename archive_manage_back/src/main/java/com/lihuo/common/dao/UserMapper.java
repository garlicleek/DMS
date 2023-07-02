package com.lihuo.common.dao;

import com.lihuo.common.po.User;
import com.lihuo.common.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper extends BaseMapper{
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    @Select("${sql}")
    User selUser(@Param("sql") String sql);

    @Select("${sql}")
    List<UserVo> selUserVoList(@Param("sql") String sql);

    @Select("${sql}")
    UserVo selUserVo(@Param("sql") String sql);
}