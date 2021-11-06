package com.example.app_readbook.RestAPI_ChưaLam;

import com.example.app_readbook.modelRestAPI.ApiReponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("register.php")
    Call<ApiReponse> reponseCallSignIn(@Field("email") String email ,@Field("username") String username ,@Field("password") String password);
    @FormUrlEncoded
    @POST("loginn.php")
    Call<ApiReponse> reponseCallLogin(@Field("username") String username ,@Field("password") String password );
}
