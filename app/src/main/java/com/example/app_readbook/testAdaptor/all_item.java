package com.example.app_readbook.testAdaptor;

import java.util.List;

public class all_item {
    int type;
    List<book_item> bookItemList;
    List<name_item> nameItemList;

    public all_item(int type, List<book_item> bookItemList, List<name_item> nameItemList) {
        this.type = type;
        this.bookItemList = bookItemList;
        this.nameItemList = nameItemList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<book_item> getBookItemList() {
        return bookItemList;
    }

    public void setBookItemList(List<book_item> bookItemList) {
        this.bookItemList = bookItemList;
    }

    public List<name_item> getNameItemList() {
        return nameItemList;
    }

    public void setNameItemList(List<name_item> nameItemList) {
        this.nameItemList = nameItemList;
    }
}
