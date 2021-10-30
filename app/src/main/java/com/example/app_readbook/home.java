package com.example.app_readbook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.app_readbook.Name_book.Name;
import com.example.app_readbook.Name_book.NameBookAdaptor;
import com.example.app_readbook.Name_book.book.book;
import com.example.app_readbook.fragment_pager.Home_fragment;
import com.example.app_readbook.fragment_pager.PhotoAdaptor;
import com.example.app_readbook.fragment_pager.model_account.Account_fragment;
import com.example.app_readbook.fragment_pager.model_favorite.Favorite_fragment;
import com.example.app_readbook.fragment_pager.model_search.Search_fragment;
import com.example.app_readbook.fragment_pager.photo;
import com.example.app_readbook.list_book.Main_BookNew;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class home extends AppCompatActivity {
    private ScrollView scrollView;
private AppBarLayout toolbar;
    private RecyclerView recyclerView;
    private NameBookAdaptor nameBookAdaptor;
    private ViewPager2 viewPager2, view;
    private RelativeLayout main;
    private LinearLayout linearLayout;
    private TextView list_bookNew;
    private BottomNavigationView bottomNavigationView;
    private CircleIndicator3 indicator;

    private List<photo> mlist;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {

            int currentPosition = view.getCurrentItem();
            if (currentPosition == mlist.size() - 1) {
                view.setCurrentItem(0);
            } else {
                view.setCurrentItem(currentPosition + 1);
            }
        }
    };

    @SuppressLint({"WrongViewCast", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_home);
        view = findViewById(R.id.view_2);
        scrollView = findViewById(R.id.scv);
        linearLayout = findViewById(R.id.rl);
        main = findViewById(R.id.lo6);
        indicator = findViewById(R.id.cr);
        toolbar = findViewById(R.id.bar_footer);
        list_bookNew = findViewById(R.id.all_bookNew);
        mlist = getListphoto();
        bottomNavigationView = findViewById(R.id.btn_navigatione);
        PhotoAdaptor photoAdaptor = new PhotoAdaptor(mlist);
        view.setAdapter(photoAdaptor);
        indicator.setViewPager(view);
        recyclerView = findViewById(R.id.rcv_name);
        nameBookAdaptor = new NameBookAdaptor(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        nameBookAdaptor.setData(getListName());
        recyclerView.setAdapter(nameBookAdaptor);
        statusbar();
        bottomNavigationView.setSelectedItemId(R.id.btn_home);
        bottomNavigationView.setOnItemSelectedListener(navListener);
        view.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mHandler.removeCallbacks(mRunnable);
                mHandler.postDelayed(mRunnable, 3000);


            }
        });
        list_bookNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this , Main_BookNew.class);
                startActivity(intent);
            }
        });

    }
private NavigationBarView.OnItemSelectedListener navListener = new NavigationBarView.OnItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
         switch (item.getItemId()){
            case R.id.btn_home:
                toolbar.setVisibility(View.VISIBLE);
                main.setVisibility(View.VISIBLE);
                showFragment(new Home_fragment());
                view.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_search:
                toolbar.setVisibility(View.GONE);
                showFragment(new Search_fragment());
                main.setVisibility(View.GONE);
                view.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                break;
            case R.id.btn_favorite:
                toolbar.setVisibility(View.GONE);
                showFragment(new Favorite_fragment());
                main.setVisibility(View.GONE);
                view.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                break;
            case R.id.btn_account:
                toolbar.setVisibility(View.GONE);
                showFragment(new Account_fragment());
                main.setVisibility(View.GONE);
                view.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
                break;
            default:
                toolbar.setVisibility(View.VISIBLE);
                main.setVisibility(View.GONE);
                showFragment(new Home_fragment());
                view.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
                break;
        }
        return true;
    }
    };

    @Override
    public void onBackPressed() {
        if (bottomNavigationView.getSelectedItemId() == R.id.btn_home) {
            super.onBackPressed();
            finish();
        }else{
            bottomNavigationView.setSelectedItemId(R.id.btn_home);
        }
    }

    private boolean showFragment(Fragment fragment) {
        if(fragment!=null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.main, fragment);
            fragmentTransaction.commit();
        }
        return  true;
    }

    private void statusbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        getWindow().setStatusBarColor(ContextCompat.getColor(home.this, R.color.background_color));
    }

    //    private void toolbarNavigation() {
//        Toolbar toolbar = findViewById(R.id.detail1_toolbar);
//        setSupportActionBar(toolbar);
//        toolbar.setNavigationOnClickListener(view -> onBackPressed());
//    }
    public List<photo> getListphoto() {
        List<photo> list = new ArrayList<>();
        list.add(new photo(R.drawable.sachhome));
        list.add(new photo(R.drawable.sachnew1));
        list.add(new photo(R.drawable.sachnew2));
        list.add(new photo(R.drawable.sachnew3));
        list.add(new photo(R.drawable.sachnew4));
        return list;
    }

    private List<Name> getListName() {
        List<Name> ls = new ArrayList<>();
        List<book> list = new ArrayList<>();
        list.add(new book(R.drawable.sach1, "Kinh Doanh Online"));
        list.add(new book(R.drawable.sach2, "Đắc Nhân Tâm"));
        list.add(new book(R.drawable.sach3, "Cha Giàu Cha.."));
        list.add(new book(R.drawable.sach4, "Nghĩ Giàu .."));
        list.add(new book(R.drawable.sach1, "Không Biết Tên"));
        list.add(new book(R.drawable.sach1, "Giàu Từ Đâu"));
        list.add(new book(R.drawable.sach1, "Giàu Như ..."));
        list.add(new book(R.drawable.sach1, "Cách Làm Giàu"));
        list.add(new book(R.drawable.sach1, "Làm Giàu .."));
        list.add(new book(R.drawable.sach1, "Ngủ Cũng Giàu"));
        ls.add(new Name("Sách Kinh Doanh", "(10)", "xem tất cả", list));
        ls.add(new Name("Sách Khoa Học", "(10)", "xem tất cả", list));
        ls.add(new Name("Sách Làm Giàu", "(10)", "xem tất cả", list));
        return ls;
    }
}
