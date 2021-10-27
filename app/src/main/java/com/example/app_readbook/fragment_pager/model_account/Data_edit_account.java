package com.example.app_readbook.fragment_pager.model_account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_readbook.R;

public class Data_edit_account extends AppCompatActivity {
private EditText edit_user;
private Button btn_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_edit_account);
        edit_user = findViewById(R.id.txt_editUsername);
        edit_user.setText(getIntent().getStringExtra("key_username"));
        btn_back = findViewById(R.id.btn_update);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backActivity();
            }
        });
    }

    private void backActivity() {
        String edit_update = edit_user.getText().toString().trim();
        Intent intent = new Intent();
        intent.putExtra("key_username", edit_update);
        setResult(Activity.RESULT_OK);
        finish();
    }
}