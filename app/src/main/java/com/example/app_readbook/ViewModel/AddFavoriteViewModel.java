package com.example.app_readbook.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_readbook.Service.ApiInterface;
import com.example.app_readbook.Service.ApiService;
import com.example.app_readbook.shareFreferences.DataManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFavoriteViewModel extends ViewModel {
    public MutableLiveData<String> mAddFavorite;
    public String AddFavorite;

    public AddFavoriteViewModel() {
        mAddFavorite = new MutableLiveData<>();
    }

    public MutableLiveData<String> getAddFavorite() {
        return mAddFavorite;
    }
    public void iniAddFavorite(String idBook, String idMember) {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<String> callback = apiInterface.UpdateFavorite(idBook, idMember);
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                AddFavorite = response.body();
                if (AddFavorite!=null) {
                    if (AddFavorite.equals("like")) {
                        DataManager.sFavorite(AddFavorite);
                        mAddFavorite.setValue(response.body());
                    } else if (AddFavorite.equals("unlike")){
//                        DataManager.sFavorite(AddFavorite);
                        mAddFavorite.setValue(null);
                    }

                }
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mAddFavorite.setValue(null);
            }
        });
    }
}
