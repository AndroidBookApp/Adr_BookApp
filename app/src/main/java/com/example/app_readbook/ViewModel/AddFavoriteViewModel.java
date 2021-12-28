package com.example.app_readbook.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_readbook.Model.favoriteDeleteData;
import com.example.app_readbook.ViewModel.Service.ApiInterface;
import com.example.app_readbook.ViewModel.Service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddFavoriteViewModel extends ViewModel {
    public MutableLiveData<favoriteDeleteData> mAddFavorite;
    public favoriteDeleteData AddFavorite;
    public boolean check;
    public AddFavoriteViewModel() {
        mAddFavorite = new MutableLiveData<>();
    }

    public MutableLiveData<favoriteDeleteData> getAddFavorite() {
        return mAddFavorite;
    }

    public void iniAddFavorite(String idBook, String idMember) {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<favoriteDeleteData> callback = apiInterface.UpdateFavorites(idBook, idMember);
        callback.enqueue(new Callback<favoriteDeleteData>() {
            @Override
            public void onResponse(Call<favoriteDeleteData> call, Response<favoriteDeleteData> response) {
                AddFavorite = response.body();
                if (response.isSuccessful() && AddFavorite != null && AddFavorite.getMessage() !=null) {
                    check  = AddFavorite.getMessage();
                    if (AddFavorite.getSuccess().equals("like") && check) {
                        mAddFavorite.setValue(response.body());
                    } else if (AddFavorite.getSuccess().equals("unlike") && !check) {
                        mAddFavorite.setValue(response.body());
                    }
                }
            }

            @Override
            public void onFailure(Call<favoriteDeleteData> call, Throwable t) {
                mAddFavorite.setValue(null);
            }
        });
    }
}
