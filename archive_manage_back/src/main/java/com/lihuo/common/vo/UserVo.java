package com.lihuo.common.vo;

public class UserVo {
    private Integer id;

    private String username;

    private String password;

    private Integer role;

    private String mingcheng;

    private Integer bumen_id;

    private String bumen;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getMingcheng() {
        return mingcheng;
    }

    public void setMingcheng(String mingcheng) {
        this.mingcheng = mingcheng;
    }

    public Integer getBumen_id() {
        return bumen_id;
    }

    public void setBumen_id(Integer bumen_id) {
        this.bumen_id = bumen_id;
    }

    public String getBumen() {
        return bumen;
    }

    public void setBumen(String bumen) {
        this.bumen = bumen;
    }
}