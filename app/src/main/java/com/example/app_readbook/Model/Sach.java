package com.example.app_readbook.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Sach {
    @SerializedName("idSach")
    @Expose
    private String idSach;
    @SerializedName("imgSach")
    @Expose
    private String imgSach;
    @SerializedName("Tensach")
    @Expose
    private String tensach;
    @SerializedName("Tacgia")
    @Expose
    private String tacgia;
    @SerializedName("NXB")
    @Expose
    private String nxb;
    @SerializedName("idDanhmuc")
    @Expose
    private String idDanhmuc;
    @SerializedName("NgayDang")
    @Expose
    private String ngayDang;
    @SerializedName("TomtatND")
    @Expose
    private String tomtatND;
    @SerializedName("Luotxem")
    @Expose
    private String luotxem;
    @SerializedName("Feedback")
    @Expose
    private String feedback;
    @SerializedName("Sotrang")
    @Expose
    private String sotrang;
    @SerializedName("Favorite")
    @Expose
    private String favorite;
    @SerializedName("IdChitietsach")
    @Expose
    private String idChitietsach;

    public Sach(String idSach, String imgSach, String tensach, String tacgia, String nxb, String idDanhmuc, String ngayDang, String tomtatND, String luotxem, String feedback, String sotrang, String favorite, String idChitietsach) {
        this.idSach = idSach;
        this.imgSach = imgSach;
        this.tensach = tensach;
        this.tacgia = tacgia;
        this.nxb = nxb;
        this.idDanhmuc = idDanhmuc;
        this.ngayDang = ngayDang;
        this.tomtatND = tomtatND;
        this.luotxem = luotxem;
        this.feedback = feedback;
        this.sotrang = sotrang;
        this.favorite = favorite;
        this.idChitietsach = idChitietsach;
    }

    public String getIdSach() {
        return idSach;
    }

    public void setIdSach(String idSach) {
        this.idSach = idSach;
    }

    public String getImgSach() {
        return imgSach;
    }

    public void setImgSach(String imgSach) {
        this.imgSach = imgSach;
    }

    public String getTensach() {
        return tensach;
    }

    public void setTensach(String tensach) {
        this.tensach = tensach;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public String getNxb() {
        return nxb;
    }

    public void setNxb(String nxb) {
        this.nxb = nxb;
    }

    public String getIdDanhmuc() {
        return idDanhmuc;
    }

    public void setIdDanhmuc(String idDanhmuc) {
        this.idDanhmuc = idDanhmuc;
    }

    public String getNgayDang() {
        return ngayDang;
    }

    public void setNgayDang(String ngayDang) {
        this.ngayDang = ngayDang;
    }

    public String getTomtatND() {
        return tomtatND;
    }

    public void setTomtatND(String tomtatND) {
        this.tomtatND = tomtatND;
    }

    public String getLuotxem() {
        return luotxem;
    }

    public void setLuotxem(String luotxem) {
        this.luotxem = luotxem;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getSotrang() {
        return sotrang;
    }

    public void setSotrang(String sotrang) {
        this.sotrang = sotrang;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getIdChitietsach() {
        return idChitietsach;
    }

    public void setIdChitietsach(String idChitietsach) {
        this.idChitietsach = idChitietsach;
    }
}