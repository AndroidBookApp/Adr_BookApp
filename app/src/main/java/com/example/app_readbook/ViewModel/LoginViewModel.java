package com.example.app_readbook.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_readbook.Model.login;
import com.example.app_readbook.ViewModel.Service.ApiInterface;
import com.example.app_readbook.ViewModel.Service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    public MutableLiveData<login> mLogin;

    public LoginViewModel() {
        mLogin = new MutableLiveData<>();
    }

    public MutableLiveData<login> getLogin() {
        return mLogin;
    }

    public void iniLogin(String username, String password , String quyen) {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<login> mUser = apiInterface.getLogin(username, password , quyen);
        mUser.enqueue(new Callback<login>() {
            @Override
            public void onResponse(Call<login> call, Response<login> response) {
                if (response.isSuccessful()) {
                    mLogin.postValue(response.body());
                } else {
                    mLogin.postValue(null);
                }
            }
            @Override
            public void onFailure(Call<login> call, Throwable t) {
                mLogin.postValue(null);
            }
        });
    }
}
