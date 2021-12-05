package com.example.app_readbook.View.list_book;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.Model.DanhMucSach;
import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.R;
import com.example.app_readbook.View.BroadCastRecivice.NextWorkConnect;
import com.example.app_readbook.ViewModel.AddFavoriteViewModel;
import com.example.app_readbook.ViewModel.MainListBookViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main_ListBook extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListBookAdaptor bookAdaptor;
    private Toolbar toolbar;
    public Sach sach;
    List<Sach> danhMucSaches;
    private TextView textView_page;
    private EditText txt_searchName;
    DanhMucSach danhMucSach;
    public NextWorkConnect nextWorkConnect = new NextWorkConnect();
    AddFavoriteViewModel addFavoriteViewModel;
    MainListBookViewModel mainListBookViewModel;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_book);
        iniAnhXa();
        BackView();
        LoadDanhMuc();
        addFavoriteViewModel = new ViewModelProvider(this).get(AddFavoriteViewModel.class);
    }

    private void BackView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    private void iniAnhXa() {
        recyclerView = findViewById(R.id.rcv_book);
        textView_page = findViewById(R.id.name_page);
        txt_searchName = findViewById(R.id.txt_searchBook);
        toolbar = findViewById(R.id.toolbar);
        getIntents();
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
        if (intent.hasExtra("danhmuc")) {
            danhMucSach = (DanhMucSach) intent.getSerializableExtra("danhmuc");
            Toast.makeText(this, danhMucSach.getTendanhmuc(), Toast.LENGTH_SHORT).show();

        }
    }

    private void LoadDanhMuc() {
        mainListBookViewModel = new ViewModelProvider(this).get(MainListBookViewModel.class);
        mainListBookViewModel.getListSach().observe(this, new Observer<List<Sach>>() {
            @Override
            public void onChanged(List<Sach> saches) {
                danhMucSaches = saches;
                if (danhMucSaches != null) {
                    loadData(danhMucSaches);
                }
            }
        });
        mainListBookViewModel.iniDataBook(danhMucSach.getIdDanhmuc());


    }

    private void loadData(List<Sach> saches) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Main_ListBook.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        bookAdaptor = new ListBookAdaptor(this);
        bookAdaptor.setData(saches);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        recyclerView.setAdapter(bookAdaptor);
    }

    public void searchName(String name) {
        ArrayList<Sach> arrayList = new ArrayList<>();
        for (Sach item : danhMucSaches) {
            if (item.getTensach().toLowerCase().contains(name.toLowerCase(Locale.ROOT))) {
                arrayList.add(item);
                Sach listBook;
            }
            bookAdaptor.searchBook(arrayList);
        }
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