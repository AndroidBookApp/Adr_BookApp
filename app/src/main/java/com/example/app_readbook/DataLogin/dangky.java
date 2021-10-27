package com.example.app_readbook.DataLogin;

import static com.example.app_readbook.R.drawable.color_btn;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_readbook.R;
import com.example.app_readbook.home;

public class dangky  extends AppCompatActivity {
    private RadioButton rdo_check;
    private Button btn_register;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky);
        rdo_check = findViewById(R.id.radio_btn);
        btn_register = findViewById(R.id.btn_dangky);
        btn_register.setEnabled(false);
        rdo_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(rdo_check.isChecked())
                {
                    btn_register.setEnabled(true);
                    btn_register.setBottom(color_btn);

                }
                else{
                    btn_register.setEnabled(false);


                }
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dangky.this , home.class);
                startActivity(intent);
            }
        });
    }

}
