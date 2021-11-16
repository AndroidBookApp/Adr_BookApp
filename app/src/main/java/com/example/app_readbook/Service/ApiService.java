package com.example.app_readbook.Service;

public class ApiService {
    private static String base_URL = "https://androidbookapp.000webhostapp.com/Server/";
    public static ApiInterface apiInterface(){
        return ApiClient.getApiClient(base_URL).create(ApiInterface.class);
    }
}
