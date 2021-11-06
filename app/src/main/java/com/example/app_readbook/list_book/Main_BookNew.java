package com.example.app_readbook.list_book;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.R;

import java.util.ArrayList;
import java.util.List;

public class Main_BookNew extends AppCompatActivity {
    private List<list_bookNew> newList;
    private RecyclerView view;
    private BookNewAdaptor bookNewAdaptor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_new);
        view = findViewById(R.id.rcv_bookNew);
        newList = getListNew();
        bookNewAdaptor = new BookNewAdaptor(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false);
        bookNewAdaptor.setData(getListNew());
        view.setLayoutManager(linearLayoutManager);

//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        view.setAdapter(bookNewAdaptor);

    }

    private List<list_bookNew> getListNew() {
        List<list_bookNew> list = new ArrayList<>();
        list.add(new list_bookNew(R.drawable.sach1 , "Tâm Tịnh" , "Nguyễn Văn Quỳnh", R.drawable.ic_baseline_favorite_24,"400 Trang"  ));
        list.add(new list_bookNew(R.drawable.sach1 , "Tâm Tịnh" , "Nguyễn Văn Quỳnh", R.drawable.ic_baseline_favorite_24,"400 Trang"  ));
        list.add(new list_bookNew(R.drawable.sach1 , "Tâm Tịnh" , "Nguyễn Văn Quỳnh", R.drawable.ic_baseline_favorite_24,"400 Trang"  ));
        list.add(new list_bookNew(R.drawable.sach1 , "Tâm Tịnh" , "Nguyễn Văn Quỳnh", R.drawable.ic_baseline_favorite_24,"400 Trang"  ));
        list.add(new list_bookNew(R.drawable.sach1 , "Tâm Tịnh" , "Nguyễn Văn Quỳnh", R.drawable.ic_baseline_favorite_24,"400 Trang"  ));
        return list;
    }

}
