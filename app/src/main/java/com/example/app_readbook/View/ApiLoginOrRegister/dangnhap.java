package com.example.app_readbook.View.ApiLoginOrRegister;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.app_readbook.Model.User;
import com.example.app_readbook.Model.login;
import com.example.app_readbook.R;
import com.example.app_readbook.SecondActivity;
import com.example.app_readbook.View.BroadCastRecivice.NextWorkConnect;
import com.example.app_readbook.ViewModel.LoginViewModel;
import com.example.app_readbook.home;
import com.example.app_readbook.shareFreferences.DataManager;
import com.example.app_readbook.shareFreferences.MySharePreferences;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class dangnhap extends AppCompatActivity {
    private static final int RC_SIGN_IN = 123;
    EditText txt_username;
    EditText txt_password;
    String user, pass;
    Button signup, signin;
    private ProgressDialog progressDialog;
    private CheckBox mCheckbox;
    private TextView tvForgetPass;
    DataManager dataManager;
    login login;
    User User;
    LoginViewModel loginViewModel;
    GoogleSignInClient googleSignInClient;
    NextWorkConnect nextWorkConnect = new NextWorkConnect();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        anhxa();
        iniLoginViewModel();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                    // ...
                }
            }
        });
    }

    private void signIn() {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(dangnhap.this, SecondActivity.class);
            startActivity(intent);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e("AAA", e.toString());

        }
    }

    private void iniLoginViewModel() {
        loginViewModel = new ViewModelProvider(dangnhap.this).get(LoginViewModel.class);
        loginViewModel.getLogin().observe(dangnhap.this, new Observer<com.example.app_readbook.Model.login>() {
            @Override
            public void onChanged(com.example.app_readbook.Model.login login) {
                if (!user.isEmpty() || !pass.isEmpty()) {
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
                } else {
                    Toast.makeText(dangnhap.this, "Vui lòng không bỏ trống tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
    }

    private void anhxa() {
        final MySharePreferences mySharePreferences = new MySharePreferences(this);
        txt_username = findViewById(R.id.user);
        txt_password = findViewById(R.id.txt_pass);
        tvForgetPass = findViewById(R.id.tv_forgetPass);
        signup = findViewById(R.id.dangky);
        signin = findViewById(R.id.btn_login);
        mCheckbox = findViewById(R.id.checkbox);
        user = mySharePreferences.saveLoginUser("username");
        pass = mySharePreferences.saveLoginPass("password");
        txt_username.setText(user);
        txt_password.setText(pass);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading.....");   // load khi bấm btn_login
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                user = txt_username.getText().toString().trim();
                pass = txt_password.getText().toString().trim();
                if (mCheckbox.isChecked()) {
                    mySharePreferences.putBooleanValueLogin(pass);
                    mySharePreferences.putValueLoginUser(user);
                }
                iniLogin();

            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dangnhap.this, dangky.class);
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
