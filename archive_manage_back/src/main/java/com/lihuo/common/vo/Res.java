package com.lihuo.common.vo;

public class Res {
    private int errno;
    private Data data;
    private String message;

    public Res(int errno, Data data, String message) {
        this.errno = errno;
        this.data = data;
        this.message = message;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
