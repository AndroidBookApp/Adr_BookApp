package com.example.app_readbook.list_comment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.R;

import java.util.ArrayList;
import java.util.List;

public class View_Readbook extends AppCompatActivity {
RecyclerView recyclerView;
private List<Comment> mlist;
private CommentAdaptor commentAdaptor;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scrollview);
        recyclerView = findViewById(R.id.rcv_reabook);
        mlist = getListComment();
        commentAdaptor = new CommentAdaptor(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this ,RecyclerView.VERTICAL , false);
        recyclerView.setLayoutManager(linearLayoutManager);
        commentAdaptor.setData(getListComment());
        recyclerView.setAdapter(commentAdaptor);
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
