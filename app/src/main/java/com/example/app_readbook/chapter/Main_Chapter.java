package com.example.app_readbook.chapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.Model.Chuong;
import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.Model.chitietsach;
import com.example.app_readbook.R;
import com.example.app_readbook.Service.ApiInterface;
import com.example.app_readbook.Service.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main_Chapter extends AppCompatActivity {
private RecyclerView recyclerView;
private ArrayList<Chuong> chuongs;
private ArrayList<chitietsach> saches;
private TextView text_name;
Sach sach;
chitietsach chitietsach;
String chapter;
private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chapter);
        recyclerView = findViewById(R.id.rcv_chapter);
        text_name = findViewById(R.id.txt_name);
//        iniIntent();
//        text_name.setText(getIntent().getStringExtra("nameBook"));

            iniIntent();
//        toolbar = findViewById(R.id.toolbar_chapter);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle(getIntent().getStringExtra("tensach"));
        getDataChuong();



    }

    private void iniIntent() {
        Intent intent = getIntent();
        if(intent.hasExtra("sach"))

        {
            sach = (Sach) intent.getSerializableExtra("sach");
            text_name.setText(sach.getTensach());
        }
    }

    private void getDataChuong() {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<List<Chuong>> chapters = apiInterface.LoadChuong(sach.getIdSach());
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

//    @Override
//    public void onBackPressed() {
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle(getIntent().getStringExtra("nameBook"));
//        super.onBackPressed();
//    }
}