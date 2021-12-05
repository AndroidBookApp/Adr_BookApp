package com.example.app_readbook.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Chapter implements Serializable {
    @SerializedName("Tensach")
    @Expose
    private String tensach;
    @SerializedName("TenChuong")
    @Expose
    private String tenChuong;
    @SerializedName("idChitietchuong")
    @Expose
    private String idChitietchuong;
    @SerializedName("idChuong")
    @Expose
    private String idChuong;
    @SerializedName("idSach")
    @Expose
    private String idSach;
    @SerializedName("Noidung")
    @Expose
    private String noidung;
    @SerializedName("imgSach")
    @Expose
    private String imgSach;

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

    public String getTenChuong() {
        return tenChuong;
    }

    public void setTenChuong(String tenChuong) {
        this.tenChuong = tenChuong;
    }

    public String getIdChitietchuong() {
        return idChitietchuong;
    }

    public void setIdChitietchuong(String idChitietchuong) {
        this.idChitietchuong = idChitietchuong;
    }

    public String getIdChuong() {
        return idChuong;
    }

    public void setIdChuong(String idChuong) {
        this.idChuong = idChuong;
    }

    public String getIdSach() {
        return idSach;
    }

    public void setIdSach(String idSach) {
        this.idSach = idSach;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

}
