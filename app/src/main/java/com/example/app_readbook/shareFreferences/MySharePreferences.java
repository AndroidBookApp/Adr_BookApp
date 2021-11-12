package com.example.app_readbook.shareFreferences;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharePreferences {
    private static final String SHARE_USER= "SHARE_USER";
    private Context mContext;

    public MySharePreferences(Context mContext) {
        this.mContext = mContext;
    }
    public void putBooleanUser(String key,boolean value_user)
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARE_USER , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key , value_user);
        editor.apply();
    }
    public Boolean getBooleanUser(String key)
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARE_USER , Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }
    public String getStringUser(String key)
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARE_USER , Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }
    public void putStringUser(String key , String valueUser)
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARE_USER , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key ,valueUser);
        editor.apply();
    }
    public void putBooleanLogin(String key,boolean value_user)
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARE_USER , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key , value_user);
        editor.apply();
    }
    public Boolean getBooleanLogin(String key)
    {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARE_USER , Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

}
