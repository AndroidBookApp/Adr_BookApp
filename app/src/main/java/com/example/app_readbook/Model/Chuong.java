package com.example.app_readbook.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Chuong  implements Serializable {
    @SerializedName("idChuong")
    @Expose
    private String idChuong;
    @SerializedName("TenChuong")
    @Expose
    private String tenChuong;
    @SerializedName("idSach")
    @Expose
    private String idSach;
    @SerializedName("IdChitietsach")
    @Expose
    private String idChitietsach;
    @SerializedName("Sotrang")
    @Expose
    private String sotrang;

    public String getIdChuong() {
        return idChuong;
    }

    public void setIdChuong(String idChuong) {
        this.idChuong = idChuong;
    }

    public String getTenChuong() {
        return tenChuong;
    }

    public void setTenChuong(String tenChuong) {
        this.tenChuong = tenChuong;
    }

    public String getIdSach() {
        return idSach;
    }

    public void setIdSach(String idSach) {
        this.idSach = idSach;
    }

    public String getIdChitietsach() {
        return idChitietsach;
    }

    public void setIdChitietsach(String idChitietsach) {
        this.idChitietsach = idChitietsach;
    }

    public String getSotrang() {
        return sotrang;
    }

    public void setSotrang(String sotrang) {
        this.sotrang = sotrang;
    }
}
