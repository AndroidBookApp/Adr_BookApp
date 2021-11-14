package com.example.app_readbook.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("idMember")
    @Expose
    private String idMember;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("MemberName")
    @Expose
    private String MemberName;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("email")
    @Expose
    private String Email;
    @SerializedName("Gioitinh")
    @Expose
    private String Gioitinh;
    @SerializedName("Ngaysinh")
    @Expose
    private String Ngaysinh;
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("ImgAvatar")
    @Expose
    private String imgAvatar;
    @SerializedName("ImgBia")
    @Expose
    private String imgBia;

    public User(String idMember, String username, String memberName, String password, String email, String gioitinh, String ngaysinh, String success, String message, String imgAvatar, String imgBia) {
        this.idMember = idMember;
        this.username = username;
        MemberName = memberName;
        this.password = password;
        Email = email;
        Gioitinh = gioitinh;
        Ngaysinh = ngaysinh;
        this.success = success;
        this.message = message;
        this.imgAvatar = imgAvatar;
        this.imgBia = imgBia;
    }

    public String getIdMember() {
        return idMember;
    }

    public void setIdMember(String idMember) {
        this.idMember = idMember;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMemberName() {
        return MemberName;
    }

    public void setMemberName(String memberName) {
        MemberName = memberName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getGioitinh() {
        return Gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        Gioitinh = gioitinh;
    }

    public String getNgaysinh() {
        return Ngaysinh;
    }

    public void setNgaysinh(String ngaysinh) {
        Ngaysinh = ngaysinh;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getImgAvatar() {
        return imgAvatar;
    }

    public void setImgAvatar(String imgAvatar) {
        this.imgAvatar = imgAvatar;
    }

    public String getImgBia() {
        return imgBia;
    }

    public void setImgBia(String imgBia) {
        this.imgBia = imgBia;
    }

    @Override
    public String toString() {
        return "User{" +
                "idMember='" + idMember + '\'' +
                ", username='" + username + '\'' +
                ", MemberName='" + MemberName + '\'' +
                ", password='" + password + '\'' +
                ", Email='" + Email + '\'' +
                ", Gioitinh='" + Gioitinh + '\'' +
                ", Ngaysinh='" + Ngaysinh + '\'' +
                ", success='" + success + '\'' +
                ", message='" + message + '\'' +
                ", imgAvatar='" + imgAvatar + '\'' +
                ", imgBia='" + imgBia + '\'' +
                '}';
    }
}
