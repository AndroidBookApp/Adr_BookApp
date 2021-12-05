package com.example.app_readbook.View.readbook;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.app_readbook.Model.Chapter;
import com.example.app_readbook.Model.Chuong;
import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.R;
import com.example.app_readbook.View.chapter.Main_Chapter;
import com.example.app_readbook.ViewModel.ReadBookViewModel;
import com.example.app_readbook.shareFreferences.DataManager;

import java.util.List;

public class MainReadbook extends AppCompatActivity {
    private TextView btn_back, btn_next, txt_book, txt_book_all, read;
    private ViewPager viewPager;
    private List<Chapter> chapters;
    private Chuong chuongList;
    private Sach sach;
    private Toolbar toolbar;
    String idChuong, idSach;
    private RecyclerView rcv_readbook;
    private AdaptorReadBook adaptorReadBook;
    private ReadBookViewModel readBookViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readbook_chapter);
        iniU();
        sach = DataManager.loadObjectSach();
        chuongList = DataManager.lChapter();
        idChuong = chuongList.getIdChuong();
        idSach = sach.getIdSach();
        BackView();
        readBookViewModel = new ViewModelProvider(this).get(ReadBookViewModel.class);
        readBookViewModel.getListChap().observe(this, new Observer<List<Chapter>>() {
            @Override
            public void onChanged(List<Chapter> chapter) {
                chapters = chapter;
                if (chapters != null) {
                    DataReadBook(chapters);
                }
            }
        });
        readBookViewModel.iniDataBook(idChuong, idSach);
//        int maxx = Integer.parseInt(getIntent().getStringExtra("page_max").toString().trim());
//        int minn = Integer.parseInt(getIntent().getStringExtra("page_min").toString().trim());
//        readbookNames = getReadbook(minn ,maxx);
//
//        ReadbookAdaptor readbookAdaptor = new ReadbookAdaptor(getSupportFragmentManager() , FragmentStatePagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT , readbookNames);
//        viewPager.setAdapter(readbookAdaptor);
//        txt_book.setText(getIntent().getStringExtra("page_min"));
//        txt_book_all.setText(getIntent().getStringExtra("page_max"));
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                txt_book.setText(String.valueOf(position + minn ));
//                if (position == 0)
//                {
//                    btn_back.setVisibility(View.GONE);
//                    btn_next.setVisibility(View.VISIBLE);
//                } else if (position == readbookNames.size() - 1 )
//                {
//                    btn_back.setVisibility(View.VISIBLE);
//                    btn_next.setVisibility(View.GONE);
//                } else {
//                    btn_back.setVisibility(View.VISIBLE);
//                    btn_next.setVisibility(View.VISIBLE);
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//        btn_back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
//            }
//        });
//        btn_next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
//            }
//        });
//    }
//
//    private List<Chapter> getReadbook(int min, int max) {
//        List<Chapter> list = new ArrayList<>();
//
//        for(int i = min ; i <= max ; i++)
//        {
//            list.add(new Chapter("Trang" +i ));
//        }
//        return  list;
    }

    private void BackView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void iniU() {
        btn_back = findViewById(R.id.before);
        btn_next = findViewById(R.id.next);
        txt_book = findViewById(R.id.txt1);
        read = findViewById(R.id.txtNameBook);
        toolbar = findViewById(R.id.toolbar);
        txt_book_all = findViewById(R.id.txt2);
        viewPager = findViewById(R.id.viewpager);
        rcv_readbook = findViewById(R.id.rcv_readbook);
        read.setText(getIntent().getStringExtra("value"));

    }

    private void DataReadBook(List<Chapter> chapters) {
        adaptorReadBook = new AdaptorReadBook();
        adaptorReadBook.setData(chapters);
        rcv_readbook.setHasFixedSize(true);
        rcv_readbook.setLayoutManager(new LinearLayoutManager(MainReadbook.this, LinearLayoutManager.VERTICAL, false));
        rcv_readbook.setAdapter(adaptorReadBook);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(MainReadbook.this , Main_Chapter.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}