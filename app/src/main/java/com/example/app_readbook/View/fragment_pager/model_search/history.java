package com.example.app_readbook.View.fragment_pager.model_search;

import java.io.Serializable;

public class history implements Serializable {
    private int img_time;
    private String book ;
    private int img_clear;

    public history(int img_time, String book, int img_clear) {
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

    public int getImg_clear() {
        return img_clear;
    }

    public void setImg_clear(int img_clear) {
        this.img_clear = img_clear;
    }
}
