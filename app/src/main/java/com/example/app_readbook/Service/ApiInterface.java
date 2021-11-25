package com.example.app_readbook.Service;

import com.example.app_readbook.Model.Chuong;
import com.example.app_readbook.Model.DanhMucSach;
import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.Model.User;
import com.example.app_readbook.Model.danhgia;
import com.example.app_readbook.Model.favoriteDeleteData;
import com.example.app_readbook.Model.login;
import com.example.app_readbook.Model.testfavorite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

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
//    @FormUrlEncoded
//    @POST("chitietsach.php")
//    Call<List<Sach>> Chitietsach(@Field("idSach") String idSach);
    @GET("chitietsach.php")
    Call<ArrayList<Sach>> Chitietsach();
    @FormUrlEncoded
    @POST("danhgia.php")
    Call<List<danhgia>> LoadDanhgia(@Field("idSach") String idSach);
    @FormUrlEncoded
    @POST("comment.php")
    Call<List<danhgia>> AddComment(@Field("idMember") String idMember , @Field("idSach") String idSach , @Field("Noidung") String Noidung);
    @FormUrlEncoded
    @POST("chuong.php")
    Call<List<Chuong>> LoadChuong(@Field("idSach") String idSach);
    @FormUrlEncoded
    @POST("thichsach.php")
    Call<String> UpdateFavorite(@Field("idMember") String idmember,@Field("idSach") String idSach);

    @FormUrlEncoded
    @POST("loginn.php")
    Call<login> getLogin(@Field("username") String username , @Field("password") String password);
    @FormUrlEncoded
    @POST("dangky.php")
    Call<User> getRegister(@Field("username") String username ,@Field("password") String password , @Field("email") String email);
//    @FormUrlEncoded
//    @POST("danhsachluotthich.php")
//    Call<List<favorite>> getListFavorite(@Field("idMember") String idMember);
    @FormUrlEncoded
    @POST("danhsachluotthich.php")
    Call<testfavorite> getListFavorite(@Field("idMember") String idMember);
    @FormUrlEncoded
    @POST("xoaluotthich.php")
    Call<favoriteDeleteData> deleteFavorite(@Field("idMember") String idMember , @Field("idSach") String idSach);
    @FormUrlEncoded
    @POST("luotxemsach.php")
    Call<String> ViewReadBook(@Field("idSach") String idSach , @Field("Luotxem") String luotxem);
    @Multipart
@POST("loadimg_server.php")
Call<login> getUpdate(@Header("idMember") String idMember,
                      @Header("ImgAvatar") String Avatar,
                      @PartMap Map<String ,RequestBody> ImgAvatar);


}
