package com.example.app_readbook.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;


public class Sach implements Serializable {
    @SerializedName("idSach")
    @Expose
    private String idSach;
    @SerializedName("idDanhmuc")
    @Expose
    private String idDanhmuc;
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
    @SerializedName("sochuong")
    @Expose
    private String sochuong;
    @SerializedName("Favorite")
    @Expose
    private String favorite;
    @SerializedName("IdChitietsach")
    @Expose
    private String idChitietsach;

    public String getIdSach() {
        return idSach;
    }

    public void setIdSach(String idSach) {
        this.idSach = idSach;
    }

    public String getIdDanhmuc() {
        return idDanhmuc;
    }

    public void setIdDanhmuc(String idDanhmuc) {
        this.idDanhmuc = idDanhmuc;
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

    public String getSochuong() {
        return sochuong;
    }

    public void setSochuong(String sochuong) {
        this.sochuong = sochuong;
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