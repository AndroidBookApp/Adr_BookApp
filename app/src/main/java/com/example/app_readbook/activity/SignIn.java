package com.example.app_readbook.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_readbook.R;
import com.example.app_readbook.home;
import com.example.app_readbook.model.ApiReponse;
import com.example.app_readbook.retrofit.ApiClient;
import com.example.app_readbook.retrofit.ApiInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignIn extends AppCompatActivity {
    private Button btn_register;
ApiInterface apiInterface;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.user) EditText txt_username;
    @SuppressLint("NonConstantResourceId")
    @BindView(R.id.txt_pass) EditText txt_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);
        apiInterface = ApiClient.getApiclient().create(ApiInterface.class);

    }
    public void btn_login(View view)
    {
        Call<ApiReponse> apiReponseCall = apiInterface.reponseCallLogin(txt_username.getText().toString() , txt_password.getText().toString());
        apiReponseCall.enqueue(new Callback<ApiReponse>() {
            @Override
            public void onResponse(Call<ApiReponse> call, Response<ApiReponse> response) {
                    if (response.body()!=null)
                    {
                        ApiReponse apiReponse = response.body();
                        if(apiReponse.isSuccess())
                        {
                            Toast.makeText(SignIn.this  , "Login successful " , Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SignIn.this , home.class);
                        }
                        else{
                            Toast.makeText(SignIn.this  , "user not found "+apiReponse.getMassage() , Toast.LENGTH_LONG).show();
                        }
                    }
            }

            @Override
            public void onFailure(Call<ApiReponse> call, Throwable t) {
                Toast.makeText(SignIn.this  , "Error, could not connect " , Toast.LENGTH_LONG).show();
            }
        });
    }
    public void btn_signup(View view)
    {
        Intent intent = new Intent(SignIn.this , Register.class);
        startActivity(intent);
    }
}