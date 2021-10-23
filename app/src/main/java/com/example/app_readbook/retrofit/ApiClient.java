package com.example.app_readbook.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String URL = "https://10.0.2.2:8888/demo_app/";
    private static Retrofit retrofit = null;
    public static Retrofit getApiclient()
    {
      if(retrofit==null)
      {
          Gson gson = new GsonBuilder().setLenient().create();
          retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
      }
      return retrofit;
    }

}
