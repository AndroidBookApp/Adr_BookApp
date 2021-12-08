package com.example.app_readbook.View.readbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.app_readbook.Model.Chapter;
import com.example.app_readbook.Model.Chuong;
import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.R;
import com.example.app_readbook.ViewModel.ReadBookViewModel;
import com.example.app_readbook.shareFreferences.DataManager;

import java.util.List;


public class ReadbookFragment extends Fragment {
    public static final String TAG = ReadbookFragment.class.getName();
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
    private TextView textView, tv_page;

    public ReadbookFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mview = inflater.inflate(R.layout.fragment_readbook, container, false);
        rcv_readbook = mview.findViewById(R.id.rcv_readBook);
        sach = DataManager.loadObjectSach();
        chuongList = DataManager.lChapter();
        idChuong = chuongList.getIdChuong();
        idSach = sach.getIdSach();
        Bundle bundle = getArguments();
        if (bundle != null) {
            ReadbookName readbookName = (ReadbookName) bundle.get("readBook_object");
            textView.setText(readbookName.getName());
        }
        return mview;
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        readBookViewModel = new ViewModelProvider(this).get(ReadBookViewModel.class);
//        readBookViewModel.getListChap().observe(this, new Observer<List<Chapter>>() {
//            @Override
//            public void onChanged(List<Chapter> chapter) {
//                chapters = chapter;
//                if (chapters != null) {
//                    DataReadBook(chapters);
//                }
//            }
//        });
//    }
//    private void DataReadBook(List<Chuong> chapters) {
//        adaptorReadBook = new AdaptorReadBook();
//        adaptorReadBook.setData(chapters);
//        rcv_readbook.setHasFixedSize(true);
//        rcv_readbook.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
//        rcv_readbook.setAdapter(adaptorReadBook);
//    }
}