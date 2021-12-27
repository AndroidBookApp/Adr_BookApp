package com.example.app_readbook.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_readbook.Model.Chuong;
import com.example.app_readbook.ViewModel.Service.ApiInterface;
import com.example.app_readbook.ViewModel.Service.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectChapterViewModel extends ViewModel {
    public MutableLiveData<List<Chuong>> mListChuong;

    public SelectChapterViewModel() {
        mListChuong = new MutableLiveData<>();
    }
    public MutableLiveData<List<Chuong>> getListChuong(){
        return mListChuong;
    }
    public void iniData(String idSach)
    {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<List<Chuong>> CallChapter = apiInterface.SelectChapters(idSach);
        CallChapter.enqueue(new Callback<List<Chuong>>() {
            @Override
            public void onResponse(Call<List<Chuong>> call, Response<List<Chuong>> response) {
                if(response.isSuccessful())
                {
                    mListChuong.postValue(response.body());
                }else {
                    mListChuong.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Chuong>> call, Throwable t) {
                mListChuong.postValue(null);
            }
        });
    }
}
