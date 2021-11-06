package com.example.app_readbook.list_comment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.R;
import com.example.app_readbook.chapter.Main_Chapter;

import java.util.ArrayList;
import java.util.List;

public class View_Readbook extends AppCompatActivity {
    private static final int MY_NEXT_PAGE =1 ;
    RecyclerView recyclerView;
private List<Comment> mlist;
private CommentAdaptor commentAdaptor;
private ImageView img_book;
private TextView textView_nameBook , node;
private Button btnRead;
private TextView textView_book , next_page , textView_tacGia;
private Toolbar toolbar;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview);
        img_book = findViewById(R.id.image_book);
        textView_nameBook = findViewById(R.id.txt_nameBook);
        textView_book  = findViewById(R.id.bookName);
        node = findViewById(R.id.node_textBook);
        btnRead = findViewById(R.id.read);
        textView_tacGia = findViewById(R.id.txt_tacGiaBook);
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("object_itemBook");
        textView_nameBook.setText(bundle.getString("name_book"));
        img_book.setImageResource((Integer) bundle.getSerializable("img"));
        textView_book.setText(getIntent().getStringExtra("name"));
        textView_book.setTextSize(14);
        node.setText(getIntent().getStringExtra("name") +" " +node.getText());
        node.setTextSize(12);
        toolbar = findViewById(R.id.toolbar_comment);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        next_page = findViewById(R.id.next_pageBook);
        recyclerView = findViewById(R.id.rcv_reabook);
        mlist = getListComment();
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
                Intent intent = new Intent(View_Readbook.this , Main_NodeReadBook.class);
                Bundle bundle = new Bundle();
                bundle.putString("object" , name);
                bundle.putString("object_book" , nameBook);
                intent.putExtra("object_node" , bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String book = textView_nameBook.getText().toString().trim();
                Intent intent = new Intent(View_Readbook.this , Main_Chapter.class);
                intent.putExtra("nameBook", book);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        toolbar = findViewById(R.id.toolbar_comment);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        super.onBackPressed();
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
