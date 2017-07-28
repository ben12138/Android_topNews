package com.jit.mayy.mytopnews;

import java.io.Serializable;

public class ResultItem implements Serializable{

	/**
	 *  "ctime": "2017-06-07 10:12",
	 *	"title": "标题内容",
	 *	"description": "来源描述",
	 *	"picUrl": "http://news.sohu.com/20170607/n495984947.shtml",
	 *	"url":
	 */
	private String ctime;
	private String title;
	private String description;
	private String picUrl;
	private String url;
	private String content;

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
