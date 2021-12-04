package com.example.app_readbook.View.View_Readbook;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.Model.User;
import com.example.app_readbook.Model.danhgia;
import com.example.app_readbook.R;
import com.example.app_readbook.Service.ApiInterface;
import com.example.app_readbook.Service.ApiService;
import com.example.app_readbook.View.list_comment.CommentAdaptor;
import com.example.app_readbook.ViewModel.AddCommentViewModel;
import com.example.app_readbook.home;
import com.example.app_readbook.shareFreferences.DataManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentFragment extends Fragment {
    View mView;
    private ArrayList<com.example.app_readbook.Model.danhgia> danhgias;
    private CommentAdaptor commentAdaptor;
    private ImageView img_MemberComment;
    private AppCompatButton btnSend;
    RecyclerView recyclerView;
    Sach sach;
    String idSach;
    String idUser, imageAvater;
    private AppCompatEditText comment;
    User user;
    String load;
    AddCommentViewModel viewModel;
    public CommentFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_comment, container, false);
        sach = new Sach();
        user = DataManager.loadUser();
        sach = DataManager.loadObjectSach();
        load = DataManager.lFavorite();
        iniUI();
        loadFavorite();
        getDataViewSach();
        return mView;
    }
    private void iniUI() {
        recyclerView = mView.findViewById(R.id.rcv_reabook);
        btnSend = mView.findViewById(R.id.btn_send);
        comment = mView.findViewById(R.id.edit_comment);
        img_MemberComment = mView.findViewById(R.id.comment_avatar);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comments = comment.getText().toString().trim();
                if(comments.isEmpty())
                {
                    Toast.makeText(getActivity(), "Bạn chưa nhập nhận xét", Toast.LENGTH_SHORT).show();
                }else {
                    addComment();
                    DataManager.saveDanhGia(danhgias);
                    DialogCommemt(Gravity.CENTER);
                }

            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AddCommentViewModel.class);
    }

    public void loadData(List<com.example.app_readbook.Model.danhgia> danhgias) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        commentAdaptor = new CommentAdaptor(getActivity());
        recyclerView.setHasFixedSize(true);
        commentAdaptor.setData(danhgias);
        recyclerView.setAdapter(commentAdaptor);
    }

    @SuppressLint("SetTextI18n")
    public void loadFavorite() {
        //load dữ liệu đã lưu trong sharedPreferences ra
        idUser = user.getIdMember();
        idSach = sach.getIdSach();
        imageAvater = user.getImgAvatar();
        Picasso.get().load(user.getImgAvatar()).into(img_MemberComment);

    }

    public void getDataViewSach() {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<List<danhgia>> mSach = apiInterface.LoadDanhgia(idSach);
        mSach.enqueue(new Callback<List<danhgia>>() {
            @Override
            public void onResponse(Call<List<danhgia>> call, Response<List<danhgia>> response) {
                danhgias = (ArrayList<com.example.app_readbook.Model.danhgia>) response.body();
                loadData(danhgias);
            }
            @Override
            public void onFailure(Call<List<danhgia>> call, Throwable t) {
            }
        });

    }
    private void addComment() {
        viewModel.getAddComment().observe(this, new Observer<List<com.example.app_readbook.Model.danhgia>>() {
            @Override
            public void onChanged(List<com.example.app_readbook.Model.danhgia> danhgias) {
                loadData(danhgias);
            }
        });
        String danhgia = Objects.requireNonNull(comment.getText()).toString().trim();
        viewModel.iniAddComment(idUser, idSach, danhgia);
    }

    private void DialogCommemt(int gravity) {
        Dialog dialog = new Dialog(getActivity());
        dialog.setCanceledOnTouchOutside(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_comment);
        Button btn_continue = dialog.findViewById(R.id.comment_continue);
        Button btn_backHome = dialog.findViewById(R.id.comment_back);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.gravity = gravity;
        window.setAttributes(attributes);
        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager.loadDanhgia();
                comment.setText("");
                dialog.dismiss();
            }
        });
        btn_backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), home.class);
                startActivity(intent);
            }
        });
        dialog.show();
    }
}