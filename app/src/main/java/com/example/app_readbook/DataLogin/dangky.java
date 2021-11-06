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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.app_readbook.R;
import com.example.app_readbook.onboarding.OnboardingMain;

import java.util.regex.Pattern;

public class dangky  extends AppCompatActivity {
    private RadioButton rdo_check;
    private Button btn_register , login;
    private EditText email , username , pass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.dangky);
        rdo_check = findViewById(R.id.radio_btn);
        btn_register = findViewById(R.id.btn_dangky);
        login = findViewById(R.id.dangNhap);
        email= findViewById(R.id.email);
        username= findViewById(R.id.user);
        pass = findViewById(R.id.pass);
        statusbar();
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               User user = new User("", "","");
                String mail = email.getText().toString().trim() ;
                String mUser = username.getText().toString().trim();
                String password = pass.getText().toString().trim();
                if(mail.equals("") || mUser.equals("") || password.equals(""))
                {
                    Toast.makeText(dangky.this , "Vui lòng nhập đầy đủ thông tin" , Toast.LENGTH_SHORT).show();
                } else if (password.length() >=6 && isValidEmailId(mail))
                {
                    DialogSuccess(Gravity.CENTER);
                }
                else
                {
                    Toast.makeText(dangky.this,"valid email and pass address",Toast.LENGTH_SHORT).show();
                }
            }

        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dangky.this , login.class);
                startActivity(intent);
            }
        });
    }
    private boolean isValidEmailId(String emaill){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(emaill).matches();
    }
private void statusbar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }
    getWindow().setStatusBarColor(ContextCompat.getColor(dangky.this, R.color.background_color));
}
public void DialogSuccess(int gravity)
{
    Dialog dialog = new Dialog(this);
    dialog.setCanceledOnTouchOutside(false);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(R.layout.dialog_success);
    Button login = dialog.findViewById(R.id.signIn);
    Button cancel = dialog.findViewById(R.id.cancel);
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
    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String user = username.getText().toString().trim();
            String password = pass.getText().toString().trim();
            String mEmail = email.getText().toString().trim();
                Toast.makeText(dangky.this , "Đăng ký thành công" , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(dangky.this , login.class);
                Bundle bundle = new Bundle();
            bundle.putString("username" ,user );
            bundle.putString("pass" ,password );
            bundle.putString("email" , mEmail);
            intent.putExtra("objectUser" ,bundle);
                startActivity(intent);
        }
    });
    cancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog.dismiss();
            Intent intent = new Intent(dangky.this , OnboardingMain.class);
            startActivity(intent);
        }
    });
    dialog.show();
}
}
