package com.lihuo.common.controller;

import com.lihuo.common.po.Archive;
import com.lihuo.common.po.ArchiveLaiyuan;
import com.lihuo.common.service.DalyService;
import com.lihuo.common.service.DanganService;
import com.lihuo.common.vo.ArchiveVo;
import com.lihuo.common.vo.Message;
import com.lihuo.common.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/daly")
@CrossOrigin
public class DalyController {

    @Autowired
    private DalyService dalyService;

    @GetMapping("/selDalyPage")
    public Page selDalyPage(int page, int limit) {
        Page page1 = this.dalyService.selDalyPage(page, limit);
        return page1;
    }

    @GetMapping("/selDalyInfo")
    public Message selDalyInfo(int id) {
        ArchiveLaiyuan archiveLaiyuan = this.dalyService.selDalyById(id);
        return archiveLaiyuan == null ? new Message("查询失败", 0, null) : new Message("查询成功", 1, archiveLaiyuan);
    }

    @PostMapping("/insDaly")
    public Message insDaly(@RequestBody ArchiveLaiyuan archiveLaiyuan) {
        boolean result = this.dalyService.insDaly(archiveLaiyuan);
        return result ? new Message("添加成功", 1, null) : new Message("添加失败", 0, null);
    }

    @PutMapping("/updDaly")
    public Message updDaly(@RequestBody ArchiveLaiyuan archiveLaiyuan) {
        boolean result = this.dalyService.updDaly(archiveLaiyuan);
        return result ? new Message("修改成功", 1, null) : new Message("修改失败", 0, null);
    }

    @DeleteMapping("/delDaly")
    public Message delDaly(int id) {
        boolean result = this.dalyService.delDaly(id);
        return result ? new Message("删除成功", 1, null) : new Message("删除失败", 0, null);
    }
}