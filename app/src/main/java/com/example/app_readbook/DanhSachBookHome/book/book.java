package com.example.app_readbook.DanhSachBookHome.book;

import java.io.Serializable;

public class book implements Serializable {
    private int resourceId;
    private String title;
    public book(int resourceId, String title) {
        this.resourceId = resourceId;
        this.title = title;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}