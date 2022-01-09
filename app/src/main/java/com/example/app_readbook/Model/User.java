package com.example.app_readbook.Model;

import android.text.TextUtils;
import android.util.Patterns;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {
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
    @SerializedName("idquyen")
    @Expose
    private String idquyen;



    public String getIdquyen() {
        return idquyen;
    }

    public void setIdquyen(String idquyen) {
        this.idquyen = idquyen;
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
    public boolean isValidEmail()
    {
        return !TextUtils.isEmpty(Email) && Patterns.EMAIL_ADDRESS.matcher(Email).matches();
    }
    public boolean isValidPassword()
    {
        return !TextUtils.isEmpty(password) && password.length() >=6 ;
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
