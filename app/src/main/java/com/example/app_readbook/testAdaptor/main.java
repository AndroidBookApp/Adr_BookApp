package com.example.app_readbook.testAdaptor;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.R;

import java.util.ArrayList;
import java.util.List;

public class main extends AppCompatActivity {
    private AllItemAdaptor allItemAdaptor;
    private RecyclerView recyclerView;
    List<all_item> list;
    List<book_item> items;
    List<name_item> name_items;
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
        items = new ArrayList<>();
        name_items = new ArrayList<>();
    }

    private List<all_item> getListData() {
        list.add(new all_item(AllItemAdaptor.TYPE_NAME , null , name_items));
        list.add(new all_item(AllItemAdaptor.TYPE_BOOK , items , null));
        list.add(new all_item(AllItemAdaptor.TYPE_NAME , null , name_items));
        list.add(new all_item(AllItemAdaptor.TYPE_BOOK , items , null));
        list.add(new all_item(AllItemAdaptor.TYPE_NAME , null , name_items));
        list.add(new all_item(AllItemAdaptor.TYPE_BOOK , items , null));
        return list;
    }
}
