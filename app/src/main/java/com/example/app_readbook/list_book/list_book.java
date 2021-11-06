package com.example.app_readbook.list_book;

public class list_book {
   String idSach;
   String Tensach;
   String idDanhmuc;
   String Tendanhmuc;
   String Tentacgia ;
   int  NgayXB;
   String TomtatND;
   String IMGsach;
   String sotrang;
   String Luotxem;
   String Feedback;
   String favorite;

    public list_book(String idSach, String tenSach, String idDanhmuc, String tendanhmuc, String tentacgia, int ngayXB, String tomtatND, String IMGsach, String sotrang, String luotxem, String feedback, String favorite) {
        this.idSach = idSach;
        Tensach = tenSach;
        this.idDanhmuc = idDanhmuc;
        Tendanhmuc = tendanhmuc;
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

    public String getTenSach() {
        return Tensach;
    }

    public void setTenSach(String tenSach) {
        Tensach = tenSach;
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
        sotrang = sotrang;
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
