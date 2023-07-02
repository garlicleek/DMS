package com.lihuo.common.service;

import com.lihuo.common.po.User;
import com.lihuo.common.vo.Page;
import com.lihuo.common.vo.UserVo;

public interface UserService {
    User selUserByUsername(String userName);

    Page selUserPage(int page, int limit);

    UserVo selUserVoById(int id);

    boolean insUser(User user);

    boolean updUser(User user);

    boolean delUser(int id);

    User selUserByUsernameAndPassword(String username, String password);
}
