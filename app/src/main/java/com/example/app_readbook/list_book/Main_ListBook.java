package com.example.app_readbook.list_book;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.R;

import java.util.ArrayList;
import java.util.List;

public class Main_ListBook extends AppCompatActivity {
private RecyclerView recyclerView;
private ListBookAdaptor listBookAdaptor;
List<list_book> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list_book);
        recyclerView = findViewById(R.id.rcv_book);
        mList = getListItem();
        listBookAdaptor = new ListBookAdaptor(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false);
        recyclerView.setLayoutManager(linearLayoutManager);
        listBookAdaptor.setData(getListItem() );
        recyclerView.setAdapter(listBookAdaptor);


    }

    private List<list_book> getListItem() {
        List<list_book> list = new ArrayList<>();
        list.add(new list_book(R.drawable.sach2 , "Nghĩ Giàu Làm Giàu","tác giả 1",
                R.drawable.ic_baseline_favorite_24,"100" , "10.000" , "400 Trang" ));
        list.add(new list_book(R.drawable.sach2 , "Nghĩ Giàu Làm Giàu","tác giả 1",
                R.drawable.ic_baseline_favorite_24,"100" , "10.000" , "400 Trang" ));
        list.add(new list_book(R.drawable.sach2 , "Nghĩ Giàu Làm Giàu","tác giả 1",
                R.drawable.ic_baseline_favorite_24,"100" , "10.000" , "400 Trang" ));
        list.add(new list_book(R.drawable.sach2 , "Nghĩ Giàu Làm Giàu","tác giả 1",
                R.drawable.ic_baseline_favorite_24,"100" , "10.000" , "400 Trang" ));
        list.add(new list_book(R.drawable.sach2 , "Nghĩ Giàu Làm Giàu","tác giả 1",
                R.drawable.ic_baseline_favorite_24,"100" , "10.000" , "400 Trang" ));
        list.add(new list_book(R.drawable.sach2 , "Nghĩ Giàu Làm Giàu","tác giả 1",
                R.drawable.ic_baseline_favorite_24,"100" , "10.000" , "400 Trang" ));
        list.add(new list_book(R.drawable.sach2 , "Nghĩ Giàu Làm Giàu","tác giả 1",
                R.drawable.ic_baseline_favorite_24,"100" , "10.000" , "400 Trang" ));

        return list;
    }
}