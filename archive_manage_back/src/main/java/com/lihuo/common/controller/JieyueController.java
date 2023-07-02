package com.lihuo.common.controller;

import com.lihuo.common.po.ArchiveJieyue;
import com.lihuo.common.service.DanganService;
import com.lihuo.common.service.JieyueService;
import com.lihuo.common.vo.Message;
import com.lihuo.common.vo.Page;
import com.lihuo.common.vo.JieyueVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/jieyue")
@CrossOrigin
public class JieyueController {
    @Autowired
    private JieyueService jieyueService;
    @Autowired
    private DanganService danganService;

    @GetMapping("/selJieyuePage")
    public Page selJieyuePage(int page, int limit) {
        Page page1 = this.jieyueService.selJieyuePage(page, limit);
        return page1;
    }

    @GetMapping("/selJieyueInfo")
    public Message selJieyueInfo(int id) {
        JieyueVo jieyueVo = this.jieyueService.selJieyueVoById(id);
        return jieyueVo == null ? new Message("查询失败", 0, null) : new Message("查询成功", 1, jieyueVo);
    }

    @PostMapping("/insJieyue")
    public Message insJieyue(@RequestBody ArchiveJieyue jieyue) {
        boolean result = this.jieyueService.insJieyue(jieyue);
        return result ? new Message("添加成功", 1, null) : new Message("添加失败", 0, null);
    }

    @PutMapping("/updJieyue")
    public Message updJieyue(@RequestBody ArchiveJieyue jieyue) {
        boolean result = this.jieyueService.updJieyue(jieyue);
        return result ? new Message("修改成功", 1, null) : new Message("修改失败", 0, null);
    }

    @PutMapping("/updState")
    public Message updState(@RequestBody Map<String, Integer> map) {
        ArchiveJieyue archiveJieyue = new ArchiveJieyue();
        archiveJieyue.setId(map.get("id"));
        archiveJieyue.setState(map.get("state"));
        boolean result = this.jieyueService.updJieyue(archiveJieyue);
        if (!result)
            return new Message("修改失败", 0, null);
        JieyueVo jieyueVo = this.jieyueService.selJieyueVoById(map.get("id"));
        boolean result1 = this.danganService.updZhuangtai(jieyueVo.getArchive_id(), 7);
        return result1 ? new Message("修改成功", 1, null) : new Message("修改失败", 0, null);
    }

    @DeleteMapping("/delJieyue")
    public Message delJieyue(int id) {
        boolean result = this.jieyueService.delJieyue(id);
        return result ? new Message("删除成功", 1, null) : new Message("删除失败", 0, null);
    }
}
