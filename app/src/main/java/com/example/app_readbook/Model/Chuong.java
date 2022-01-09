package com.example.app_readbook.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Chuong  implements Serializable {
    @SerializedName("idChuong")
    @Expose
    private String idChuong;
    @SerializedName("idSach")
    @Expose
    private String idSach;
    @SerializedName("TenChuong")
    @Expose
    private String tenChuong;
    @SerializedName("noidung")
    @Expose
    private String noidung;
    @SerializedName("imgSach")
    @Expose
    private String imgSach;

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

    public String getTenChuong() {
        return tenChuong;
    }

    public void setTenChuong(String tenChuong) {
        this.tenChuong = tenChuong;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getImgSach() {
        return imgSach;
    }

    public void setImgSach(String imgSach) {
        this.imgSach = imgSach;
    }

    @Override
    public String toString() {
        return "Chuong{" +
                "idChuong='" + idChuong + '\'' +
                ", idSach='" + idSach + '\'' +
                ", tenChuong='" + tenChuong + '\'' +
                ", noidung='" + noidung + '\'' +
                ", imgSach='" + imgSach + '\'' +
                '}';
    }
}
