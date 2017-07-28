package com.jit.mayy.mytopnews.domain;

public class News {
    private String newsId;
    private String title;
    private String description;
    private String picUrl;
    private String content;

    public News() {
    }

    public News(String newsId, String title, String description, String picUrl, String content) {
        this.newsId = newsId;
        this.title = title;
        this.description = description;
        this.picUrl = picUrl;
        this.content = content;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
