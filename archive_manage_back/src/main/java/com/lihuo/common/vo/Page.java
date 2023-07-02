package com.lihuo.common.vo;

import java.util.List;

public class Page {

	private final int code = 0;
	private String msg;
	private int count;
	private List<?> data;

	public Page() {
		super();
	}

	public Page(String msg, int count, List<?> data) {
		super();
		this.msg = msg;
		this.count = count;
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public int getCode() {
		return code;
	}
}
