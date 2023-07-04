package com.lihuo.common.controller;

import com.lihuo.common.po.ArchiveHistory;
import com.lihuo.common.service.DalsService;
import com.lihuo.common.vo.Message;
import com.lihuo.common.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dals")
@CrossOrigin
public class DalsController {

    @Autowired
    private DalsService dalsService;

    @GetMapping("/selDalsPage")
    public Page selDalsPage(int page, int limit) {
        Page page1 = this.dalsService.selDalsPage(page, limit);
        return page1;
    }

    @PostMapping("/insDals")
    public Message insDals(@RequestBody ArchiveHistory archiveHistory) {
        boolean result = this.dalsService.insDals(archiveHistory);
        return result ? new Message("添加成功", 1, null) : new Message("添加失败", 0, null);
    }

}