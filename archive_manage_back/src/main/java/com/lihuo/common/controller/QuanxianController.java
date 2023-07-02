package com.lihuo.common.controller;

import com.lihuo.common.po.Quanxian;
import com.lihuo.common.service.QuanxianService;
import com.lihuo.common.vo.Message;
import com.lihuo.common.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quanxian")
@CrossOrigin
public class QuanxianController {
    @Autowired
    private QuanxianService quanxianService;

    @GetMapping("/selQuanxianPage")
    public Page selQuanxianPage(int page, int limit) {
        Page page1 = this.quanxianService.selQuanxianPage(page, limit);
        return page1;
    }

    @GetMapping("/selQuanxianInfo")
    public Message selQuanxianInfo(int id) {
        Quanxian quanxian = this.quanxianService.selQuanxianById(id);
        return quanxian == null ? new Message("查询失败", 0, null) : new Message("查询成功", 1, quanxian);
    }

    @PostMapping("/insQuanxian")
    public Message insQuanxian(@RequestBody Quanxian quanxian) {
        boolean result = this.quanxianService.insQuanxian(quanxian);
        return result ? new Message("添加成功", 1, null) : new Message("添加失败", 0, null);
    }

    @PutMapping("/updQuanxian")
    public Message updQuanxian(@RequestBody Quanxian quanxian) {
        boolean result = this.quanxianService.updQuanxian(quanxian);
        return result ? new Message("修改成功", 1, null) : new Message("修改失败", 0, null);
    }

    @DeleteMapping("/delQuanxian")
    public Message delQuanxian(int id) {
        boolean result = this.quanxianService.delQuanxian(id);
        return result ? new Message("删除成功", 1, null) : new Message("删除失败", 0, null);
    }
}