package com.example.app_readbook.shareFreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.Model.User;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

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
    public static void saveSach(ArrayList<Sach> sach)
    {
        Gson gson = new Gson();
        JsonArray jsonArray = gson.toJsonTree(sach).getAsJsonArray();
        String strSach = jsonArray.toString();
        DataManager.getInstance().mySharePreferences.putSach(OBJECT_BOOK , strSach);
    }
    public static ArrayList<Sach> loadSach()
    {
        String strSach = DataManager.getInstance().mySharePreferences.getSach(OBJECT_BOOK);
        Gson gson = new Gson();
        ArrayList<Sach> sach = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(strSach);
            JSONObject jsonObject;
            Sach mSach;
            for(int i = 0 ; i<jsonArray.length() ; i++)
            {
                jsonObject = jsonArray.getJSONObject(i);
                mSach = gson.fromJson(jsonObject.toString(), Sach.class);
                sach.add(mSach);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sach ;
    }
    public static void saveObjectSach(Sach sach)
    {

        Gson gson = new Gson();
        String strUser = gson.toJson(sach);
        DataManager.getInstance().mySharePreferences.putSach(OBJECT_USER,strUser);
    }
    public static Sach loadObjectSach()
    {
        String strUser = DataManager.getInstance().mySharePreferences.getSach(OBJECT_USER);
        Gson gson = new Gson();
        Sach sach = gson.fromJson(strUser , Sach.class);
        return sach;
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
