package com.example.app_readbook.test_home;

public class book_item {
    String idSach;
    String Tensach;
    String Tentacgia ;
    int  NgayXB;
    String TomtatND;
    String IMGsach;
    String sotrang;
    String Luotxem;
    String Feedback;
    String favorite;

    public book_item(String idSach, String tensach, String tentacgia, int ngayXB, String tomtatND, String IMGsach, String sotrang, String luotxem, String feedback, String favorite) {
        this.idSach = idSach;
        Tensach = tensach;
        Tentacgia = tentacgia;
        NgayXB = ngayXB;
        TomtatND = tomtatND;
        this.IMGsach = IMGsach;
        this.sotrang = sotrang;
        Luotxem = luotxem;
        Feedback = feedback;
        this.favorite = favorite;
    }

    public String getIdSach() {
        return idSach;
    }

    public void setIdSach(String idSach) {
        this.idSach = idSach;
    }

    public String getTensach() {
        return Tensach;
    }

    public void setTensach(String tensach) {
        Tensach = tensach;
    }

    public String getTentacgia() {
        return Tentacgia;
    }

    public void setTentacgia(String tentacgia) {
        Tentacgia = tentacgia;
    }

    public int getNgayXB() {
        return NgayXB;
    }

    public void setNgayXB(int ngayXB) {
        NgayXB = ngayXB;
    }

    public String getTomtatND() {
        return TomtatND;
    }

    public void setTomtatND(String tomtatND) {
        TomtatND = tomtatND;
    }

    public String getIMGsach() {
        return IMGsach;
    }

    public void setIMGsach(String IMGsach) {
        this.IMGsach = IMGsach;
    }

    public String getSotrang() {
        return sotrang;
    }

    public void setSotrang(String sotrang) {
        this.sotrang = sotrang;
    }

    public String getLuotxem() {
        return Luotxem;
    }

    public void setLuotxem(String luotxem) {
        Luotxem = luotxem;
    }

    public String getFeedback() {
        return Feedback;
    }

    public void setFeedback(String feedback) {
        Feedback = feedback;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }
}
