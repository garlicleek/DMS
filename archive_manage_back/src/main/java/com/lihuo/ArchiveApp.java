package com.lihuo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan("com.lihuo.common.dao")
@ServletComponentScan
public class ArchiveApp {
    public static void main(String[] args) {
        SpringApplication.run(ArchiveApp.class);
    }
}
