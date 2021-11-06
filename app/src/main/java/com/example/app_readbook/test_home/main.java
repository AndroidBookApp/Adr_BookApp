package com.example.app_readbook.test_home;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.R;

import java.util.ArrayList;
import java.util.List;

public class main extends AppCompatActivity {
    private com.example.app_readbook.test_home.AllItemAdaptor allItemAdaptor;
    private RecyclerView recyclerView;
    List<all> list;
    List<name_item> name_items;
    List<book_item> bookItemList;

    public static final String url = "http://192.168.1.6:8888/demo_app/sach.php";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_adaptor_mainfragments);
        recyclerView = findViewById(R.id.ryc_main);
        allItemAdaptor = new AllItemAdaptor();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        allItemAdaptor.setData(this, getListData());
        recyclerView.setAdapter(allItemAdaptor);
        list = new ArrayList<>();
        bookItemList = new ArrayList<>();
        name_items = new ArrayList<>();
    }

    private List<all> getListData() {
        list.add(new all(com.example.app_readbook.test_home.AllItemAdaptor.TYPE_NAME , name_items , null));
        list.add(new all(com.example.app_readbook.test_home.AllItemAdaptor.TYPE_NAME , null , bookItemList));
        return list;
    }
}
