package com.example.app_readbook.View.list_book;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.app_readbook.Class.CustomProgessDialog;
import com.example.app_readbook.Model.DanhMucSach;
import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.Model.User;
import com.example.app_readbook.Model.favoriteDeleteData;
import com.example.app_readbook.R;
import com.example.app_readbook.View.BroadCastRecivice.NextWorkConnect;
import com.example.app_readbook.ViewModel.AddFavoriteViewModel;
import com.example.app_readbook.ViewModel.AddViewBookViewModel;
import com.example.app_readbook.ViewModel.MainListBookViewModel;
import com.example.app_readbook.shareFreferences.DataManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Main_ListBook extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListBookAdaptor bookAdaptor;
    private Toolbar toolbar;
    public Sach sach;
    public User user;
    List<Sach> danhMucSaches;
    private SwipeRefreshLayout layout;
    private EditText txt_searchName;
    DanhMucSach danhMucSach;
    public NextWorkConnect nextWorkConnect = new NextWorkConnect();
    AddFavoriteViewModel addFavoriteViewModel;
    MainListBookViewModel mainListBookViewModel;
    AddViewBookViewModel addViewBookViewModel;
    public Toast toast;
    private favoriteDeleteData favoriteDeleteData;
    private CustomProgessDialog customProgessDialog;
    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_book);
        user = DataManager.loadUser();
        sach = DataManager.loadObjectSach();
        customProgessDialog = new CustomProgessDialog(Main_ListBook.this);
        customProgessDialog.show();
        toast = new Toast(this);
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
        layout  = findViewById(R.id.refest_main);
        layout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                LoadDanhMuc();
                layout.setRefreshing(false);
            }
        });
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
                    customProgessDialog.dismiss();
                    loadData();
                }
            }
        });
        mainListBookViewModel.iniDataBook(danhMucSach.getIdDanhmuc());


    }

    private void loadData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Main_ListBook.this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        bookAdaptor = new ListBookAdaptor(this);
        bookAdaptor.setData(danhMucSaches);
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
    private void Toast(String text)
    {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_toast_favorite , findViewById(R.id.layout_toast_favorite));
        TextView textView = view.findViewById(R.id.tv_toast_favorite);
        toast.setView(view);
        toast.setGravity(Gravity.BOTTOM , 0 , 0);
        toast.setDuration(Toast.LENGTH_LONG);
        textView.setText(text);
        toast.show();

    }
}