package com.lihuo.common.controller;

import cn.hutool.jwt.JWT;
import com.lihuo.common.po.BumenQuanxian;
import com.lihuo.common.service.BmqxService;
import com.lihuo.common.vo.Message;
import com.lihuo.common.vo.Page;
import com.lihuo.common.vo.BmqxVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/bmqx")
@CrossOrigin
public class BmqxController {
    @Autowired
    private BmqxService bmqxService;

    @GetMapping("/selBmqxPage")
    public Page selBmqxPage(int page, int limit) {
        Page page1 = this.bmqxService.selBmqxPage(page, limit);
        return page1;
    }

    @GetMapping("/selBmqxInfo")
    public Message selBmqxInfo(int id) {
        BmqxVo bmqxVo = this.bmqxService.selBmqxVoById(id);
        return bmqxVo == null ? new Message("查询失败", 0, null) : new Message("查询成功", 1, bmqxVo);
    }

    @PostMapping("/insBmqx")
    public Message insBmqx(@RequestBody BumenQuanxian bmqx) {
        boolean result = this.bmqxService.insBmqx(bmqx);
        return result ? new Message("添加成功", 1, null) : new Message("添加失败", 0, null);
    }

    @PutMapping("/updBmqx")
    public Message updBmqx(@RequestBody BumenQuanxian bmqx) {
        boolean result = this.bmqxService.updBmqx(bmqx);
        return result ? new Message("修改成功", 1, null) : new Message("修改失败", 0, null);
    }

    @DeleteMapping("/delBmqx")
    public Message delBmqx(int id) {
        boolean result = this.bmqxService.delBmqx(id);
        return result ? new Message("删除成功", 1, null) : new Message("删除失败", 0, null);
    }
}
