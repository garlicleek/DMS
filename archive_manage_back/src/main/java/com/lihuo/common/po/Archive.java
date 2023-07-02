package com.lihuo.common.po;

public class Archive {
    private Integer id;

    private String bianhao;

    private String mingcheng;

    private String lujing;

    private Integer leixing_id;

    private Integer laiyuan_id;

    private Integer miji_id;

    private Integer zhuangtai;

    private String danganjia;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBianhao() {
        return bianhao;
    }

    public void setBianhao(String bianhao) {
        this.bianhao = bianhao == null ? null : bianhao.trim();
    }

    public String getMingcheng() {
        return mingcheng;
    }

    public void setMingcheng(String mingcheng) {
        this.mingcheng = mingcheng == null ? null : mingcheng.trim();
    }

    public String getLujing() {
        return lujing;
    }

    public void setLujing(String lujing) {
        this.lujing = lujing == null ? null : lujing.trim();
    }

    public Integer getLeixing_id() {
        return leixing_id;
    }

    public void setLeixing_id(Integer leixing_id) {
        this.leixing_id = leixing_id;
    }

    public Integer getLaiyuan_id() {
        return laiyuan_id;
    }

    public void setLaiyuan_id(Integer laiyuan_id) {
        this.laiyuan_id = laiyuan_id;
    }

    public Integer getMiji_id() {
        return miji_id;
    }

    public void setMiji_id(Integer miji_id) {
        this.miji_id = miji_id;
    }

    public Integer getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(Integer zhuangtai) {
        this.zhuangtai = zhuangtai;
    }

    public String getDanganjia() {
        return danganjia;
    }

    public void setDanganjia(String danganjia) {
        this.danganjia = danganjia;
    }
}