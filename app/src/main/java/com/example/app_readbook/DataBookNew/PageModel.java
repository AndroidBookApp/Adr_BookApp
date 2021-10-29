package com.example.app_readbook.DataBookNew;

import java.io.Serializable;

public class PageModel implements Serializable {
    private String Content;

    public PageModel(String content) {
        Content = content;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
