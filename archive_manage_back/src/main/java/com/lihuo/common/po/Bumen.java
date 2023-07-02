package com.lihuo.common.po;

public class Bumen {
    private Integer id;

    private String mingcheng;

    private String dengji;

    private Integer shangji_id;

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

    public String getDengji() {
        return dengji;
    }

    public void setDengji(String dengji) {
        this.dengji = dengji == null ? null : dengji.trim();
    }

    public Integer getShangji_id() {
        return shangji_id;
    }

    public void setShangji_id(Integer shangji_id) {
        this.shangji_id = shangji_id;
    }
}