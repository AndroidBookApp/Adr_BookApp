package com.example.app_readbook.ViewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_readbook.ViewModel.Service.ApiInterface;
import com.example.app_readbook.ViewModel.Service.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

// Add view book
public class AddViewBookViewModel extends ViewModel {
    public MutableLiveData<String> mAddViewBook;

    public AddViewBookViewModel() {
        mAddViewBook = new MutableLiveData<>();
    }
    public MutableLiveData<String> getAddViewBook(){
        return  mAddViewBook;
    }
    public void iniAddView(String idSach , String view)
    {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<String> strViewBook = apiInterface.ViewReadBook(idSach, view);
        strViewBook.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String view = response.body();
                if (view.equals("Success")) {
                   mAddViewBook.setValue(view);
                }else {
                    mAddViewBook.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                mAddViewBook.setValue(null);
            }
        });
    }
}
