package com.example.app_readbook.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DeleteFavoriteViewModel extends ViewModel {
    public MutableLiveData<String> mDeleteFavorite;
    String success;
    public DeleteFavoriteViewModel() {
        mDeleteFavorite = new MutableLiveData<>();

    }
    public MutableLiveData<String> getDeleteFavorite()
    {
        return mDeleteFavorite;
    }
//    public void iniDeleteData(String idMember , String idBook) {
//        ApiInterface apiInterface = ApiService.apiInterface();
//        Call<String> favoriteCall = apiInterface.deleteFavorite(idMember , idBook);
//        favoriteCall.enqueue(new Callback<String>() {
//            @Override
//            public void onResponse(Call<String> call, Response<String> response) {
//                success = response.body();
//                if(response.isSuccessful())
//                {
//                    if(success.equals("success"))
//                    {
//                        mDeleteFavorite.setValue(response.body());
//                    }
//                    else {
//                        mDeleteFavorite.setValue(null);
//                    }
//                }
//
//            }
//
//            @Override
//            public void onFailure(Call<String> call, Throwable t) {
//                mDeleteFavorite.setValue(null);
//            }
//        });
//    }
}
