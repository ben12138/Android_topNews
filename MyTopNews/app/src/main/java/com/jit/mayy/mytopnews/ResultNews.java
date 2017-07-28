package com.jit.mayy.mytopnews;

import com.jit.mayy.mytopnews.domain.News;

import java.io.Serializable;
import java.util.List;

public class ResultNews implements Serializable {
    private List<News> list;

    public List<News> getList() {
        return list;
    }

    public void setList(List<News> list) {
        this.list = list;
    }
}
