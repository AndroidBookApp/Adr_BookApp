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
import com.example.app_readbook.shareFreferences.DataManager;
import com.example.app_readbook.shareFreferences.MySharePreferences;

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
    MySharePreferences mySharePreferences;
    DataManager dataManager;
    User mUser;
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
//                    ApiInterface apiInterface = ApiService.apiInterface();
//                    Call<List<User>> userList = apiInterface.getUser(user, pass);
//                    userList.enqueue(new Callback<List<User>>() {
//                        @Override
//                        public void onResponse(Call<List<User>> call, Response<List<User>> response) {
//                            ArrayList<User> users = (ArrayList<User>) response.body();
//
//
//                            if(users != null) {
//                                if (users.size() > 0) {
//                                    Intent intent = new Intent(dangnhap.this, home.class);
//                                    startActivity(intent);
////                                    mUser = new User(mUser.getIdMember() ,mUser.getUsername(),mUser.getMemberName(),mUser.getPassword(),mUser.getEmail(),mUser.getGioitinh(),mUser.getNgaysinh(),mUser.getSuccess(),mUser.getMessage());
////                                    DataManager.setObjectUser(mUser);
//                                    Toast.makeText(dangnhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//                        }
//                        @Override
//                        public void onFailure(Call<List<User>> call, Throwable t) {
//                            Toast.makeText(dangnhap.this, "error", Toast.LENGTH_SHORT).show();
//                        }
//                    });
                    ApiInterface apiInterface = ApiService.apiInterface();
                    Call<login> mUser = apiInterface.getLogin(user,pass);
                    mUser.enqueue(new Callback<login>() {
                        @Override
                        public void onResponse(Call<login> call, Response<login> response) {
                            login login = response.body();
                                if (response.isSuccessful()) {
//                                mySharePreferences.putBooleanUser("", true);
//                                this.
                                    Toast.makeText(dangnhap.this, login.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                        }

                        @Override
                        public void onFailure(Call<login> call, Throwable t) {
                            Toast.makeText(dangnhap.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            Log.e("AAA" , t.getMessage());
                        }
                    });
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
}
