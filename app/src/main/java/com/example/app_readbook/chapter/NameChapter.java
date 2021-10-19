package com.example.app_readbook.chapter;

public class NameChapter {
    private String chapter;
    private String book;

    public NameChapter(String chapter, String book) {
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
