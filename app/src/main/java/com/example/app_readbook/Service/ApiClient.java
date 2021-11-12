package com.example.app_readbook.Service;

import com.example.app_readbook.ApiView.dangnhap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.Retrofit;

import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static Retrofit retrofit = null;
    private static InputStream inputStream;
    public static Retrofit getApiClient(String url)
    {
        if(retrofit==null)
        {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().readTimeout(10000, TimeUnit.MILLISECONDS)
                .writeTimeout(10000 ,TimeUnit.MILLISECONDS)
                .connectTimeout(10000 , TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .protocols(Arrays.asList(Protocol.HTTP_1_1))
                .build();
            Gson gson = new GsonBuilder().setLenient().create();
            retrofit = new Retrofit.Builder().baseUrl(url).client(okHttpClient).addConverterFactory(GsonConverterFactory.create(gson)).build();
            gson.fromJson(new InputStreamReader(inputStream), dangnhap.class);


      }
      return retrofit;
    }

}
