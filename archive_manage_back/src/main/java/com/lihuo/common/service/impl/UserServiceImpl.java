package com.lihuo.common.service.impl;

import com.lihuo.common.dao.UserMapper;
import com.lihuo.common.po.User;
import com.lihuo.common.service.UserService;
import com.lihuo.common.vo.Page;
import com.lihuo.common.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User selUserByUsername(String username) {
        String sql = "select * from user where username='" + username + "'";
        User user = this.userMapper.selUser(sql);
        return user;
    }

    @Override
    public User selUserByUsernameAndPassword(String username, String password) {
        String sql = "select * from user where username='" + username + "' and password='" + password + "'";
        User user = this.userMapper.selUser(sql);
        return user;
    }

    @Override
    public Page selUserPage(int page, int limit) {
        String listSql = "select a.*,b.mingcheng as bumen from user a left join bumen b on a.bumen_id=b.id limit " + (page - 1) * limit + "," + limit;
        String countSql = "select count(id) from user";
        List<UserVo> list = this.userMapper.selUserVoList(listSql);
        int count = this.userMapper.selInt(countSql);
        return new Page("查询成功", count, list);
    }

    @Override
    public UserVo selUserVoById(int id) {
        String sql = "select a.*,b.mingcheng as bumen from user a left join bumen b on a.bumen_id=b.id where a.id=" + id;
        UserVo userVo = this.userMapper.selUserVo(sql);
        return userVo;
    }

    @Override
    public boolean insUser(User user) {
        User user1 = this.selUserByUsername(user.getUsername());
        if (user1 == null) {
            int rows = this.userMapper.insertSelective(user);
            return rows > 0;
        } else
            return false;
    }

    @Override
    public boolean updUser(User user) {
        String sql = "select count(id) from user where username='" + user.getUsername() + "' and id!=" + user.getId();
        int count = this.userMapper.selInt(sql);
        if (count == 0) {
            int rows = this.userMapper.updateByPrimaryKeySelective(user);
            return rows > 0;
        } else
            return false;
    }

    @Override
    public boolean delUser(int id) {
        int rows = this.userMapper.deleteByPrimaryKey(id);
        return rows > 0;
    }
}
