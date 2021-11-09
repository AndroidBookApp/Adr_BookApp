package com.example.app_readbook.Service;

import com.example.app_readbook.Model.DanhMucSach;
import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("sach.php")
    Call<List<Sach>> responseSach();
    @GET("sach.php")
    Call<List<Sach>> listsach();
    @FormUrlEncoded
    @POST("danhmucsach.php")
    Call<List<Sach>> listDanhMuc(@Field("idDanhmuc") String idDanhmuc);
    @GET("danhmuc.php")
    Call<List<DanhMucSach>> TenDanhMuc();
    @FormUrlEncoded
    @POST("login.php")
    Call<List<User>> getUser(@Field("username") String username ,@Field("password") String password);
    @FormUrlEncoded
    @POST("dangky.php")
    Call<User> getRegister(@Field("username") String username ,@Field("password") String password , @Field("email") String email);
}
