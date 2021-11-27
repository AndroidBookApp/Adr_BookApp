package com.example.app_readbook.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class favorite {
    @SerializedName("idMember")
    @Expose
    private String idMember;
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

    private int clickDelete;

    public int isClickDelete() {
        return clickDelete;
    }

    public void setClickDelete(int clickDelete) {
        this.clickDelete = clickDelete;
    }

    public String getIdMember() {
        return idMember;
    }

    public void setIdMember(String idMember) {
        this.idMember = idMember;
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

}
