package com.example.app_readbook.View.ApiLoginOrRegister;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.app_readbook.Model.User;
import com.example.app_readbook.Model.login;
import com.example.app_readbook.R;
import com.example.app_readbook.ViewModel.LoginViewModel;
import com.example.app_readbook.home;
import com.example.app_readbook.shareFreferences.DataManager;

public class dangnhap extends AppCompatActivity {
    EditText txt_username;
    EditText txt_password;
    String user , pass;
    Button signup , signin;
    private ProgressDialog progressDialog;
    private CheckBox mCheckbox;
    private TextView tvForgetPass;
    DataManager dataManager;
    login login;
    User User;
    LoginViewModel loginViewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        anhxa();
        iniLoginViewModel();
    }
    private void iniLoginViewModel() {
        loginViewModel = new ViewModelProvider(dangnhap.this).get(LoginViewModel.class);
        loginViewModel.getLogin().observe(dangnhap.this, new Observer<com.example.app_readbook.Model.login>() {
            @Override
            public void onChanged(com.example.app_readbook.Model.login login) {
                if(!user.isEmpty() || !pass.isEmpty()) {
                    if (login == null) {
                        Toast.makeText(dangnhap.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    } else {
                        DataManager.saveUserName(login.getUser());
                        Intent intent = new Intent(dangnhap.this, home.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        Toast.makeText(dangnhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
                else {
                    Toast.makeText(dangnhap.this, "Vui lòng không bỏ trống tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
    }

    private void anhxa() {
        txt_username = findViewById(R.id.user);
        txt_password = findViewById(R.id.txt_pass);
        tvForgetPass = findViewById(R.id.tv_forgetPass);
         signup = findViewById(R.id.dangky);
         signin = findViewById(R.id.btn_login);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading.....");   // load khi bấm btn_login
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mCheckbox = findViewById(R.id.checkbox);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    progressDialog.show();
                    user = txt_username.getText().toString().trim();
                    pass = txt_password.getText().toString().trim();
                    iniLogin();

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dangnhap.this , dangky.class);
                startActivity(intent);
            }
        });
    }

    private void iniLogin() {
        loginViewModel.iniLogin(user, pass);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        DataManager.loadUser();
//        if ((dataManager.isLogin())) {
//            Intent intent = new Intent(dangnhap.this, home.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//        }
    }

}
