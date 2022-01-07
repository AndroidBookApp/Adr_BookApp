//package com.example.app_readbook.ViewModel;
//
//import androidx.lifecycle.MutableLiveData;
//import androidx.lifecycle.ViewModel;
//
//import com.example.app_readbook.Model.Chapter;
//import com.example.app_readbook.ViewModel.Service.ApiInterface;
//import com.example.app_readbook.ViewModel.Service.ApiService;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class ReadBookViewModel extends ViewModel {
//    public MutableLiveData<List<Chapter>> mListChap;
//
//    public ReadBookViewModel() {
//        mListChap = new MutableLiveData<>();
//    }
//    public MutableLiveData<List<Chapter>> getListChap(){
//        return mListChap;
//    }
//    public void iniDataBook(String idChuong , String idSach)
//    {
//        ApiInterface apiInterface = ApiService.apiInterface();
//        Call<List<Chapter>> callChapter = apiInterface.SelectChapter(idChuong , idSach);
//        callChapter.enqueue(new Callback<List<Chapter>>() {
//            @Override
//            public void onResponse(Call<List<Chapter>> call, Response<List<Chapter>> response) {
//                if (response.isSuccessful())
//                {
//                    mListChap.setValue(response.body());
//                }else {
//                    mListChap.setValue(null);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Chapter>> call, Throwable t) {
//                mListChap.setValue(null);
//            }
//        });
//    }
//}
