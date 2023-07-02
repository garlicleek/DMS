package com.lihuo.common.controller;

import cn.hutool.jwt.JWT;
import com.lihuo.common.po.User;
import com.lihuo.common.service.UserService;
import com.lihuo.common.vo.Message;
import com.lihuo.common.vo.Page;
import com.lihuo.common.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Value("${jwt.key}")
    private String key;
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public Message login(String username, String password) {
        User user = this.userService.selUserByUsernameAndPassword(username, password);
        if (user == null) {
            return new Message("登录失败", 0, null);
        } else {
            String token = JWT.create().setPayload("username", username).setKey(key.getBytes(StandardCharsets.UTF_8)).sign();
            return new Message("登录成功", 1, token);
        }
    }

    @GetMapping("/selUserPage")
    public Page selUserPage(int page, int limit) {
        Page page1 = this.userService.selUserPage(page, limit);
        return page1;
    }

    @GetMapping("/selUserInfo")
    public Message selUserInfo(int id) {
        UserVo userVo = this.userService.selUserVoById(id);
        return userVo == null ? new Message("查询失败", 0, null) : new Message("查询成功", 1, userVo);
    }

    @PostMapping("/insUser")
    public Message insUser(@RequestBody User user) {
        boolean result = this.userService.insUser(user);
        return result ? new Message("添加成功", 1, null) : new Message("添加失败", 0, null);
    }

    @PutMapping("/updUser")
    public Message updUser(@RequestBody User user) {
        boolean result = this.userService.updUser(user);
        return result ? new Message("修改成功", 1, null) : new Message("修改失败", 0, null);
    }

    @DeleteMapping("/delUser")
    public Message delUser(int id) {
        boolean result = this.userService.delUser(id);
        return result ? new Message("删除成功", 1, null) : new Message("删除失败", 0, null);
    }
}