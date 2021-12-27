package com.example.app_readbook.ViewModel.Service;

public class ApiService {
    public static String base_URL = "https://androidbookapp.000webhostapp.com/Server/";
    public static ApiInterface apiInterface(){
        return ApiClient.getApiClient(base_URL).create(ApiInterface.class);
    }
}
