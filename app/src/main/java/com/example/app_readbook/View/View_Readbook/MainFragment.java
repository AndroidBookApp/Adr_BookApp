package com.example.app_readbook.View.View_Readbook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.Model.DanhMucSach;
import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.Model.User;
import com.example.app_readbook.R;
import com.example.app_readbook.View.Adaptor.CategoryAdaptor;
import com.example.app_readbook.View.readbook.MainReadbook;
import com.example.app_readbook.ViewModel.Service.ApiInterface;
import com.example.app_readbook.ViewModel.Service.ApiService;
import com.example.app_readbook.shareFreferences.DataManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainFragment extends Fragment {
    private AppCompatTextView  textView_tacGia, textView_NXB, textView_nameBook, node;
    private AppCompatButton btnRead;
    private View mView;
    User user;
    String  danhmuc;
    Sach sach;
    String idSach;
    private List<DanhMucSach> danhMucSaches;
    private TextView tv_comment , tv_view , tv_like;
    private RecyclerView recyclerView;
    String idUser, memberName, imageAvater;
    View_ReadBook view_readBook;
    private CategoryAdaptor categoryAdaptor;
    private List<Sach> saches;
    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        view_readBook = new View_ReadBook();
        sach = new Sach();
        user = DataManager.loadUser();
        sach = DataManager.loadObjectSach();
        danhmuc = DataManager.loadObjectSach().getIdDanhmuc();
        iniUI();
        loadFavorite();
        loadDataCategory();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_main, container, false);
        return mView;
    }
    @SuppressLint("SetTextI18n")
    private void loadFavorite() {
        //load d??? li???u ???? l??u trong sharedPreferences ra
        idUser = user.getIdMember();
        idSach = sach.getIdSach();
        memberName = user.getMemberName();
        imageAvater = user.getImgAvatar();
        textView_nameBook.setText(sach.getTensach());
        textView_tacGia.setText("T??c Gi??? :" + sach.getTacgia());
        textView_NXB.setText("N??m Xu???t B???n :" + sach.getNxb());
        tv_comment.setText(sach.getFeedback());
        tv_view.setText(sach.getLuotxem());
        tv_like.setText(sach.getFavorite());
        node.setText(sach.getTomtatND());
    }
    public void iniUI() {
        btnRead = mView.findViewById(R.id.read_book);
        textView_nameBook = mView.findViewById(R.id.txt_nameBook);
        textView_tacGia = mView.findViewById(R.id.txt_tacGiaBook);
        textView_NXB = mView.findViewById(R.id.txt_NXB);
        node = mView.findViewById(R.id.node_textBook);
        recyclerView = mView.findViewById(R.id.rcv_category);
        tv_comment = mView.findViewById(R.id.comment);
        tv_view = mView.findViewById(R.id.ic_view);
        tv_like = mView.findViewById(R.id.icon_like);
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainReadbook.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                DataManager.saveObjectSach(sach);
                startActivity(intent);
            }
        });
    }
    private void loadDataCategory()
    {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<List<Sach>> listCall = apiInterface.listDanhMuc(danhmuc);
        listCall.enqueue(new Callback<List<Sach>>() {
            @Override
            public void onResponse(Call<List<Sach>> call, Response<List<Sach>> response) {
                saches = response.body();
                if(saches!=null)
                {
                    categoryAdaptor = new CategoryAdaptor(saches , getContext());
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL , false));
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(categoryAdaptor);
                }
            }
            @Override
            public void onFailure(Call<List<Sach>> call, Throwable t) {

            }
        });
    }
}