package com.example.app_readbook.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.ViewModel.Service.ApiInterface;
import com.example.app_readbook.ViewModel.Service.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainListBookViewModel extends ViewModel {
    public MutableLiveData<List<Sach>> mListSach;

    public MainListBookViewModel() {
        mListSach = new MutableLiveData<>();
    }
    public MutableLiveData<List<Sach>> getListSach(){
        return mListSach;
    }
    public void iniDataBook(String idDanhMuc)
    {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<List<Sach>> callBook = apiInterface.listDanhMuc(idDanhMuc);
        callBook.enqueue(new Callback<List<Sach>>() {
            @Override
            public void onResponse(Call<List<Sach>> call, Response<List<Sach>> response) {
                if (response.isSuccessful())
                {
                    mListSach.setValue(response.body());
                }else {
                    mListSach.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Sach>> call, Throwable t) {
                mListSach.setValue(null);
            }
        });
    }
}
