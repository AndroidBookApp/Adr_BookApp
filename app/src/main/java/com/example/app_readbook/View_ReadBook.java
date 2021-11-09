package com.example.app_readbook;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app_readbook.chapter.Main_Chapter;
import com.example.app_readbook.list_book.list_book;
import com.example.app_readbook.list_comment.Comment;
import com.example.app_readbook.list_comment.CommentAdaptor;
import com.example.app_readbook.list_comment.Main_NodeReadBook;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.ArrayList;
import java.util.List;

public class View_ReadBook extends AppCompatActivity {
    RecyclerView recyclerView;
    private List<Comment> mlist;
    private List<list_book> list;
    private CommentAdaptor commentAdaptor;
    private ImageView img_book;
    private CollapsingToolbarLayout coordinatorLayout;
    private AppCompatButton btnRead ;
    private AppCompatTextView textView_book , next_page , textView_tacGia , textView_DanhMuc , textView_NXB , textView_nameBook , node;
    private Toolbar toolbar;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_read);
        mlist = getListComment();
        initUI();
        iniUIIntent();

        commentAdaptor = new CommentAdaptor(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this ,RecyclerView.VERTICAL , false);
        recyclerView.setLayoutManager(linearLayoutManager);
        commentAdaptor.setData(getListComment());
        recyclerView.setAdapter(commentAdaptor);
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
                String book = textView_nameBook.getText().toString().trim();
                Intent intent = new Intent(View_ReadBook.this , Main_Chapter.class);
                intent.putExtra("nameBook", book);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    @SuppressLint("SetTextI18n")
    private void iniUIIntent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("put_book");
        textView_nameBook.setText(bundle.getString("name"));
        textView_tacGia.setText(bundle.getString("tac_gia"));
//        textView_DanhMuc.setText(bundle.getString("TenDanhMuc"));
        int NXB = bundle.getInt("NgayXB" , 0);
        textView_NXB.setText("Năm Xuất Bản " +String.valueOf(NXB));
        String title = bundle.getString("TenDanhMuc");
        String image = bundle.getString("img_book");
        Glide.with(this).load(image).into(img_book);
//        textView_nameBook.setText(getIntent().getStringExtra("name"));
//        textView_nameBook.setTextSize(14);
        node.setText(bundle.getString("TomTatND") +" " +node.getText());
        node.setTextSize(12);
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
//        coordinatorLayout.setTag("TenDanhMuc");

        coordinatorLayout.setTitle(getString(R.string.app_name));
    }

    private List<Comment> getListComment() {
        List<Comment> list = new ArrayList<>();
        list.add(new Comment(R.drawable.account, "Nguyễn Văn Quỳnh" ,
                "Đây là một cuốn sách hay nhất tôi từng đọc" , "thích","Phản hồi ","1 ngày trước") );
        list.add(new Comment(R.drawable.account, "Nguyễn Văn Vinh" ,
                "Đây là một cuốn sách hay nhất tôi từng đọc" , "thích","Phản hồi ","1 ngày trước") );
        list.add(new Comment(R.drawable.account, "Nguyễn Văn Quỳnh" ,
                "Đây là một cuốn sách hay nhất tôi từng đọc" , "thích","Phản hồi ","1 ngày trước") );
        list.add(new Comment(R.drawable.account, "Nguyễn Văn Quỳnh" ,
                "Đây là một cuốn sách hay nhất tôi từng đọc" , "thích","Phản hồi ","1 ngày trước") );
        list.add(new Comment(R.drawable.account, "Nguyễn Văn Quỳnh" ,
                "Đây là một cuốn sách hay nhất tôi từng đọc" , "thích","Phản hồi ","1 ngày trước") );
        list.add(new Comment(R.drawable.account, "Nguyễn Văn Quang" ,
                "Đây là một cuốn sách hay nhất tôi từng đọc" , "thích","Phản hồi ","1 ngày trước") );

        return  list;
    }
}
