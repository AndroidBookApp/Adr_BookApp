package com.example.app_readbook;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.Model.DanhMucSach;
import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.Model.User;
import com.example.app_readbook.Model.chitietsach;
import com.example.app_readbook.Model.danhgia;
import com.example.app_readbook.Service.ApiInterface;
import com.example.app_readbook.Service.ApiService;
import com.example.app_readbook.View.chapter.Main_Chapter;
import com.example.app_readbook.View.list_comment.Comment;
import com.example.app_readbook.View.list_comment.CommentAdaptor;
import com.example.app_readbook.View.list_comment.Main_NodeReadBook;
import com.example.app_readbook.ViewModel.AddCommentViewModel;
import com.example.app_readbook.ViewModel.AddFavoriteViewModel;
import com.example.app_readbook.shareFreferences.DataManager;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class View_ReadBook extends AppCompatActivity {
    private static final String SHARE_DANHGIA = "SHARE_DANHGIA";
    RecyclerView recyclerView;
    private List<Comment> mlist;
    private ArrayList<Sach> saches;
    private ArrayList<chitietsach> chitietsach;
    private ArrayList<DanhMucSach> danhMucSaches;
    private ArrayList<danhgia> danhgias;
    private CommentAdaptor commentAdaptor;
    private ImageView img_book, img_MemberComment;
    private CollapsingToolbarLayout coordinatorLayout;
    private AppCompatButton btnRead, btnSend;
    Sach sach;
    String idSach;
    String idUser, memberName, imageAvater;
    DanhMucSach danhMucSach;
    chitietsach chitiet;
    danhgia danhgia;
    private FloatingActionButton favorites;
    private AppCompatTextView next_page, textView_tacGia, textView_NXB, textView_nameBook, node;
    private Toolbar toolbar;
    private AppCompatEditText comment;
    User user;
    String load;
    AddCommentViewModel viewModel;
    AddFavoriteViewModel favoriteViewModel;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_read);
        load = DataManager.lFavorite();
        initUI();
        getDatFavorite();
        loadFavorite();
        getDataViewSach();

    }

    @SuppressLint("SetTextI18n")
    private void loadFavorite() {
        //load dữ liệu đã lưu trong sharedPreferences ra
        sach = new Sach();

        user = DataManager.loadUser();
        sach = DataManager.loadObjectSach();
        idSach = DataManager.loadObjectSach().getIdSach();
        Picasso.get().load(user.getImgAvatar()).into(img_MemberComment);
        memberName = user.getMemberName();
        imageAvater = user.getImgAvatar();
        textView_nameBook.setText(sach.getTensach());
        textView_tacGia.setText("Tác Giả :" + sach.getTacgia());
        textView_NXB.setText("Năm Xuất Bản :" + sach.getNxb());
        Picasso.get().load(sach.getImgSach()).into(img_book);
        node.setText(sach.getTomtatND());
        coordinatorLayout.setTitle(sach.getTensach());

    }
    public void getDataViewSach() {
        loadFavorite();
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<List<danhgia>> mSach = apiInterface.LoadDanhgia(idSach);
        mSach.enqueue(new Callback<List<danhgia>>() {
            @Override
            public void onResponse(Call<List<danhgia>> call, Response<List<danhgia>> response) {
                danhgias = (ArrayList<com.example.app_readbook.Model.danhgia>) response.body();
                recyclerView.setLayoutManager(new LinearLayoutManager(View_ReadBook.this, LinearLayoutManager.VERTICAL, false));
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(View_ReadBook.this, DividerItemDecoration.VERTICAL);
                recyclerView.addItemDecoration(dividerItemDecoration);
                commentAdaptor = new CommentAdaptor(View_ReadBook.this);
                commentAdaptor.setData(danhgias);
                recyclerView.setAdapter(commentAdaptor);
            }
            @Override
            public void onFailure(Call<List<danhgia>> call, Throwable t) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        toolbar = findViewById(R.id.DanhMuc);
        setSupportActionBar(toolbar);
        DataManager.loadObjectSach();
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onBackPressed();
    }

    private void initUI() {
        btnRead = findViewById(R.id.read);
        next_page = findViewById(R.id.next_pageBook);
        coordinatorLayout = findViewById(R.id.collapsingToolbarLayout);
        recyclerView = findViewById(R.id.rcv_reabook);
        img_book = findViewById(R.id.image_book);
        textView_nameBook = findViewById(R.id.txt_nameBook);
        textView_tacGia = findViewById(R.id.txt_tacGiaBook);
        textView_NXB = findViewById(R.id.txt_NXB);
        node = findViewById(R.id.node_textBook);
        favorites = findViewById(R.id.btn_favoriteView);
        btnSend = findViewById(R.id.btn_send);
        comment = findViewById(R.id.edit_comment);
        img_MemberComment = findViewById(R.id.comment_avatar);

        favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idUser = user.getIdMember();
                idSach = sach.getIdSach();
                favoriteViewModel.iniAddFavorite(idSach, idUser);
            }
        });
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addComment();
                DataManager.saveDanhGia(danhgias);
                DialogCommemt(Gravity.CENTER);
            }
        });
        next_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(View_ReadBook.this, Main_NodeReadBook.class);
                startActivity(intent);
            }
        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(View_ReadBook.this, Main_Chapter.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private void DialogCommemt(int gravity) {
        Dialog dialog = new Dialog(this);
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
                getDatFavorite();
                dialog.dismiss();
            }
        });
        btn_backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(View_ReadBook.this, home.class);
                startActivity(intent);
            }
        });
        dialog.show();
    }

    public void addComment() {
        viewModel = new ViewModelProvider(View_ReadBook.this).get(AddCommentViewModel.class);
        viewModel.getAddComment().observe(View_ReadBook.this, new Observer<List<com.example.app_readbook.Model.danhgia>>() {
            @Override
            public void onChanged(List<com.example.app_readbook.Model.danhgia> danhgias) {
                commentAdaptor.setData(danhgias);
                recyclerView.setAdapter(commentAdaptor);
            }
        });
        String danhgia = Objects.requireNonNull(comment.getText()).toString().trim();
        viewModel.iniAddComment(idUser, idSach, danhgia);
    }

    private void getDatFavorite() {

        favoriteViewModel = new ViewModelProvider(this).get(AddFavoriteViewModel.class);
        favoriteViewModel.getAddFavorite().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                if(s != null) {
                    favorites.setImageResource(R.drawable.ic_baseline_favorite_1_24);
                    Toast.makeText(View_ReadBook.this, "Thích", Toast.LENGTH_SHORT).show();
                    DataManager.sFavorite(s);
                    loadFavorite();
                }else {
                    favorites.setImageResource(R.drawable.ic_baseline_favorite_24);
                    Toast.makeText(View_ReadBook.this, "Bỏ Thích", Toast.LENGTH_SHORT).show();
                    DataManager.sFavorite(s);
                    loadFavorite();
                }
            }

        });

    }



}
