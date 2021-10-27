package com.example.app_readbook.list_book;

public class list_book {
    private int resourceId ;
    private String title;
    private String tac_gia;
    private int icon;
    private String icon_comment;
    private String icon_view ;
    private String icon_book;

    public list_book(int resourceId, String title, String tac_gia, int icon, String icon_comment, String icon_view, String icon_book) {
        this.resourceId = resourceId;
        this.title = title;
        this.tac_gia = tac_gia;
        this.icon = icon;
        this.icon_comment = icon_comment;
        this.icon_view = icon_view;
        this.icon_book = icon_book;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTac_gia() {
        return tac_gia;
    }

    public void setTac_gia(String tac_gia) {
        this.tac_gia = tac_gia;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getIcon_comment() {
        return icon_comment;
    }

    public void setIcon_comment(String icon_comment) {
        this.icon_comment = icon_comment;
    }

    public String getIcon_view() {
        return icon_view;
    }

    public void setIcon_view(String icon_view) {
        this.icon_view = icon_view;
    }

    public String getIcon_book() {
        return icon_book;
    }

    public void setIcon_book(String icon_book) {
        this.icon_book = icon_book;
    }

}
