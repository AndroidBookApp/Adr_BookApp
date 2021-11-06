package com.example.app_readbook.test_home;

import java.util.List;

public class all {
    int type;
    List<name_item> name_items;
    List<book_item> bookItemList;

    public all(int type, List<name_item> name_items, List<book_item> bookItemList) {
        this.type = type;
        this.name_items = name_items;
        this.bookItemList = bookItemList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<name_item> getName_items() {
        return name_items;
    }

    public void setName_items(List<name_item> name_items) {
        this.name_items = name_items;
    }

    public List<book_item> getBookItemList() {
        return bookItemList;
    }

    public void setBookItemList(List<book_item> bookItemList) {
        this.bookItemList = bookItemList;
    }
}
