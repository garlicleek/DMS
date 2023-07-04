package com.lihuo.common.controller;

import com.lihuo.common.vo.Data;
import com.lihuo.common.vo.Res;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/upload")
@CrossOrigin
public class UploadController {
    @RequestMapping("/file")
    public Res uploadFile(MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {
        String filename = System.currentTimeMillis() + "." + file.getOriginalFilename().split("\\.")[1];
        //String dirPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\file\\";
        String dirPath = "/home/java/static/file/";
        String realPath = dirPath + filename;
        String url = "/file/" + filename;
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File desFile = new File(realPath);
        if (desFile.exists()) {
            desFile.delete();
        }
        file.transferTo(desFile);
        return new Res(0, new Data(url, url, url, null), null);
    }

    @RequestMapping("/video")
    public Res uploadVideo(MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {

        String filename = System.currentTimeMillis() + ".mp4";
        String dirPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\file\\";
        //String dirPath = "/home/java/static/file/";
        String realPath = dirPath + filename;
        String url = "/file/" + filename;
        File dirFile = new File(dirPath);
        if (!dirFile.exists()) {
            dirFile.mkdir();
        }
        File desFile = new File(realPath);
        if (desFile.exists()) {
            desFile.delete();
        }
        file.transferTo(desFile);
        return new Res(0, new Data(url, url, url, null), null);
    }
}
