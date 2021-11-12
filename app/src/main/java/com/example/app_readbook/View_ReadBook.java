package com.example.app_readbook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app_readbook.Model.DanhMucSach;
import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.Model.User;
import com.example.app_readbook.Model.chitietsach;
import com.example.app_readbook.Model.danhgia;
import com.example.app_readbook.Service.ApiInterface;
import com.example.app_readbook.Service.ApiService;
import com.example.app_readbook.chapter.Main_Chapter;
import com.example.app_readbook.list_comment.Comment;
import com.example.app_readbook.list_comment.CommentAdaptor;
import com.example.app_readbook.list_comment.Main_NodeReadBook;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class View_ReadBook extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<Comment> mlist;
    private ArrayList<Sach> saches;
    private ArrayList<chitietsach> chitietsach;
    private ArrayList<DanhMucSach> danhMucSaches;
    private ArrayList<danhgia> danhgias;
    private CommentAdaptor commentAdaptor;
    private ImageView img_book;
    private CollapsingToolbarLayout coordinatorLayout;
    private AppCompatButton btnRead ;
    Sach sach;
    DanhMucSach danhMucSach;
    chitietsach chitiet;
    danhgia danhgia;
    private FloatingActionButton favorite;
    private AppCompatTextView textView_book , next_page , textView_tacGia , textView_DanhMuc , textView_NXB , textView_nameBook , node;
    private Toolbar toolbar;
    User user;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_read);

        initUI();
        iniUIIntent();
        getDataViewSach();
        next_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = node.getText().toString().trim();
                String nameBook = textView_nameBook.getText().toString().trim();
                Intent intent = new Intent(View_ReadBook.this , Main_NodeReadBook.class);
                Bundle bundle = new Bundle();
                bundle.putString("object" , name);
                bundle.putString("object_book" , nameBook);
                bundle.putString("image" , String.valueOf(img_book));
                intent.putExtra("object_node" , bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saches = new ArrayList<>();
                String book = textView_nameBook.getText().toString().trim();
                Intent intent = new Intent(View_ReadBook.this , Main_Chapter.class);
//                intent.putExtra("view_sach" , );
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    private void getDataViewSach() {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<List<danhgia>> mSach = apiInterface.LoadDanhgia(sach.getIdSach());
        mSach.enqueue(new Callback<List<danhgia>>() {
            @Override
            public void onResponse(Call<List<danhgia>> call, Response<List<danhgia>> response) {
                danhgias = (ArrayList<com.example.app_readbook.Model.danhgia>) response.body();
                recyclerView.setLayoutManager(new LinearLayoutManager(View_ReadBook.this , LinearLayoutManager.VERTICAL , false));
                commentAdaptor = new CommentAdaptor(danhgias , View_ReadBook.this);
                recyclerView.setAdapter(commentAdaptor);
            }

            @Override
            public void onFailure(Call<List<danhgia>> call, Throwable t) {

            }
        });

    }

    @SuppressLint("SetTextI18n")
    private void iniUIIntent() {
        Intent intent = getIntent();
        if(intent.hasExtra("sach"))
        {
            sach = (Sach) intent.getSerializableExtra("sach");
            textView_nameBook.setText(sach.getTensach());
            textView_tacGia.setText(sach.getTacgia());
            textView_NXB.setText(sach.getNxb());
            Glide.with(this).load(sach.getImgSach()).into(img_book);
            node.setText(sach.getTomtatND() +" " +node.getText());
            node.setTextSize(12);
            coordinatorLayout.setTitle(sach.getIdDanhmuc());

        }

    }

    @Override
    public void onBackPressed() {
        toolbar = findViewById(R.id.DanhMuc);
        setSupportActionBar(toolbar);
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
        textView_nameBook  = findViewById(R.id.txt_nameBook);
        textView_tacGia = findViewById(R.id.txt_tacGiaBook);
        textView_NXB = findViewById(R.id.txt_NXB);
        node = findViewById(R.id.node_textBook);
        favorite = findViewById(R.id.btn_favoriteView);
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDatFavorite();
            }
        });
    }

    private void getDatFavorite() {
        favorite.setImageResource(R.drawable.ic_baseline_favorite_1_24);
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<String> callback = apiInterface.UpdateFavorite(user.getIdMember(),String.valueOf(saches.get(Integer.parseInt(sach.getIdSach()))));
        callback.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                String ketqua = response.body();
                if(ketqua.equals("Success"))
                {
                    Toast.makeText(View_ReadBook.this, "Đã thích", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(View_ReadBook.this, "Thích không thành công", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
        favorite.setEnabled(false);
    }
}
