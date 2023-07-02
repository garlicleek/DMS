package com.lihuo.common.controller;

import com.lihuo.common.po.Bumen;
import com.lihuo.common.service.BumenService;
import com.lihuo.common.vo.Message;
import com.lihuo.common.vo.Page;
import com.lihuo.common.vo.BumenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bumen")
@CrossOrigin
public class BumenController {
    @Autowired
    private BumenService bumenService;

    @GetMapping("/selBumenPage")
    public Page selBumenPage(int page, int limit, int dengji, int shangji_id) {
        Page page1 = this.bumenService.selBumenPage(page, limit, dengji, shangji_id);
        return page1;
    }

    @GetMapping("/selBumenInfo")
    public Message selBumenInfo(int id) {
        BumenVo bumenVo = this.bumenService.selBumenVoById(id);
        return bumenVo == null ? new Message("查询失败", 0, null) : new Message("查询成功", 1, bumenVo);
    }

    @PostMapping("/insBumen")
    public Message insBumen(@RequestBody Bumen bumen) {
        boolean result = this.bumenService.insBumen(bumen);
        return result ? new Message("添加成功", 1, null) : new Message("添加失败", 0, null);
    }

    @PutMapping("/updBumen")
    public Message updBumen(@RequestBody Bumen bumen) {
        boolean result = this.bumenService.updBumen(bumen);
        return result ? new Message("修改成功", 1, null) : new Message("修改失败", 0, null);
    }

    @DeleteMapping("/delBumen")
    public Message delBumen(int id) {
        boolean result = this.bumenService.delBumen(id);
        return result ? new Message("删除成功", 1, null) : new Message("删除失败", 0, null);
    }
}
