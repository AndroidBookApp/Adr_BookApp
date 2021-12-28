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
    public MutableLiveData<String> loadImage;
    public login mLogin;
    public String upLoadImageService;
    public UpdateMemberViewModel() {
        mUpdate = new MutableLiveData<>();
        loadImage = new MutableLiveData<>();
    }
    public MutableLiveData<login> getUpdate()
    {
        return mUpdate;
    }
    public MutableLiveData<String> getLoadImage()
    {
        return loadImage;
    }
    public  void iniLoadImage(String anhdaidien , String anhbia)
    {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<String> upLoad = apiInterface.UpdateFavorite(anhdaidien, anhbia);
        upLoad.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful())
                {

                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
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
