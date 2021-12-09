package com.example.app_readbook.View.readbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.app_readbook.Model.Chuong;
import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.R;
import com.example.app_readbook.ViewModel.ReadBookViewModel;
import com.example.app_readbook.shareFreferences.DataManager;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ReadbookFragment extends Fragment {
    public static final String TAG = ReadbookFragment.class.getName();
    private TextView btn_back, btn_next, txt_book, txt_book_all, read;
    private ViewPager viewPager;
    private List<Chuong> chapters;
    private Chuong chuongList;
    private Sach sach;
    private Toolbar toolbar;
    String idChuong, idSach;
    private RecyclerView rcv_readbook;
    private AdaptorReadBook adaptorReadBook;
    private ReadBookViewModel readBookViewModel;
    private TextView textView, tv_page;
    private ImageView img_book;

    public ReadbookFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mview = inflater.inflate(R.layout.fragment_readbook, container, false);
        textView = mview.findViewById(R.id.txt_readBook);
        img_book = mview.findViewById(R.id.img_sach);
        sach = DataManager.loadObjectSach();
        chuongList = DataManager.lChapter();
        idChuong = chuongList.getIdChuong();
        idSach = sach.getIdSach();
        Bundle bundle = getArguments();
        if (bundle != null) {
            Chuong chapters = (Chuong) bundle.get("readBook_object");
            textView.setText(chapters.getNoidung());
            Picasso.get().load(chapters.getImgSach()).into(img_book);
        }
        return mview;
    }
}