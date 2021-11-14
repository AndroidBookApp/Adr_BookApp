package com.example.app_readbook.shareFreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.Model.User;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.List;

public class DataManager {

    private static final String OBJECT_USER = "OBJECT_USER";
    private static final String OBJECT_BOOK = "OBJECT_BOOK";
    private static DataManager instance;
    private static Context mContext;
    private MySharePreferences mySharePreferences;
    private SharedPreferences sharedPreferences;
    public static void init(Context context)
    {
        instance = new DataManager();
        instance.mySharePreferences = new MySharePreferences(context);
    }
    public static DataManager getInstance()
    {
        if (instance == null)
        {
            instance = new DataManager();
        }
        return instance;
    }


    public static void saveUserName(User user)
    {
       Gson gson = new Gson();
       String srtUser = gson.toJson(user);
       DataManager.getInstance().mySharePreferences.putStringUser(OBJECT_USER , srtUser);
    }
    public static User loadUser()
    {
        String strUser = DataManager.getInstance().mySharePreferences.getStringUser(OBJECT_USER);
        Gson gson = new Gson();
        User user = gson.fromJson(strUser , User.class);
        return user;
    }
    public static void saveSach(List<Sach> sach)
    {
        Gson gson = new Gson();
        String srtUser = gson.toJson(sach);
        DataManager.getInstance().mySharePreferences.putSach(OBJECT_BOOK , srtUser);
    }
    public static List<Sach> loadSach()
    {
        String strUser = DataManager.getInstance().mySharePreferences.getSach(OBJECT_BOOK);
        Gson gson = new Gson();
        List<Sach> sach = gson.fromJson(strUser , (Type) Sach.class);
        return sach ;
    }
    public boolean isLogin()
    {
       return DataManager.getInstance().mySharePreferences.saveLogin(OBJECT_USER);

    }
    public User getUserName()
    {
         sharedPreferences = mContext.getSharedPreferences(OBJECT_USER , Context.MODE_PRIVATE);
        return new User(sharedPreferences.getString("idMember" , null),
                sharedPreferences.getString("username" , null),
                sharedPreferences.getString("MemberName" , null),
                sharedPreferences.getString("password" , null),
                sharedPreferences.getString("Email" , null),
                sharedPreferences.getString("Gioitinh" , null),
                sharedPreferences.getString("Ngaysinh" , null),
                sharedPreferences.getString("Success" , null),
                sharedPreferences.getString("Message" , null),
                sharedPreferences.getString("ImgAvatar" , null),
                sharedPreferences.getString("ImgBia" , null));

    }
    public void logOut()
    {
        sharedPreferences = mContext.getSharedPreferences(OBJECT_USER , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
