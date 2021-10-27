package com.example.app_readbook.fragment_pager.model_search;

import java.io.Serializable;

public class history implements Serializable {
    private int img_time;
    private String book ;
    private String img_clear;

    public history(int img_time, String book, String img_clear) {
        this.img_time = img_time;
        this.book = book;
        this.img_clear = img_clear;
    }

    public int getImg_time() {
        return img_time;
    }

    public void setImg_time(int img_time) {
        this.img_time = img_time;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getImg_clear() {
        return img_clear;
    }

    public void setImg_clear(String img_clear) {
        this.img_clear = img_clear;
    }
}
