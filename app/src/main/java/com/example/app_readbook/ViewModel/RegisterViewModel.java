package com.example.app_readbook.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_readbook.Model.User;
import com.example.app_readbook.Service.ApiInterface;
import com.example.app_readbook.Service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterViewModel extends ViewModel {
    public MutableLiveData<User> mUser;

    public RegisterViewModel() {
        mUser = new MutableLiveData<>();
    }
    public MutableLiveData<User> getUser()
    {
        return mUser;
    }
    public void initRegister(String username , String password , String email)
    {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<User> Register = apiInterface.getRegister(username , password , email);
        Register.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if(response.isSuccessful())
                {
                    mUser.setValue(response.body());
                }
                else {
                    mUser.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                mUser.setValue(null);
            }
        });
    }
}
