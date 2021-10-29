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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.DataBookNew.MainChapter;
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
private TextView textView_book , next_page;

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
        img_book.setImageResource(getIntent().getIntExtra("img_book" , 0));
        textView_nameBook.setText(getIntent().getStringExtra("name"));
        textView_book.setText(getIntent().getStringExtra("name"));
        textView_book.setTextSize(14);
        node.setText(getIntent().getStringExtra("name") +" " +node.getText());
        node.setTextSize(12);
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
                String img = img_book.getDrawable().toString();
                Intent intent = new Intent(View_Readbook.this , Main_Chapter.class);
                intent.putExtra("object" , name);
                intent.putExtra("object_book" , nameBook);
                intent.putExtra("img" ,img);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String book = textView_nameBook.getText().toString().trim();
                Intent intent = new Intent(View_Readbook.this , MainChapter.class);
                intent.putExtra("nameBook", book);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
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
