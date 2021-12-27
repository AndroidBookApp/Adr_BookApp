package com.example.app_readbook.View.list_book;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.R;
import com.example.app_readbook.ViewModel.Service.ApiInterface;
import com.example.app_readbook.ViewModel.Service.ApiService;
import com.example.app_readbook.View.BroadCastRecivice.NextWorkConnect;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main_BookNew extends AppCompatActivity {
    private ArrayList<Sach> newList;
    private RecyclerView view;
    private BookNewAdaptor bookNewAdaptor;
    private SwipeRefreshLayout refreshLayout;
    NextWorkConnect nextWorkConnect = new NextWorkConnect();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_new);
        view = findViewById(R.id.rcv_bookNew);
        refreshLayout = findViewById(R.id.refest);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDataIMG();
                refreshLayout.setRefreshing(false);
            }
        });
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
    @Override
    protected void onStart() {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(nextWorkConnect , intentFilter);
        super.onStart();

    }

    @Override
    protected void onStop() {
        unregisterReceiver(nextWorkConnect);
        super.onStop();
    }

}
