package com.example.app_readbook.View.readbook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.app_readbook.Model.Chuong;
import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.R;
import com.example.app_readbook.View.View_Readbook.View_ReadBook;
import com.example.app_readbook.ViewModel.SelectChapterViewModel;
import com.example.app_readbook.shareFreferences.DataManager;

import java.util.List;

public class MainReadbook extends AppCompatActivity {
    private TextView btn_back, btn_next, txt_book, read;
    private List<Chuong> chapters;
    private Chuong chuongList;
    private Sach sach;
    private Toolbar toolbar;
    String idChuong, idSach ;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch aSwitch;
    boolean isDark;
    private RelativeLayout layout;
    private VerticalViewPager verticalViewPager;
    private SelectChapterViewModel selectChapterViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readbook_chapter);
        iniU();
        sach = DataManager.loadObjectSach();
        chuongList = DataManager.lChapter();
        idChuong = chuongList.getIdChuong();
        idSach = sach.getIdSach();
        read.setText(sach.getTensach());
        BackView();
        selectChapterViewModel = new ViewModelProvider(this).get(SelectChapterViewModel.class);
        selectChapterViewModel.getListChuong().observe(this, new Observer<List<Chuong>>() {
            @Override
            public void onChanged(List<Chuong> chuongs) {
                chapters = chuongs;

                if (chapters!=null)
                {
                    DataReadBook(isDark);
                    txt_book.setText(chuongList.getTenChuong());
                }

            }
        });
        selectChapterViewModel.iniData(idSach);

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
        verticalViewPager = findViewById(R.id.view_pager_read);
        layout = findViewById(R.id.activityRoot);
        aSwitch = findViewById(R.id.switch_dark);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isDark = !isDark;
                if(isDark)
                {
                    layout.setBackgroundColor(getResources().getColor(R.color.black));
                }else{
                    layout.setBackgroundColor(getResources().getColor(R.color.white));
                }
                    DataReadBook(isDark);

            }
        });

    }
    private void DataReadBook(boolean isDark) {
        ReadbookAdaptor readbookAdaptor = new ReadbookAdaptor(getSupportFragmentManager() , FragmentStatePagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT , chapters , isDark);
        verticalViewPager.setAdapter(readbookAdaptor);
        verticalViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                txt_book.setText(chapters.get(position).getTenChuong());

                if (position == 0)
                {
                    btn_back.setVisibility(View.GONE);
                    btn_next.setVisibility(View.VISIBLE);
                } else if (position == chapters.size() - 1)
                {
                    btn_back.setVisibility(View.VISIBLE);
                    btn_next.setVisibility(View.GONE);
                } else {
                    btn_back.setVisibility(View.VISIBLE);
                    btn_next.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verticalViewPager.setCurrentItem(verticalViewPager.getCurrentItem() - 1);
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verticalViewPager.setCurrentItem(verticalViewPager.getCurrentItem() + 1);
            }
        });
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(MainReadbook.this , View_ReadBook.class);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }
}