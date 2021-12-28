package com.example.app_readbook.shareFreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.app_readbook.Model.Chuong;
import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.Model.User;
import com.example.app_readbook.Model.danhgia;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static final String OBJECT_USER = "OBJECT_USER";

    private static final String OBJECT_BOOK = "OBJECT_BOOK";
    private static final String OBJECT_DANHGIA = "OBJECT_DANHGIA";
    private static final String STRING_FAVORITE = "STRING_FAVORITE";
    private static final String FAVORITE = "FAVORITE";
    private static final String OBJECT_LOGIN = "OBJECT_LOGIN";
    private static final String CHAPTER = "CHAPTER";
    private static DataManager instance;
    private static Context mContext;
    private MySharePreferences mySharePreferences;
    private SharedPreferences sharedPreferences;

    public static void init(Context context) {
        instance = new DataManager();
        instance.mySharePreferences = new MySharePreferences(context);
    }

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public static void saveUserName(User user) {
        Gson gson = new Gson();
        String srtUser = gson.toJson(user);
        DataManager.getInstance().mySharePreferences.putStringUser(OBJECT_USER, srtUser);
    }
    public static User loadUser() {
        String strUser = DataManager.getInstance().mySharePreferences.getStringUser(OBJECT_USER);
        Gson gson = new Gson();
        User user = gson.fromJson(strUser, User.class);
        return user;
    }



    public static void saveObjectSach(Sach sach) {
        Gson gson = new Gson();
        String strUser = gson.toJson(sach);
        DataManager.getInstance().mySharePreferences.putSach(OBJECT_BOOK, strUser);
    }

    public static Sach loadObjectSach() {
        String strUser = DataManager.getInstance().mySharePreferences.getSach(OBJECT_BOOK);
        Gson gson = new Gson();
        Sach sach = gson.fromJson(strUser, Sach.class);
        return sach;
    }

    public static void saveDanhGia(List<danhgia> mDanhgia) {
        Gson gson = new Gson();
        String strDanhgia = gson.toJson(mDanhgia);
        DataManager.getInstance().mySharePreferences.putDanhGia(OBJECT_DANHGIA, strDanhgia);
    }

    public static List<danhgia> loadDanhgia() {
        String strDanhgia = DataManager.getInstance().mySharePreferences.getDanhGia(OBJECT_DANHGIA);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<danhgia>>() {
        }.getType();
        List<danhgia> mDanhgia = gson.fromJson(strDanhgia, type);
        return mDanhgia;
    }

    //Lưu Chương
    public static void sChapter(Chuong key)
    {
        Gson gson = new Gson();
        String strFavorite = gson.toJson(key);
        DataManager.getInstance().mySharePreferences.setChapter(CHAPTER , strFavorite);
    }
    // Load ra dữ liệu chương
    public static Chuong lChapter()
    {
        String strUser = DataManager.getInstance().mySharePreferences.getChapter(CHAPTER);
        Gson gson = new Gson();
        Chuong chuong = gson.fromJson(strUser, Chuong.class);
        return chuong;
    }
}
