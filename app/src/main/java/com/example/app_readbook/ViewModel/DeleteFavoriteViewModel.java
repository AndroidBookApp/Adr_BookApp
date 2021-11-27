package com.example.app_readbook.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_readbook.Model.favorite;
import com.example.app_readbook.Service.ApiInterface;
import com.example.app_readbook.Service.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteFavoriteViewModel extends ViewModel {
    public MutableLiveData<List<favorite>> mDeleteFavorite;
    String idBook , idMember;
    public DeleteFavoriteViewModel() {
        mDeleteFavorite = new MutableLiveData<>();
        iniDeleteData(idMember , idBook);
    }
    public MutableLiveData<List<favorite>> getDeleteFavorite()
    {
        return mDeleteFavorite;
    }
    public void iniDeleteData(String idMember , String idBook) {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<List<favorite>> favoriteCall = apiInterface.deleteFavorite(idMember , idBook);
        favoriteCall.enqueue(new Callback<List<favorite>>() {
            @Override
            public void onResponse(Call<List<favorite>> call, Response<List<favorite>> response) {
                if(response.isSuccessful())
                {
                    mDeleteFavorite.postValue(response.body());
                }
                else {
                    mDeleteFavorite.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<favorite>> call, Throwable t) {
                mDeleteFavorite.postValue(null);
            }
        });
    }
}
