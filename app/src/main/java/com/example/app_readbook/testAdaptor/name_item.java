package com.example.app_readbook.testAdaptor;

public class name_item {
    String nameBook;
    String sl;
    String all;

    public name_item(String nameBook, String sl, String all) {
        this.nameBook = nameBook;
        this.sl = sl;
        this.all = all;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getSl() {
        return sl;
    }

    public void setSl(String sl) {
        this.sl = sl;
    }

    public String getAll() {
        return all;
    }

    public void setAll(String all) {
        this.all = all;
    }
}
