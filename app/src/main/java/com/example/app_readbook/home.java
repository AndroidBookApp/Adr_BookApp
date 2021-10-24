package com.example.app_readbook;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.app_readbook.Name_book.Name;
import com.example.app_readbook.Name_book.NameBookAdaptor;
import com.example.app_readbook.book.book;
import com.example.app_readbook.fragment.PhotoAdaptor;
import com.example.app_readbook.fragment.ViewPagerAdaptor2;
import com.example.app_readbook.fragment.photo;
import com.example.app_readbook.model_search.IClickHistory;
import com.example.app_readbook.model_search.history;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class home extends AppCompatActivity {
    private ScrollView scrollView;
    private RecyclerView recyclerView;
    private NameBookAdaptor nameBookAdaptor;
    private ViewPager2  viewPager2, view;
    private RelativeLayout layout , relativeLayout_footer;

private LinearLayout relativeLayout;

    private BottomNavigationView bottomNavigationView;
    private CircleIndicator3 indicator;
    private List<photo> mlist;
    private final Handler mHandler = new Handler(Looper.getMainLooper());
    private final Runnable mRunnable = new Runnable() {
        @Override
        public void run() {

            int currentPosition =  view.getCurrentItem();
            if(currentPosition == mlist.size()  - 1 )
            {
                view.setCurrentItem(0);
            }else {
                view.setCurrentItem(currentPosition + 1);
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_home);
        relativeLayout_footer = findViewById(R.id.lo6);
        viewPager2 = findViewById(R.id.viewview);
        view = findViewById(R.id.view_2);
        scrollView = findViewById(R.id.scv);
        relativeLayout = findViewById(R.id.rl);
        indicator  = findViewById(R.id.cr);
        layout = findViewById(R.id.lo_main);
        mlist = getListphoto();
        bottomNavigationView = findViewById(R.id.btn_navigatione);
        ViewPagerAdaptor2 viewPagerAdaptor2 = new ViewPagerAdaptor2(this);
        viewPager2.setAdapter(viewPagerAdaptor2);
        PhotoAdaptor photoAdaptor = new PhotoAdaptor(mlist);
        view.setAdapter(photoAdaptor);
        indicator.setViewPager(view);
        recyclerView = findViewById(R.id.rcv_name);
        nameBookAdaptor = new NameBookAdaptor(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this , RecyclerView.VERTICAL , false);
        recyclerView.setLayoutManager(linearLayoutManager);
        nameBookAdaptor.setData(getListName());
        recyclerView.setAdapter(nameBookAdaptor);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.btn_home:
//                        viewPager2.setCurrentItem(0);
                        relativeLayout_footer.setVisibility(View.VISIBLE);
                        view.setVisibility(View.VISIBLE);
                        relativeLayout.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.VISIBLE);
                        layout.setVisibility(View.VISIBLE);
                        scrollView.setVisibility(View.VISIBLE);
//                        viewPager2.setVisibility(View.VISIBLE);

                    break;
                    case R.id.btn_search:
                        listHistory();
                        relativeLayout_footer.setVisibility(View.VISIBLE);
////                        viewPager2.setCurrentItem(1);
//                        view.setVisibility(View.GONE);
//                        relativeLayout.setVisibility(View.GONE);
//                        recyclerView.setVisibility(View.GONE);
////                        scrollView.setVisibility(View.GONE);
////                        viewPager2.setVisibility(View.GONE);
////                        layout.setVisibility(View.GONE);
                        viewPager2.setCurrentItem(0);
                        view.setVisibility(View.VISIBLE);
                        relativeLayout.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.VISIBLE);
                        layout.setVisibility(View.VISIBLE);
                        scrollView.setVisibility(View.VISIBLE);
                        break;
                    case R.id.btn_favorite:
                        relativeLayout_footer.setVisibility(View.GONE);
                        viewPager2.setCurrentItem(2);
                        view.setVisibility(View.GONE);
                        relativeLayout.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.GONE);
//                      viewPager2.setVisibility(View.GONE);
//                        layout.setVisibility(View.GONE);
//                        scrollView.setVisibility(View.GONE);

                        break;
                    case R.id.btn_account:
                        relativeLayout_footer.setVisibility(View.GONE);
                        viewPager2.setCurrentItem(3);
                        view.setVisibility(View.GONE);
                        relativeLayout.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.GONE);
//                        viewPager2.setVisibility(View.GONE);
//                        scrollView.setVisibility(View.GONE);
//                        layout.setVisibility(View.GONE);
                        break;

                }
                return true;
            }
        });
        view.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
               super.onPageSelected(position);
                mHandler.removeCallbacks(mRunnable);
                mHandler.postDelayed(mRunnable , 3000);


            }
        });
    }
    public List<photo> getListphoto() {
        List<photo> list = new ArrayList<>();
        list.add(new photo(R.drawable.sachhome));
        list.add(new photo(R.drawable.nenxanhla));
        list.add(new photo(R.drawable.nenvang));
        list.add(new photo(R.drawable.nenhong));
        return list;
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
        ls.add(new Name("Sách Kinh Doanh" ,"(10)","xem tất cả", list));
        ls.add(new Name("Sách Khoa Học","(10)","xem tất cả" ,list));
        ls.add(new Name("Sách Làm Giàu" ,"(10)","xem tất cả",list));
        return ls;
    }
    private void listHistory(){
        List<history> list = new ArrayList<>();
        list.add(new history(R.drawable.ic_baseline_access_time_24 , "Đắc Nhân Tâm" , R.drawable.ic_baseline_clear_24));
        list.add(new history(R.drawable.ic_baseline_access_time_24 , "Đắc Nhân Sĩ" , R.drawable.ic_baseline_clear_24));
        list.add(new history(R.drawable.ic_baseline_access_time_24 , "Kinh Doanh Online" , R.drawable.ic_baseline_clear_24));
        list.add(new history(R.drawable.ic_baseline_access_time_24 , "Nghĩ giàu làm giàu" , R.drawable.ic_baseline_clear_24));
        list.add(new history(R.drawable.ic_baseline_access_time_24 , "Đắc Nhân Tâm" , R.drawable.ic_baseline_clear_24));
        list.add(new history(R.drawable.ic_baseline_access_time_24 , "Đắc Nhân Tâm" , R.drawable.ic_baseline_clear_24));
        MyBottomSheetDialogFragment myBottomSheetDialogFragment = new MyBottomSheetDialogFragment(list, new IClickHistory() {
            @Override
            public void clickItem(history history) {

            }
        });
        myBottomSheetDialogFragment.show(getSupportFragmentManager(), myBottomSheetDialogFragment.getTag());
    }
}
