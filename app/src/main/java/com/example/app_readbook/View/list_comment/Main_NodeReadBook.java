package com.example.app_readbook.View.list_comment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.app_readbook.R;
import com.example.app_readbook.View.BroadCastRecivice.NextWorkConnect;

public class Main_NodeReadBook extends AppCompatActivity {
private TextView text_node , text_nameBook;
private ImageView img_book;
NextWorkConnect nextWorkConnect = new NextWorkConnect();
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_node_read_book);
        text_nameBook = findViewById(R.id.name_book);
        text_node = findViewById(R.id.text_node);
        img_book = findViewById(R.id.img_node);
//        listBook = new list_book("" ,text_nameBook.getText().toString().trim(),"" )
        Intent intent = getIntent();
        Bundle mBundle = intent.getBundleExtra("object_node");
        String img = mBundle.getString("image");
        String name = mBundle.getString("object_book");
        String node = mBundle.getString("object");
        text_nameBook.setText(name);
        text_node.setText(text_node.getText()+" " + node);
        Glide.with(this).load(img).into(img_book);



    }
    @Override
    protected void onStart() {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(nextWorkConnect , intentFilter);
        super.onStart();

    }

    @Override
    protected void onStop() {
        unregisterReceiver(nextWorkConnect);
        super.onStop();
    }
}