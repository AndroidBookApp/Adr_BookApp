package com.example.app_readbook.DataBookNew;

import java.io.Serializable;
import java.util.ArrayList;

public class ChapterName implements Serializable {
    private String chapter;
    private String book;
    private ArrayList<PageModel> page;

    public ChapterName(ArrayList<PageModel> page) {
        this.page = page;
    }

    public ArrayList<PageModel> getPage() {
        return page;
    }

    public void setPage(ArrayList<PageModel> page) {
        this.page = page;
    }

    public ChapterName(String chapter, String book) {
        this.chapter = chapter;
        this.book = book;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }
}
