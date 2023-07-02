package com.lihuo.common.controller;

import cn.hutool.jwt.JWTUtil;
import com.lihuo.common.po.Archive;
import com.lihuo.common.po.ArchiveJieyue;
import com.lihuo.common.po.User;
import com.lihuo.common.service.DanganService;
import com.lihuo.common.service.JieyueService;
import com.lihuo.common.service.UserService;
import com.lihuo.common.vo.ArchiveVo;
import com.lihuo.common.vo.Message;
import com.lihuo.common.vo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/dangan")
@CrossOrigin
public class DanganController {
    @Value("${jwt.key}")
    private String key;
    private final static String AUTH_HEADER = "Authorization";
    private final static String AUTH_HEADER_TYPE = "Bearer";

    @Autowired
    private DanganService danganService;
    @Autowired
    private JieyueService jieyueService;
    @Autowired
    private UserService userService;

    @GetMapping("/selDanganPage")
    public Page selDanganPage(int page, int limit) {
        Page page1 = this.danganService.selArchivePage(page, limit);
        return page1;
    }

    @GetMapping("/selDanganInfo")
    public Message selDanganInfo(int id) {
        ArchiveVo archiveVo = this.danganService.selArchiveVoById(id);
        return archiveVo == null ? new Message("查询失败", 0, null) : new Message("查询成功", 1, archiveVo);
    }

    @PostMapping("/insDangan")
    public Message insDangan(@RequestBody Archive archive) {
        boolean result = this.danganService.insArchive(archive);
        return result ? new Message("添加成功", 1, null) : new Message("添加失败", 0, null);
    }

    @PutMapping("/updDangan")
    public Message updDangan(@RequestBody Archive archive) {
        boolean result = this.danganService.updArchive(archive);
        return result ? new Message("修改成功", 1, null) : new Message("修改失败", 0, null);
    }

    @PutMapping("/updSp1")
    public Message updSp1(@RequestBody Map<String, Integer> map) {
        boolean result = this.danganService.updZhuangtai(map.get("id"), map.get("zhuangtai"));
        return result ? new Message("修改成功", 1, null) : new Message("修改失败", 0, null);
    }

    @PutMapping("/updSp2")
    public Message updSp2(@RequestBody Map<String, Integer> map) {
        boolean result = this.danganService.updZhuangtai(map.get("id"), map.get("zhuangtai"));
        return result ? new Message("修改成功", 1, null) : new Message("修改失败", 0, null);
    }

    @PutMapping("/updJj")
    public Message updJj(@RequestBody Map<String, Integer> map) {
        boolean result = this.danganService.updZhuangtai(map.get("id"), map.get("zhuangtai"));
        return result ? new Message("修改成功", 1, null) : new Message("修改失败", 0, null);
    }

    @PutMapping("/updJd")
    public Message updJd(@RequestBody Map<String, Integer> map) {
        boolean result = this.danganService.updZhuangtai(map.get("id"), map.get("zhuangtai"));
        return result ? new Message("修改成功", 1, null) : new Message("修改失败", 0, null);
    }

    @PutMapping("/updGd")
    public Message updGd(@RequestBody Map<String, String> map) {
        Archive archive = new Archive();
        archive.setId(Integer.parseInt(map.get("id")));
        archive.setZhuangtai(Integer.parseInt(map.get("zhuangtai")));
        archive.setDanganjia(map.get("danganjia"));
        boolean result = this.danganService.updArchive(archive);
        return result ? new Message("修改成功", 1, null) : new Message("修改失败", 0, null);
    }

    @PutMapping("/updBg")
    public Message updBg(@RequestBody Map<String, Integer> map) {
        boolean result = this.danganService.updZhuangtai(map.get("id"), map.get("zhuangtai"));
        return result ? new Message("修改成功", 1, null) : new Message("修改失败", 0, null);
    }

    @PutMapping("/updJy")
    public Message updJy(@RequestBody Map<String, Integer> map, HttpServletRequest request) {
        String authHeader = request.getHeader(AUTH_HEADER);
        String authToken = authHeader.split(" ")[1];
        String userName = (String) JWTUtil.parseToken(authToken).getPayload("username");
        User user = this.userService.selUserByUsername(userName);
        ArchiveJieyue archiveJieyue = new ArchiveJieyue();
        archiveJieyue.setArchive_id(map.get("id"));
        archiveJieyue.setUser_id(user.getId());
        boolean result = this.jieyueService.insJieyue(archiveJieyue);
        return result ? new Message("修改成功", 1, null) : new Message("修改失败", 0, null);
    }

    @PutMapping("/updGh")
    public Message updGh(@RequestBody Map<String, Integer> map) {
        boolean result = this.danganService.updZhuangtai(map.get("id"), map.get("zhuangtai"));
        return result ? new Message("修改成功", 1, null) : new Message("修改失败", 0, null);
    }

    @PutMapping("/updDqjd")
    public Message updDqjd(@RequestBody Map<String, Integer> map) {
        boolean result = this.danganService.updZhuangtai(map.get("id"), map.get("zhuangtai"));
        return result ? new Message("修改成功", 1, null) : new Message("修改失败", 0, null);
    }

    @PutMapping("/updXh1")
    public Message updXh1(@RequestBody Map<String, Integer> map) {
        boolean result = this.danganService.updZhuangtai(map.get("id"), map.get("zhuangtai"));
        return result ? new Message("修改成功", 1, null) : new Message("修改失败", 0, null);
    }

    @PutMapping("/updXh2")
    public Message updXh2(@RequestBody Map<String, Integer> map) {
        boolean result = this.danganService.updZhuangtai(map.get("id"), map.get("zhuangtai"));
        return result ? new Message("修改成功", 1, null) : new Message("修改失败", 0, null);
    }

    @PutMapping("/updXh")
    public Message updXh(@RequestBody Map<String, Integer> map) {
        boolean result = this.danganService.updZhuangtai(map.get("id"), map.get("zhuangtai"));
        return result ? new Message("修改成功", 1, null) : new Message("修改失败", 0, null);
    }

    @PutMapping("/updZhuangtai")
    public Message updZhuangtai(@RequestBody Map<String, Integer> map) {
        boolean result = this.danganService.updZhuangtai(map.get("id"), map.get("zhuangtai"));
        return result ? new Message("修改成功", 1, null) : new Message("修改失败", 0, null);
    }

    @DeleteMapping("/delDangan")
    public Message delDangan(int id) {
        boolean result = this.danganService.delArchive(id);
        return result ? new Message("删除成功", 1, null) : new Message("删除失败", 0, null);
    }
}