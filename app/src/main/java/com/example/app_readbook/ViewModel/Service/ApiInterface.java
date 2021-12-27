package com.example.app_readbook.ViewModel.Service;

import com.example.app_readbook.Model.Chapter;
import com.example.app_readbook.Model.Chuong;
import com.example.app_readbook.Model.DanhMucSach;
import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.Model.User;
import com.example.app_readbook.Model.danhgia;
import com.example.app_readbook.Model.favorite;
import com.example.app_readbook.Model.favoriteDeleteData;
import com.example.app_readbook.Model.listFavorite;
import com.example.app_readbook.Model.login;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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
    @POST("danhgia.php")
    Call<List<danhgia>> LoadDanhgia(@Field("idSach") String idSach);

    @FormUrlEncoded
    @POST("comment.php")
    Call<List<danhgia>> AddComment(@Field("idMember") String idMember, @Field("idSach") String idSach, @Field("Noidung") String Noidung);

    @FormUrlEncoded
    @POST("chuong.php")
    Call<List<Chuong>> LoadChuong(@Field("idSach") String idSach);

    @FormUrlEncoded
    @POST("thichsach.php")
    Call<String> UpdateFavorite(@Field("idMember") String idMember, @Field("idSach") String idSach);
    @FormUrlEncoded
    @POST("thichsach.php")
    Call<favoriteDeleteData> UpdateFavorites(@Field("idMember") String idMember, @Field("idSach") String idSach);
    @FormUrlEncoded
    @POST("loginn.php")
    Call<login> getLogin(@Field("username") String username, @Field("password") String password ,  @Field("idquyen") String idquyen);

    @FormUrlEncoded
    @POST("dangky.php")
    Call<User> getRegister(@Field("username") String username, @Field("password") String password, @Field("email") String email , @Field("idquyen") String idquyen);

    @FormUrlEncoded
    @POST("danhsachluotthich.php")
    Call<List<favorite>> getListFavorite(@Field("idMember") String idMember);

    @FormUrlEncoded
    @POST("xoaluotthich.php")
    Call<String> deleteFavorite(@Field("idSach") String idSach, @Field("idMember") String idMember);
    @FormUrlEncoded
    @POST("loadfavorite.php")
    Call<ArrayList<listFavorite>> loadFavorite(@Field("idMember") String idMember );

    @FormUrlEncoded
    @POST("luotxemsach.php")
    Call<String> ViewReadBook(@Field("idSach") String idSach, @Field("Luotxem") String luotxem);
    @FormUrlEncoded
    @POST("noidungchuong.php")
    Call<List<Chapter>> SelectChapter(@Field("idChuong") String idChuong , @Field("idSach") String idSach);
    @FormUrlEncoded
    @POST("showchuong.php")
    Call<List<Chuong>> SelectChapters(@Field("idSach") String idSach);
    @FormUrlEncoded
    @POST("update_member.php")
    Call<login> getUpdateMember(@Field("idMember") String idMember,
                                @Field("username") String username,
                                @Field("MemberName") String memberName,
                                @Field("password") String password,
                                @Field("email") String email,
                                @Field("Gioitinh") String Gioitinh,
                                @Field("Ngaysinh") String Ngaysinh,
                                @Field("ImgAvatar") String ImgAvatar,
                                @Field("ImgBia") String ImgBia
                                );
    @Multipart
    @POST("update_profile.php")
    Call<String> UploadPhoto(@Part MultipartBody.Part avatar);
    @Multipart
    @POST("update_profile_anhbia.php")
    Call<String> UploadPhotoIMGPAGE(@Part MultipartBody.Part img_page);

}
