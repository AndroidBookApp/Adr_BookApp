package com.example.app_readbook.View.chapter;

public class NameChapter {
    private String chapter;
    private String book_min;
    private String book_max;

    public NameChapter(String chapter, String book_min, String book_max) {
        this.chapter = chapter;
        this.book_min = book_min;
        this.book_max = book_max;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getBook_min() {
        return book_min;
    }

    public void setBook_min(String book_min) {
        this.book_min = book_min;
    }

    public String getBook_max() {
        return book_max;
    }

    public void setBook_max(String book_max) {
        this.book_max = book_max;
    }
}