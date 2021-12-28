package com.example.app_readbook.ViewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.app_readbook.Model.danhgia;
import com.example.app_readbook.ViewModel.Service.ApiInterface;
import com.example.app_readbook.ViewModel.Service.ApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCommentViewModel extends ViewModel {
    public MutableLiveData<List<danhgia>> mAddComment;
    public List<danhgia> comment;
    public AddCommentViewModel() {
        mAddComment = new MutableLiveData<>();
    }
    public MutableLiveData<List<danhgia>> getAddComment() {
        return mAddComment;
    }
    public void iniAddComment(String idUser, String id, String vietdanhgia) {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<List<danhgia>> addCommentCall = apiInterface.AddComment(idUser, id, vietdanhgia);
        addCommentCall.enqueue(new Callback<List<danhgia>>() {
            @Override
            public void onResponse(Call<List<danhgia>> call, Response<List<danhgia>> response) {
                if (response.isSuccessful()) {
                    mAddComment.setValue(response.body());
                } else {
                    mAddComment.setValue(response.body());
                    Log.e("AAA", response.message());
                }
            }
            @Override
            public void onFailure(Call<List<danhgia>> call, Throwable t) {
                mAddComment.setValue(null);
                Log.e("AAA", t.getMessage());
            }
        });
    }
}
