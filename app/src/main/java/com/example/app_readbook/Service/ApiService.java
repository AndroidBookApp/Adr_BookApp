package com.example.app_readbook.Service;

public class ApiService {
    private static String base_URL = "http://192.168.1.6:8888/demo_app/";
    public static ApiInterface apiInterface(){
        return ApiClient.getApiClient(base_URL).create(ApiInterface.class);
    }
}
