package com.example.app_readbook.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_readbook.Model.login;
import com.example.app_readbook.ViewModel.Service.ApiInterface;
import com.example.app_readbook.ViewModel.Service.ApiService;
import com.example.app_readbook.shareFreferences.DataManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateMemberViewModel extends ViewModel {
    // khai báo
    public MutableLiveData<login> mUpdate;

    public login mLogin;

    public UpdateMemberViewModel() {
        mUpdate = new MutableLiveData<>();
    }
    public MutableLiveData<login> getUpdate()
    {
        return mUpdate;
    }



    public void iniUpdateMember(String idMember , String username , String MemberName , String password , String email, String gioitinh , String ngaysinh , String anhdaidien ,String anhbia )
    {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<login> UpdateMember = apiInterface.getUpdateMember(idMember , username , MemberName, password ,email, gioitinh ,ngaysinh, anhdaidien, anhbia);
        UpdateMember.enqueue(new Callback<login>() {
            @Override
            public void onResponse(Call<login> call, Response<login> response) {
                mLogin = response.body();
                if (mLogin != null) {
                    if (mLogin.getSuccess().equals("200"))
                    {
                        mUpdate.setValue(mLogin);
                        DataManager.saveUserName(mLogin.getUser());
                    }
                }else {
                    mUpdate.setValue(null);
                }

            }

            @Override
            public void onFailure(Call<login> call, Throwable t) {
                mUpdate.setValue(null);
            }
        });
    }
}
