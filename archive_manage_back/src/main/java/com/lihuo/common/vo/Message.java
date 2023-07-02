package com.lihuo.common.vo;

/**
 * 前台实体类
 * */
public class Message {

    private String message;
    private int state;
    private Object data;

    public Message() {
    }

    public Message(String message, int state, Object data) {
        super();
        this.message = message;
        this.state = state;
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
