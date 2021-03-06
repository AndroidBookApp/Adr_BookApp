package com.example.app_readbook.View.ApiLoginOrRegister;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
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

import com.example.app_readbook.Class.CustomProgessDialog;
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
    String user, pass , quyen = "2";
    Button signup, signin;
    private ProgressDialog progressDialog;
    private CheckBox mCheckbox;
    private TextView tvForgetPass;
    DataManager dataManager;
    login login;
    User User;
    Toast toast;
    CustomProgessDialog customProgessDialog;
    LoginViewModel loginViewModel;
    GoogleSignInClient googleSignInClient;
    NextWorkConnect nextWorkConnect = new NextWorkConnect();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        customProgessDialog = new CustomProgessDialog(this);
        anhxa();
        toast = new Toast(this);
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
                if (!user.isEmpty() || !pass.isEmpty() || !quyen.equals("2")) {
                    if (login == null) {
                        Toast("????ng nh???p kh??ng th??nh c??ng");
                        customProgessDialog.dismiss();
                    } else {
                        DataManager.saveUserName(login.getUser());
                        Intent intent = new Intent(dangnhap.this, home.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        Toast("????ng nh???p th??nh c??ng");
                        customProgessDialog.dismiss();
                    }
                } else {
                    Toast("Vui l??ng kh??ng b??? tr???ng t??i kho???n ho???c m???t kh???u");
                    customProgessDialog.dismiss();
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
        Intent intent = getIntent();
        if(intent.hasExtra("name") && intent.hasExtra("pass"))
        {
            user = intent.getStringExtra("name");
            pass = intent.getStringExtra("pass");
        }else{
            user = mySharePreferences.saveLoginUser("username");
            pass = mySharePreferences.saveLoginPass("password");
        }
        txt_username.setText(user);
        txt_password.setText(pass);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customProgessDialog.show();
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
        loginViewModel.iniLogin(user, pass , quyen);
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
    private void Toast(String text)
    {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_toast , findViewById(R.id.layout_toast));
        TextView textView = view.findViewById(R.id.tv_toast);
        toast.setView(view);
        toast.setGravity(Gravity.BOTTOM , 0 , 0);
        toast.setDuration(Toast.LENGTH_LONG);
        textView.setText(text);
        toast.show();

    }

}
