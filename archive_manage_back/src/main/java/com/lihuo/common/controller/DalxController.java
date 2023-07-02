package com.lihuo.common.controller;

import com.lihuo.common.po.Archive;
import com.lihuo.common.po.ArchiveLeixing;
import com.lihuo.common.service.DalxService;
import com.lihuo.common.service.DanganService;
import com.lihuo.common.vo.ArchiveVo;
import com.lihuo.common.vo.Message;
import com.lihuo.common.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dalx")
@CrossOrigin
public class DalxController {

    @Autowired
    private DalxService dalxService;

    @GetMapping("/selDalxPage")
    public Page selDalxPage(int page, int limit) {
        Page page1 = this.dalxService.selDalxPage(page, limit);
        return page1;
    }

    @GetMapping("/selDalxInfo")
    public Message selDalxInfo(int id) {
        ArchiveLeixing archiveLeixing = this.dalxService.selDalxById(id);
        return archiveLeixing == null ? new Message("查询失败", 0, null) : new Message("查询成功", 1, archiveLeixing);
    }

    @PostMapping("/insDalx")
    public Message insDalx(@RequestBody ArchiveLeixing archiveLeixing) {
        boolean result = this.dalxService.insDalx(archiveLeixing);
        return result ? new Message("添加成功", 1, null) : new Message("添加失败", 0, null);
    }

    @PutMapping("/updDalx")
    public Message updDalx(@RequestBody ArchiveLeixing archiveLeixing) {
        boolean result = this.dalxService.updDalx(archiveLeixing);
        return result ? new Message("修改成功", 1, null) : new Message("修改失败", 0, null);
    }

    @DeleteMapping("/delDalx")
    public Message delDalx(int id) {
        boolean result = this.dalxService.delDalx(id);
        return result ? new Message("删除成功", 1, null) : new Message("删除失败", 0, null);
    }
}