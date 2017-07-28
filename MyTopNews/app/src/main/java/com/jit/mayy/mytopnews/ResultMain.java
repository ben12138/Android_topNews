package com.jit.mayy.mytopnews;

import java.io.Serializable;
import java.util.List;

public class ResultMain implements Serializable{

	/**
	 * "code": 200,
     * "msg": "success",
     * "newslist":
	 */
	private int code;
	private String msg;
	private List<ResultItem> newslist;

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public List<ResultItem> getNewslist() {
		return newslist;
	}
	public void setNewslist(List<ResultItem> newslist) {
		this.newslist = newslist;
	}
	
	
	
}
