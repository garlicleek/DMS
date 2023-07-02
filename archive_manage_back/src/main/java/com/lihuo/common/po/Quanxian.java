package com.lihuo.common.po;

public class Quanxian {
    private Integer id;

    private String mingcheng;

    private String jiekou;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMingcheng() {
        return mingcheng;
    }

    public void setMingcheng(String mingcheng) {
        this.mingcheng = mingcheng == null ? null : mingcheng.trim();
    }

    public String getJiekou() {
        return jiekou;
    }

    public void setJiekou(String jiekou) {
        this.jiekou = jiekou == null ? null : jiekou.trim();
    }
}