package com.example.app_readbook.fragment_pager.model_home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.app_readbook.R;
import com.example.app_readbook.fragment_pager.PhotoAdaptor;
import com.example.app_readbook.fragment_pager.photo;
import com.example.app_readbook.home;
import com.example.app_readbook.list_book.Main_BookNew;
import com.example.app_readbook.testAdaptor.AllItemAdaptor;
import com.example.app_readbook.testAdaptor.all_item;
import com.example.app_readbook.testAdaptor.book_item;
import com.example.app_readbook.testAdaptor.name_item;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;


public class Home_fragment extends Fragment{
    private View mView;
    home mHome;
    private CircleIndicator3 indicator;
    private ViewPager2  view;
    private RecyclerView recyclerView;
    private List<photo> mlist;
    private TextView list_bookNew;
    private LinearLayout layout_bookNew;
    private AllItemAdaptor allItemAdaptor;
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
    public Home_fragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home_fragment, container, false);
        mHome = new home();
        indicator = mView.findViewById(R.id.cr);
        mlist = getListphoto();
        list_bookNew = mView.findViewById(R.id.all_bookNew);
        view = mView.findViewById(R.id.view_2);
        layout_bookNew = mView.findViewById(R.id.id3);
        PhotoAdaptor photoAdaptor = new PhotoAdaptor(mlist);
        view.setAdapter(photoAdaptor);
        indicator.setViewPager(view);
        recyclerView = mView.findViewById(R.id.rcv_name);
        allItemAdaptor = new AllItemAdaptor();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        allItemAdaptor.setData(getActivity() , getListData());
        recyclerView.setAdapter(allItemAdaptor);
        allItemAdaptor.setOnItemClickListener(new AllItemAdaptor.OnClickListener() {
            @Override
            public void IClick(int position) {

                    Intent intent = new Intent(getActivity(), Main_BookNew.class);
                    getActivity().startActivity(intent);

            }
        });

        view.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mHandler.removeCallbacks(mRunnable);
                mHandler.postDelayed(mRunnable, 3000);
            }
        });
        layout_bookNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getActivity();
                Intent intent = new Intent(context , Main_BookNew.class);
                context.startActivity(intent);
            }
        });
        return mView;
    }

    private List<all_item> getListData() {
        List<all_item> list = new ArrayList<>();
        List<book_item> items = new ArrayList<>();
        List<name_item> name_items = new ArrayList<>();
        name_items.add(new name_item("Sách Kinh Doanh" , "10" , "xem tất cả"));
        items.add(new book_item(R.drawable.sach1, "Kinh Doanh Online"));
        items.add(new book_item(R.drawable.sach2, "Đắc Nhân Tâm"));
        items.add(new book_item(R.drawable.sach3, "Cha Giàu Cha.."));
        items.add(new book_item(R.drawable.sach4, "Nghĩ Giàu .."));
        items.add(new book_item(R.drawable.sach1, "Không Biết Tên"));
        items.add(new book_item(R.drawable.sach1, "Giàu Từ Đâu"));
        items.add(new book_item(R.drawable.sach1, "Giàu Như ..."));
        items.add(new book_item(R.drawable.sach1, "Cách Làm Giàu"));
        items.add(new book_item(R.drawable.sach1, "Làm Giàu .."));
        items.add(new book_item(R.drawable.sach1, "Ngủ Cũng Giàu"));
        list.add(new all_item(AllItemAdaptor.TYPE_NAME , null , name_items));
        list.add(new all_item(AllItemAdaptor.TYPE_BOOK , items , null));
        list.add(new all_item(AllItemAdaptor.TYPE_NAME , null , name_items));
        list.add(new all_item(AllItemAdaptor.TYPE_BOOK , items , null));
        list.add(new all_item(AllItemAdaptor.TYPE_NAME , null , name_items));
        list.add(new all_item(AllItemAdaptor.TYPE_BOOK , items , null));
        return list;


    }

    private List<photo> getListphoto() {
        List<photo> list = new ArrayList<>();
        list.add(new photo(R.drawable.sachhome));
        list.add(new photo(R.drawable.sachnew1));
        list.add(new photo(R.drawable.sachnew2));
        list.add(new photo(R.drawable.sachnew3));
        list.add(new photo(R.drawable.sachnew4));
        return list;
    }


}


