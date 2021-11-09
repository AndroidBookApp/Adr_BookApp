package com.example.app_readbook.list_book;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.Model.DanhMucSach;
import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.R;
import com.example.app_readbook.Service.ApiInterface;
import com.example.app_readbook.Service.ApiService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main_ListBook extends AppCompatActivity {
private RecyclerView recyclerView;
private ListBookAdaptor bookAdaptor;
private ArrayList<DanhMucSach> Danhmuc;
private Toolbar toolbar;
ArrayList<Sach> danhMucSaches;
private TextView textView_page;
private EditText txt_searchName ;
DanhMucSach danhMucSach;
public static final String url = "http://192.168.1.6:8888/demo_app/sach.php";

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_book);
        recyclerView = findViewById(R.id.rcv_book);
        textView_page = findViewById(R.id.name_page);
        txt_searchName = findViewById(R.id.txt_searchBook);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getIntents();
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        LoadDanhMuc();
        txt_searchName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                searchName(s.toString());
            }
        });


    }

    private void getIntents() {
        Intent intent = getIntent();
        if(intent.hasExtra("danhmuc"))
        {
            danhMucSach = (DanhMucSach) intent.getSerializableExtra("danhmuc");
            Toast.makeText(this, danhMucSach.getTendanhmuc(), Toast.LENGTH_SHORT).show();

        }
    }

    private void LoadDanhMuc() {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<List<Sach>> mDanhSach = apiInterface.listDanhMuc(danhMucSach.getIdDanhmuc());
        mDanhSach.enqueue(new Callback<List<Sach>>() {
            @Override
            public void onResponse(Call<List<Sach>> call, Response<List<Sach>> response) {
               danhMucSaches = (ArrayList<Sach>) response.body();
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Main_ListBook.this);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setHasFixedSize(true);
                bookAdaptor = new ListBookAdaptor( Main_ListBook.this ,  danhMucSaches);
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
                recyclerView.addItemDecoration(dividerItemDecoration);
                recyclerView.setAdapter(bookAdaptor);
            }

            @Override
            public void onFailure(Call<List<Sach>> call, Throwable t) {

            }
        });

            }



    public void searchName(String name)
{
    ArrayList<Sach> arrayList = new ArrayList<>();
    for (Sach item : danhMucSaches )
    {
        if(item.getTensach().toLowerCase().contains(name.toLowerCase(Locale.ROOT)))
        {
            arrayList.add(item);
            Sach listBook;
        }
        bookAdaptor.searchBook(arrayList);
    }
}


}