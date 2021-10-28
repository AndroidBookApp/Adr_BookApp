package com.example.app_readbook.list_book;

public class list_bookNew {
    private int boo_new;
    private String name_new;
    private String tacgia_new;
    private int icon_favorite_new;
    private String soTrang;

    public list_bookNew(int boo_new, String name_new, String tacgia_new, int icon_favorite_new, String soTrang) {
        this.boo_new = boo_new;
        this.name_new = name_new;
        this.tacgia_new = tacgia_new;
        this.icon_favorite_new = icon_favorite_new;
        this.soTrang = soTrang;
    }

    public String getSoTrang() {
        return soTrang;
    }

    public void setSoTrang(String soTrang) {
        this.soTrang = soTrang;
    }

    public int getBoo_new() {
        return boo_new;
    }

    public void setBoo_new(int boo_new) {
        this.boo_new = boo_new;
    }

    public String getName_new() {
        return name_new;
    }

    public void setName_new(String name_new) {
        this.name_new = name_new;
    }

    public String getTacgia_new() {
        return tacgia_new;
    }

    public void setTacgia_new(String tacgia_new) {
        this.tacgia_new = tacgia_new;
    }

    public int getIcon_favorite_new() {
        return icon_favorite_new;
    }

    public void setIcon_favorite_new(int icon_favorite_new) {
        this.icon_favorite_new = icon_favorite_new;
    }
}
