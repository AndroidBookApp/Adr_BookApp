package com.example.app_readbook.list_comment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_readbook.R;

public class Main_NodeReadBook extends AppCompatActivity {

private TextView text_node , text_nameBook;
private ImageView img_book;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_node_read_book);
        text_nameBook = findViewById(R.id.name_book);
        text_node = findViewById(R.id.text_node);
        img_book = findViewById(R.id.img_node);
        Intent intent = getIntent();
        Bundle mBundle = intent.getBundleExtra("object_node");
        if(mBundle!= null)
        {
//            int img = mBundle.getInt("img");
            String name = mBundle.getString("object_book");
            String node = mBundle.getString("object");
//            img_book.setImageResource(img);
            text_nameBook.setText(name);
            text_node.setText(node +" " + text_node.getText());
        }


    }
}