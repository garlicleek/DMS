package com.lihuo.common.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ArchiveHistory {
    private Integer id;

    private Integer archive_id;

    private Integer caozuo_id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;

    private String beizhu;

    private Integer user_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArchive_id() {
        return archive_id;
    }

    public void setArchive_id(Integer archive_id) {
        this.archive_id = archive_id;
    }

    public Integer getCaozuo_id() {
        return caozuo_id;
    }

    public void setCaozuo_id(Integer caozuo_id) {
        this.caozuo_id = caozuo_id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getBeizhu() {
        return beizhu;
    }

    public void setBeizhu(String beizhu) {
        this.beizhu = beizhu == null ? null : beizhu.trim();
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }
}