package com.example.app_readbook.Lòng_RecyclerView;

import com.example.app_readbook.list_book.list_book;

import java.util.List;

public class all_item {
    int type;
    List<list_book> list_bookList;

    public all_item(int type, List<list_book> list_bookList) {
        this.type = type;
        this.list_bookList = list_bookList;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<list_book> getList_bookList() {
        return list_bookList;
    }

    public void setList_bookList(List<list_book> list_bookList) {
        this.list_bookList = list_bookList;
    }
}
