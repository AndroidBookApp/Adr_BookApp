package com.example.app_readbook.View.readbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.app_readbook.Model.Chuong;
import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.R;
import com.example.app_readbook.shareFreferences.DataManager;
import com.squareup.picasso.Picasso;


public class ReadbookFragment extends Fragment {
    public boolean isDark;
    private Chuong chuongList;
    private Sach sach;
    private RelativeLayout layout;
    String idChuong, idSach;
    private TextView textView ;
    private ImageView img_book;
    public ReadbookFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mview = inflater.inflate(R.layout.fragment_readbook, container, false);
        textView = mview.findViewById(R.id.txt_readBook);
        img_book = mview.findViewById(R.id.img_sach);
        layout = mview.findViewById(R.id.layout_dark);
        sach = DataManager.loadObjectSach();
        chuongList = DataManager.lChapter();
        idChuong = chuongList.getIdChuong();
        idSach = sach.getIdSach();
        Bundle bundle = getArguments();
        if (bundle != null) {
            Chuong chapters = (Chuong) bundle.get("readBook_object");
            textView.setText(chapters.getNoidung());
            Picasso.get().load(chapters.getImgSach()).into(img_book);
            isDark = bundle.getBoolean("dark");
            if(isDark)
            {
                setDarkTheme();
            }
        }
        return mview;
    }

    private void setDarkTheme() {
        layout.setBackgroundResource(R.color.background__dark_color);
        textView.setTextColor(getResources().getColor(R.color.white));
    }
}