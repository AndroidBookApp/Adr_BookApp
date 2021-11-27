package com.example.app_readbook.shareFreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.Model.User;
import com.example.app_readbook.Model.danhgia;
import com.example.app_readbook.Model.favorite;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataManager {

    private static final String OBJECT_USER = "OBJECT_USER";

    private static final String OBJECT_BOOK = "OBJECT_BOOK";
    private static final String OBJECT_DANHGIA = "OBJECT_DANHGIA";
    private static final String STRING_FAVORITE = "STRING_FAVORITE";
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

//    public static void savUser(String user) {
//        DataManager.getInstance().mySharePreferences.putStringUser(OBJECT_USER, user);
//    }
//
//    public static String loadUsers() {
//        return DataManager.getInstance().mySharePreferences.getStringUser(OBJECT_USER);
//    }
    public static void saveSach(List<Sach> sach) {
        Gson gson = new Gson();
        JsonArray jsonArray = gson.toJsonTree(sach).getAsJsonArray();
        String strSach = jsonArray.toString();
        DataManager.getInstance().mySharePreferences.putSach(OBJECT_BOOK, strSach);
    }

    public static List<Sach> loadSach() {
        String strSach = DataManager.getInstance().mySharePreferences.getSach(OBJECT_BOOK);
        Gson gson = new Gson();
        ArrayList<Sach> sach = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(strSach);
            JSONObject jsonObject;
            Sach mSach;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                mSach = gson.fromJson(jsonObject.toString(), Sach.class);
                sach.add(mSach);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sach;
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

    public boolean isLogin() {
        return DataManager.getInstance().mySharePreferences.saveLogin(OBJECT_USER);
    }

    public static void saveFavorite(favorite favorites) {
        Gson gson = new Gson();
        String strFavorite = gson.toJson(favorites);
        DataManager.getInstance().mySharePreferences.SaveFavorite(STRING_FAVORITE, strFavorite);
    }

    public static List<favorite> loadFavorite() {
        String strFavorite = DataManager.getInstance().mySharePreferences.LoadFavorite(STRING_FAVORITE);
        Gson gson = new Gson();
        List<favorite> mFavorite = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(strFavorite);
            JSONObject jsonObject;
            favorite favorite;
            for(int i = 0 ; i< jsonArray.length() ; i++)
            {
                jsonObject = jsonArray.getJSONObject(i);
                favorite = gson.fromJson(jsonObject.toString(), com.example.app_readbook.Model.favorite.class);
                mFavorite.add(favorite);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return mFavorite;
    }
    public void logOut() {
        sharedPreferences = mContext.getSharedPreferences(OBJECT_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
    public static void sFavorite(String key)
    {
        DataManager.getInstance().mySharePreferences.SaveFavorite(STRING_FAVORITE , key);
    }
    public static String lFavorite()
    {
        return DataManager.getInstance().mySharePreferences.LoadFavorite(STRING_FAVORITE);
    }
}
