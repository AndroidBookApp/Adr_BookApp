package com.example.app_readbook.shareFreferences;

import android.content.Context;

import com.example.app_readbook.Model.User;
import com.google.gson.Gson;

public class DataManager {
    private static final String FIRST_INSTALL = "FIRST_INSTALL";
    private static final String GET_USER ="GET_USER" ;
    private static final String OBJECT_USER = "OBJECT_USER";
    private static DataManager instance;
    private MySharePreferences mySharePreferences;
    public static void init(Context mContext)
    {
        instance = new DataManager();
        instance.mySharePreferences = new MySharePreferences(mContext);
    }
    public static DataManager getInstance()
    {
        if (instance == null)
        {
            instance = new DataManager();
        }
        return instance;
    }
    public static void setFirstInstall(boolean firstInstall)
    {
        DataManager.getInstance().mySharePreferences.putBooleanUser(FIRST_INSTALL , firstInstall);
    }
    public static boolean getFirstInstall(){
        return  DataManager.getInstance().mySharePreferences.getBooleanUser(FIRST_INSTALL);
    }

    public static void setObjectUser(User user)
    {
        Gson gson = new Gson();
        String strUser = gson.toJson(user);
        DataManager.getInstance().mySharePreferences.putStringUser(OBJECT_USER , strUser);
    }
    public static User getObjectUser()
    {
        String strJsonUser = DataManager.getInstance().mySharePreferences.getStringUser(OBJECT_USER);
        Gson gson = new Gson();
        User user = gson.fromJson(strJsonUser , User.class);
        return user;
    }
}
