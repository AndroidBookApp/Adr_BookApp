package com.example.app_readbook.View.readbook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.app_readbook.Class.CustomProgessDialog;
import com.example.app_readbook.Model.Chuong;
import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.R;
import com.example.app_readbook.View.View_Readbook.View_ReadBook;
import com.example.app_readbook.ViewModel.SelectChapterViewModel;
import com.example.app_readbook.shareFreferences.DataManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainReadbook extends AppCompatActivity {
    private TextView txt_book, read;
    private List<Chuong> chapters;
    private ImageView btn_back ,btn_next;
    private Chuong chuongList;
    private Sach sach;
    private Toolbar toolbar;
    String tenChuong, idSach ;
    private int shortAnimationDuration;
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    Switch aSwitch;
    boolean isDark;
    boolean iClick = true;
    private FloatingActionButton button;
    private LinearLayout lo2;
    ReadbookAdaptor readbookAdaptor;
    private ConstraintLayout layout;
    private VerticalViewPager verticalViewPager;
    private SelectChapterViewModel selectChapterViewModel;
    private CustomProgessDialog customProgessDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readbook_chapter);
        iniU();
        customProgessDialog = new CustomProgessDialog(MainReadbook.this);
        customProgessDialog.show();
        selectChapterViewModel = new ViewModelProvider(this).get(SelectChapterViewModel.class);
        chuongList = DataManager.lChapter();
        if(chuongList!=null)
        {
            txt_book.setText(chuongList.getTenChuong());
        }
        sach = DataManager.loadObjectSach();
        idSach = sach.getIdSach();
        read.setText(sach.getTensach());
        BackView();
        selectChapterViewModel.getListChuong().observe(this, new Observer<List<Chuong>>() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onChanged(List<Chuong> chuongs) {
                chapters =  chuongs;
                if (chapters!=null)
                {
                    customProgessDialog.dismiss();
                    DataReadBook();
                }
                else {
                    customProgessDialog.dismiss();
                    Toast.makeText(MainReadbook.this, "Sách chưa có dữ liệu", Toast.LENGTH_SHORT).show();
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
        lo2 = findViewById(R.id.lil_02);
        aSwitch = findViewById(R.id.switch_dark);
        button = findViewById(R.id.btn_showView);
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                isDark = !isDark;
                if(isDark)
                {
                    layout.setBackgroundColor(getResources().getColor(R.color.black));
                }else{
                    layout.setBackgroundColor(getResources().getColor(R.color.white));
                }
                DataReadBook();

            }
        });

    }
    @RequiresApi(api = Build.VERSION_CODES.M)
    private void DataReadBook() {

        readbookAdaptor = new ReadbookAdaptor(getSupportFragmentManager() , FragmentStatePagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT ,chapters , isDark ,tenChuong);
        verticalViewPager.setAdapter(readbookAdaptor);
        verticalViewPager.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            }
        });

        verticalViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {




            }
            @Override
            public void onPageSelected(int position) {
                txt_book.setText(chapters.get(position).getTenChuong());
                if (position == 0)
                {
                    btn_back.setImageResource(R.drawable.ic_baseline_navigate_before2_24);
                    btn_next.setEnabled(true);
                    btn_back.setEnabled(false);
                } else if (position == chapters.size() - 1)
                {
                    btn_back.setEnabled(true);
                    btn_next.setEnabled(false);
                    btn_next.setImageResource(R.drawable.ic_baseline_navigate_next2_24);
                } else {
                    btn_back.setEnabled(true);
                    btn_next.setEnabled(true);
                    btn_next.setImageResource(R.drawable.ic_baseline_navigate_next1_24);
                    btn_back.setImageResource(R.drawable.ic_baseline_navigate_before1_24);

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

    public void show(View view) {
        if(iClick)
        {
            iClick = false;
            lo2.setVisibility(View.VISIBLE);
            button.setVisibility(View.GONE);
            button.animate().alpha(0).setDuration(1000).translationX(0);
            lo2.animate().alpha(1).setDuration(1000);

        }else{
            iClick = true;
            button.setVisibility(View.VISIBLE);
            lo2.setVisibility(View.GONE);
            lo2.animate().alpha(0).setDuration(1000).translationX(0);
            button.animate().alpha(1).setDuration(1000);
        }
    }
}