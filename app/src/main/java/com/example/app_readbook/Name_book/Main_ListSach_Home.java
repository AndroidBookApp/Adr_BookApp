package com.example.app_readbook.Name_book;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.Name_book.book.book;
import com.example.app_readbook.R;

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
        statusbar();

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
    private void statusbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        getWindow().setStatusBarColor(ContextCompat.getColor(Main_ListSach_Home.this, R.color.white));
    }
    public void goToDetailFragments(book book)
    {

    }
}