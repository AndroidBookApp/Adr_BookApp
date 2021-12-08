package com.example.app_readbook.View.View_Readbook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;

import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.Model.User;
import com.example.app_readbook.R;
import com.example.app_readbook.View.readbook.MainReadbook;
import com.example.app_readbook.shareFreferences.DataManager;


public class MainFragment extends Fragment {
    private AppCompatTextView  textView_tacGia, textView_NXB, textView_nameBook, node;
    private AppCompatButton btnRead;
    private View mView;
    User user;
    String load;
    Sach sach;
    String idSach;
    String idUser, memberName, imageAvater;
    View_ReadBook view_readBook;
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_main, container, false);
        view_readBook = new View_ReadBook();
        sach = new Sach();
        user = DataManager.loadUser();
        sach = DataManager.loadObjectSach();
        load = DataManager.lFavorite();
        iniUI();
        loadFavorite();
        return mView;
    }
    @SuppressLint("SetTextI18n")
    private void loadFavorite() {
        //load dữ liệu đã lưu trong sharedPreferences ra
        idUser = user.getIdMember();
        idSach = sach.getIdSach();
        memberName = user.getMemberName();
        imageAvater = user.getImgAvatar();
        textView_nameBook.setText(sach.getTensach());
        textView_tacGia.setText("Tác Giả :" + sach.getTacgia());
        textView_NXB.setText("Năm Xuất Bản :" + sach.getNxb());
        node.setText(sach.getTomtatND());
    }
    public void iniUI() {
        btnRead = mView.findViewById(R.id.read_book);
        textView_nameBook = mView.findViewById(R.id.txt_nameBook);
        textView_tacGia = mView.findViewById(R.id.txt_tacGiaBook);
        textView_NXB = mView.findViewById(R.id.txt_NXB);
        node = mView.findViewById(R.id.node_textBook);
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainReadbook.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}