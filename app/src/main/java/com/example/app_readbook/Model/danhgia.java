package com.example.app_readbook.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class danhgia implements Serializable {
    @SerializedName("idMember")
    @Expose
    private String idMember;
    @SerializedName("MemberName")
    @Expose
    private String memberName;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("ImgAvatar")
    @Expose
    private String imgAvatar;
    @SerializedName("idSach")
    @Expose
    private String idSach;
    @SerializedName("idDanhgia")
    @Expose
    private String idDanhgia;
    @SerializedName("Noidung")
    @Expose
    private String noidung;
    @SerializedName("Thoigian")
    @Expose
    private String thoigian;

    public String getIdMember() {
        return idMember;
    }

    public void setIdMember(String idMember) {
        this.idMember = idMember;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImgAvatar() {
        return imgAvatar;
    }

    public void setImgAvatar(String imgAvatar) {
        this.imgAvatar = imgAvatar;
    }

    public String getIdSach() {
        return idSach;
    }

    public void setIdSach(String idSach) {
        this.idSach = idSach;
    }

    public String getIdDanhgia() {
        return idDanhgia;
    }

    public void setIdDanhgia(String idDanhgia) {
        this.idDanhgia = idDanhgia;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }
}
