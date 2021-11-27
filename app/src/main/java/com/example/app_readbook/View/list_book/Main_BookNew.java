package com.example.app_readbook.View.list_book;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.R;
import com.example.app_readbook.Service.ApiInterface;
import com.example.app_readbook.Service.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main_BookNew extends AppCompatActivity {
    private ArrayList<Sach> newList;
    private RecyclerView view;
    private BookNewAdaptor bookNewAdaptor;
    String danhmuc;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_new);
        view = findViewById(R.id.rcv_bookNew);
        getDataIMG();
    }

    private void getDataIMG() {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<List<Sach>> mSach = apiInterface.listsach();
        mSach.enqueue(new Callback<List<Sach>>() {
            @Override
            public void onResponse(Call<List<Sach>> call, Response<List<Sach>> response) {
                newList = (ArrayList<Sach>) response.body();
                bookNewAdaptor = new BookNewAdaptor(Main_BookNew.this , newList);
                view.setLayoutManager(new LinearLayoutManager(Main_BookNew.this , LinearLayoutManager.VERTICAL , false));
                view.setAdapter(bookNewAdaptor);
            }
            @Override
            public void onFailure(Call<List<Sach>> call, Throwable t) {

            }
        });
    }


}
