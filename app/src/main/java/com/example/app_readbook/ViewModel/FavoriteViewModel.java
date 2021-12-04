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

public class FavoriteViewModel extends ViewModel {
    String idMember;
    public MutableLiveData<List<favorite>> mListFavorite;
    public List<favorite> mFavorite;

    public FavoriteViewModel() {
        mListFavorite = new MutableLiveData<>();
        iniDataFavorite(idMember);
    }
    public MutableLiveData<List<favorite>> getListFavorite()
    {
        return mListFavorite;
    }
    public void iniDataFavorite(String idMember) {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<List<favorite>> listCall = apiInterface.getListFavorite(idMember);
        listCall.enqueue(new Callback<List<favorite>>() {
            @Override
            public void onResponse(Call<List<favorite>> call, Response<List<favorite>> response) {
                if(response.isSuccessful())
                {
                    mListFavorite.postValue(response.body());
                }
                else {
                    mListFavorite.postValue(null);
                }
            }
            @Override
            public void onFailure(Call<List<favorite>> call, Throwable t) {
                mListFavorite.postValue(null);
            }
        });
    }
}
