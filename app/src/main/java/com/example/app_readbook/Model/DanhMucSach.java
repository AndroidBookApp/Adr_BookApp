package com.example.app_readbook.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DanhMucSach  implements Serializable {
    @SerializedName("idDanhmuc")
    @Expose
    private String idDanhmuc;
    @SerializedName("Tendanhmuc")
    @Expose
    private String tendanhmuc;
    @SerializedName("sosach")
    @Expose
    private String sosach;
    public String getIdDanhmuc() {
        return idDanhmuc;
    }

    public void setIdDanhmuc(String idDanhmuc) {
        this.idDanhmuc = idDanhmuc;
    }

    public String getTendanhmuc() {
        return tendanhmuc;
    }

    public void setTendanhmuc(String tendanhmuc) {
        this.tendanhmuc = tendanhmuc;
    }

    public String getSosach() {
        return sosach;
    }

    public void setSosach(String sosach) {
        this.sosach = sosach;
    }

    @Override
    public String toString() {
        return "DanhMucSach{" +
                "idDanhmuc='" + idDanhmuc + '\'' +
                ", tendanhmuc='" + tendanhmuc + '\'' +
                ", sosach='" + sosach + '\'' +
                '}';
    }
}
