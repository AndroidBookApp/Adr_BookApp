package com.example.app_readbook.View.chapter;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.Model.Chuong;
import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.R;
import com.example.app_readbook.ViewModel.Service.ApiInterface;
import com.example.app_readbook.ViewModel.Service.ApiService;
import com.example.app_readbook.View.BroadCastRecivice.NextWorkConnect;
import com.example.app_readbook.shareFreferences.DataManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class Main_Chapter extends AppCompatActivity {
private RecyclerView recyclerView;
private ArrayList<Chuong> chuongs;
private TextView text_name;
Sach sach;
private Toolbar toolbar;
String idSach;
NextWorkConnect nextWorkConnect = new NextWorkConnect();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chapter);
        recyclerView = findViewById(R.id.rcv_chapter);
        text_name = findViewById(R.id.txt_name);
        toolbar = findViewById(R.id.toolbar_chapter);

        iniIntent();
        BackView();
        getDataChuong();
    }
    private void BackView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    private void iniIntent() {
        sach = new Sach();
        sach = DataManager.loadObjectSach();
        idSach = sach.getIdSach();
        text_name.setText(sach.getTensach());
    }

    private void getDataChuong() {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<List<Chuong>> chapters = apiInterface.LoadChuong(idSach);
        chapters.enqueue(new Callback<List<Chuong>>() {
            @Override
            public void onResponse(Call<List<Chuong>> call, Response<List<Chuong>> response) {
                chuongs = (ArrayList<Chuong>) response.body();
                ChapterAdaptor chapterAdaptor = new ChapterAdaptor(chuongs,Main_Chapter.this );
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Main_Chapter.this , LinearLayoutManager.VERTICAL , false);
                recyclerView.setLayoutManager(linearLayoutManager);
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
                recyclerView.addItemDecoration(dividerItemDecoration);
                recyclerView.setAdapter(chapterAdaptor);
            }

            @Override
            public void onFailure(Call<List<Chuong>> call, Throwable t) {

            }
        });
    }
    @Override
    public void onBackPressed() {

        super.onBackPressed();
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