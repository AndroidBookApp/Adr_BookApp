package com.example.app_readbook.test_home;

import java.util.List;

public class name_item {
    String idDanhmuc;
    String Tendanhmuc;
    List<book_item> bookItemList;

    public name_item(String idDanhmuc, String tendanhmuc, List<book_item> bookItemList) {
        this.idDanhmuc = idDanhmuc;
        Tendanhmuc = tendanhmuc;
        this.bookItemList = bookItemList;
    }

    public String getIdDanhmuc() {
        return idDanhmuc;
    }

    public void setIdDanhmuc(String idDanhmuc) {
        this.idDanhmuc = idDanhmuc;
    }

    public String getTendanhmuc() {
        return Tendanhmuc;
    }

    public void setTendanhmuc(String tendanhmuc) {
        Tendanhmuc = tendanhmuc;
    }

    public List<book_item> getBookItemList() {
        return bookItemList;
    }

    public void setBookItemList(List<book_item> bookItemList) {
        this.bookItemList = bookItemList;
    }
}
