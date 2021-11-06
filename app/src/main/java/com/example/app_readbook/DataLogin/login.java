package com.example.app_readbook.DataLogin;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.app_readbook.R;
import com.example.app_readbook.home;

public class login extends AppCompatActivity {
    private EditText txt_name, txt_pass;
    private Button btn_sign_in , btn_register;
    private String username  , password ;
    private CheckBox checkBox;
    private String URL = "https://192.168.1.6/demo_app/login.php";
    //pass : Quynh@123
    //passdb : Quynhquang@2901
     User user ;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.login);
            btn_sign_in = findViewById(R.id.btn_login);
            txt_name = findViewById(R.id.user);
            txt_pass = findViewById(R.id.txt_pass);
            checkBox = findViewById(R.id.checkbox);
            Intent intent = getIntent();
            Bundle bundle = intent.getBundleExtra("objectUser");
            txt_name.setText(bundle.getString("username"));
            txt_pass.setText(bundle.getString("pass"));
            user = new User(txt_name.getText().toString().trim(),txt_pass.getText().toString().trim(), "");
            btn_register = findViewById(R.id.dangky);
            statusbar();
            btn_sign_in.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    username = txt_name.getText().toString().trim();
                    password = txt_pass.getText().toString().trim();
                    if(username.equals("") || password.equals("")) {
                        Toast.makeText(login.this, "Không được bỏ trống tài khoản hoặc mật khẩu", Toast.LENGTH_LONG).show();
                    }
                    else if (username.equals(user.getUsername()) && password.equals(user.getPass()))
                    {
                        Intent intent = new Intent(login.this , home.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("objectUser" , username);
                        bundle.putString("objectPass" , password);
                        intent.putExtra("duLieu" , bundle);
                        Toast.makeText(login.this , "Đăng Nhập Thành Công" , Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                    else if(username.equals(user.getUsername()) && !user.isValidPass())
                    {
                        DiaLogFail(Gravity.CENTER);
                    }
                    else if(username.equals("") && !user.isValidPass())
                    {
                        DiaLogFail(Gravity.CENTER);
                    }
                    else{
                        DiaLogFail(Gravity.CENTER);
                    }

                }
            });
    btn_register.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(login.this , dangky.class);
            startActivity(intent);
        }
    });
                }

private void statusbar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
    getWindow().setStatusBarColor(ContextCompat.getColor(login.this, R.color.background_color));
}
public void DiaLogFail(int gravity)
{
    Dialog dialog = new Dialog(this);
    dialog.setCanceledOnTouchOutside(false);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(R.layout.dialog_error);
    Button button_signUp = dialog.findViewById(R.id.signUp);
    Button button_cancel = dialog.findViewById(R.id.login);
    Window window = dialog.getWindow();
    if(window == null)
    {
        return;
    }
    window.setLayout(WindowManager.LayoutParams.MATCH_PARENT , WindowManager.LayoutParams.WRAP_CONTENT);
    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    WindowManager.LayoutParams windowAttributes = window.getAttributes();
    windowAttributes.gravity = gravity;
    window.setAttributes(windowAttributes);
    button_signUp.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(login.this , dangky.class);
            startActivity(intent);
        }
    });
    button_cancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           dialog.dismiss();
        }
    });
    dialog.show();
}
}


