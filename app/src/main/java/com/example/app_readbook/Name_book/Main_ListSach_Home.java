package com.example.app_readbook.Name_book;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.R;
import com.example.app_readbook.book.book;

import java.util.ArrayList;
import java.util.List;

public class Main_ListSach_Home extends AppCompatActivity {
private RecyclerView recyclerView;
private NameBookAdaptor nameBookAdaptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
        recyclerView = findViewById(R.id.rcv_name);
        nameBookAdaptor = new NameBookAdaptor(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this , RecyclerView.VERTICAL , false);
        recyclerView.setLayoutManager(linearLayoutManager);
        nameBookAdaptor.setData(getListName());
        recyclerView.setAdapter(nameBookAdaptor);
    }

    private List<Name> getListName() {
        List<Name> ls = new ArrayList<>();
        List<book> list = new ArrayList<>();
        list.add(new book(R.drawable.sach1 , "Book 1"));
        list.add(new book(R.drawable.sach1 , "Book 2"));
        list.add(new book(R.drawable.sach1 , "Book 3"));
        list.add(new book(R.drawable.sach1 , "Book 4"));
        list.add(new book(R.drawable.sach1 , "Book 5"));
        list.add(new book(R.drawable.sach1 , "Book 1"));
        list.add(new book(R.drawable.sach1 , "Book 2"));
        list.add(new book(R.drawable.sach1 , "Book 3"));
        list.add(new book(R.drawable.sach1 , "Book 4"));
        list.add(new book(R.drawable.sach1 , "Book 5"));

        return ls;
    }
}