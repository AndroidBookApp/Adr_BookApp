package com.example.app_readbook.list_comment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_readbook.R;

public class Main_NodeReadBook extends AppCompatActivity {

private TextView text_node , text_nameBook;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_node_read_book);
        text_nameBook = findViewById(R.id.name_book);
        text_node = findViewById(R.id.text_node);
        text_nameBook.setText(getIntent().getStringExtra("object_book"));
        text_node.setText(getIntent().getStringExtra("object") +" " + text_node.getText());

    }
}