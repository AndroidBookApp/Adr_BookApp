package com.example.app_readbook.ApiView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_readbook.Model.User;
import com.example.app_readbook.Model.login;
import com.example.app_readbook.R;
import com.example.app_readbook.Service.ApiInterface;
import com.example.app_readbook.Service.ApiService;
import com.example.app_readbook.home;
import com.example.app_readbook.shareFreferences.DataManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        anhxa();

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = txt_username.getText().toString().trim();
                pass = txt_password.getText().toString().trim();
                if (user.length() > 0 && pass.length() > 0)
                {
                    ApiInterface apiInterface = ApiService.apiInterface();
                    Call<login> mUser = apiInterface.getLogin(user,pass);
                    mUser.enqueue(new Callback<login>() {
                        @Override
                        public void onResponse(Call<login> call, Response<login> response) {
                            login logins = response.body();
                                if (response.isSuccessful()) {
                                    if (logins!=null)
                                    {
                                        DataManager.saveUserName(logins.getUser());
                                        Intent intent = new Intent(dangnhap.this, home.class);
                                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                        Toast.makeText(dangnhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                    }

//                                    }
                                }
                        }

                        @Override
                        public void onFailure(Call<login> call, Throwable t) {
                            Toast.makeText(dangnhap.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("AAA" , t.getMessage());
                        }
                    });
                }else {
                    Toast.makeText(dangnhap.this,"Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
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
    }

    @Override
    protected void onStart() {
        super.onStart();
//        if ((dataManager.isLogin()))
//        {
//            Intent intent = new Intent(dangnhap.this, home.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            startActivity(intent);
//        }
    }
}
