package com.example.app_readbook.shareFreferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class MySharePreferences {
    private static final String SHARE_USER = "SHARE_USER";
    private static final String SACH_SHARE = "SACH_BOOK";
    private static final String DANHGIA_SHARE = "DANHGIA";
    private static final String SAVE_FAVORITE = "SAVE_FAVORITE";
    private static final String MY_OPEN_APP = "MY_OPEN_APP";
    private static final String LIKE_FAVORITE ="LIKE_FAVORITE" ;
    private static final String SHARE_LOGIN_PASS = "SHARE_LOGIN_PASS";
    private static final String SHARE_LOGIN_USER = "SHARE_LOGIN_USER";
    private static final String SHARE_CHAPTER = "SHARE_CHAPTER";
    private static final String FAVORITE = "FAVORITE";
    private Context mContext;

    public MySharePreferences(Context mContext) {
        this.mContext = mContext;
    }

    public String getStringUser(String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARE_USER, Context.MODE_PRIVATE);
        return sharedPreferences.getString("idMember", null);
    }

    public void putStringUser(String key, String valueUser) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARE_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("idMember", valueUser);
        editor.putString("username", valueUser);
        editor.putString("MemberName", valueUser);
        editor.putString("password", valueUser);
        editor.putString("Email", valueUser);
        editor.putString("Gioitinh", valueUser);
        editor.putString("Ngaysinh", valueUser);
        editor.putString("Success", valueUser);
        editor.putString("Message", valueUser);
        editor.putString("ImgAvatar", valueUser);
        editor.putString("ImgBia", valueUser);
        editor.putString("idquyen", valueUser);
        editor.apply();
    }
    // lưu danh sách chương vào dữ liệu
    public void setChapter(String key , String value)
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARE_CHAPTER , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("idChuong" , value);
        editor.putString("TenChuong" , value);
        editor.putString("idSach" , value);
        editor.putString("noidung" , value);
        editor.apply();
    }
    //Lấy dữ liệu ra khi đã lưu vào
    public String getChapter(String key)
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARE_CHAPTER , Context.MODE_PRIVATE);
        return sharedPreferences.getString("idChuong" , null);
    }
    public String saveLoginUser(String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARE_LOGIN_USER, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }
    public String saveLoginPass(String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARE_LOGIN_PASS, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }
    public void putValueLoginUser(String key )
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARE_LOGIN_USER , 0);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username" ,key);
        editor.apply();
    }
    public void putBooleanValueLogin(String key )
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARE_LOGIN_PASS , 0);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("password" ,key);
        editor.apply();
    }
    public void putBooleanValue(String key , boolean value)
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MY_OPEN_APP , 0);
        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key ,value);
        editor.apply();
    }
    public boolean saveOpenApp(String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MY_OPEN_APP, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }
    public String getSach(String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SACH_SHARE, Context.MODE_PRIVATE);
        return sharedPreferences.getString("idSach", null);
    }

    public void putSach(String key, String valueSach) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SACH_SHARE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("idSach", valueSach);
        editor.putString("idDanhmuc", valueSach);
        editor.putString("Tensach", valueSach);
        editor.putString("Tacgia", valueSach);
        editor.putString("NXB", valueSach);
        editor.putString("NgayDang", valueSach);
        editor.putString("TomtatND", valueSach);
        editor.putString("Luotxem", valueSach);
        editor.putString("Feedback", valueSach);
        editor.putString("sochuong", valueSach);
        editor.putString("Favorite", valueSach);
        editor.putString("IdChitietsach", valueSach);
        editor.apply();

    }
    public String getDanhGia(String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(DANHGIA_SHARE, Context.MODE_PRIVATE);
        return sharedPreferences.getString("idMember", null);
    }
    public void putDanhGia(String key, String valueDanhGia) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(DANHGIA_SHARE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("idMember", valueDanhGia);
        editor.putString("MemberName", valueDanhGia);
        editor.putString("username", valueDanhGia);
        editor.putString("ImgAvatar", valueDanhGia);
        editor.putString("idSach", valueDanhGia);
        editor.putString("idDanhgia", valueDanhGia);
        editor.putString("Noidung", valueDanhGia);
        editor.putString("Thoigian", valueDanhGia);
        editor.apply();

    }


}
