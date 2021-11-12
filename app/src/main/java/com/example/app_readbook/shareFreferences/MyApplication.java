package com.example.app_readbook.shareFreferences;

import android.app.Application;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataManager.init(getApplicationContext());
    }
}
