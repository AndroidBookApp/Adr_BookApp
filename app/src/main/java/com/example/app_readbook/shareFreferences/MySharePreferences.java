package com.example.app_readbook.shareFreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharePreferences {
    private static final String SHARE_USER= "SHARE_USER";
    private static final String SACH_SHARE= "SACH_BOOK";
    private Context mContext;

    public MySharePreferences(Context mContext) {
        this.mContext = mContext;
    }

    public String getStringUser(String key)
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARE_USER , Context.MODE_PRIVATE);
        return sharedPreferences.getString("idMember" , null);
    }
    public void putStringUser(String key , String valueUser)
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARE_USER , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("idMember" ,valueUser);
        editor.putString("username" ,valueUser);
        editor.putString("MemberName" ,valueUser);
        editor.putString("password" ,valueUser);
        editor.putString("Email" ,valueUser);
        editor.putString("Gioitinh" ,valueUser);
        editor.putString("Ngaysinh" ,valueUser);
        editor.putString("Success" ,valueUser);
        editor.putString("Message" ,valueUser);
        editor.putString("ImgAvatar" ,valueUser);
        editor.putString("ImgBia" ,valueUser);
        editor.putBoolean("login",true);
        editor.apply();
    }
    public boolean saveLogin(String key)
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SACH_SHARE , Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("login" , false);
    }
public String getSach(String key)
{
    SharedPreferences sharedPreferences = mContext.getSharedPreferences(SACH_SHARE , Context.MODE_PRIVATE);
    return sharedPreferences.getString("idSach" , null);
}
public void putSach(String key , String valueSach)
{
    SharedPreferences sharedPreferences = mContext.getSharedPreferences(SACH_SHARE , Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = sharedPreferences.edit();
    editor.putString("idSach" , valueSach);
    editor.putString("idDanhmuc" , valueSach);
    editor.putString("Tensach" , valueSach);
    editor.putString("Tacgia" , valueSach);
    editor.putString("NXB" , valueSach);
    editor.putString("NgayDang" , valueSach);
    editor.putString("TomtatND" , valueSach);
    editor.putString("Luotxem" , valueSach);
    editor.putString("Feedback" , valueSach);
    editor.putString("Sotrang" , valueSach);
    editor.putString("Favorite" , valueSach);
    editor.putString("IdChitietsach" , valueSach);
    editor.putBoolean("readbook",true);
    editor.apply();

}
}
