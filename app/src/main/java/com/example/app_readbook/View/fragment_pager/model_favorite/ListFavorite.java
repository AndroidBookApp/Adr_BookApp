package com.example.app_readbook.View.fragment_pager.model_favorite;

public class ListFavorite {
    private int resourceId ;
    private String title_name;
    private String tac_gia;
    private int icon_clear;

    public ListFavorite(int resourceId, String title_name, String tac_gia, int icon_clear) {
        this.resourceId = resourceId;
        this.title_name = title_name;
        this.tac_gia = tac_gia;
        this.icon_clear = icon_clear;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public String getTitle_name() {
        return title_name;
    }

    public void setTitle_name(String title_name) {
        this.title_name = title_name;
    }

    public String getTac_gia() {
        return tac_gia;
    }

    public void setTac_gia(String tac_gia) {
        this.tac_gia = tac_gia;
    }

    public int getIcon_clear() {
        return icon_clear;
    }

    public void setIcon_clear(int icon_clear) {
        this.icon_clear = icon_clear;
    }
}
