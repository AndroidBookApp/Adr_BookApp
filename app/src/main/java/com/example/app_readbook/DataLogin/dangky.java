package com.example.app_readbook.DataLogin;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_readbook.R;

public class dangky  extends AppCompatActivity {
    private RadioButton rdo_check;
    private Button btn_register , login;
    private EditText email , username , pass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky);
        rdo_check = findViewById(R.id.radio_btn);
        btn_register = findViewById(R.id.btn_dangky);
        login = findViewById(R.id.dangNhap);
        email= findViewById(R.id.email);
        username= findViewById(R.id.user);
        pass = findViewById(R.id.pass);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mail = email.getText().toString().trim();

                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                email.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if(mail.matches(emailPattern) && s.length() > 0)
                        {
                            Toast.makeText(dangky.this , "Email khong chinh xac " , Toast.LENGTH_SHORT).show();

                        }else
                        {
                            Toast.makeText(dangky.this , "Email chinh xac" , Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                String user = username.getText().toString().trim();
                String password = pass.getText().toString().trim();
                if(mail.equals("") || user.equals("") || password.equals(""))
                {
                    Toast.makeText(dangky.this , "Vui lòng nhập đầy đủ thông tin" , Toast.LENGTH_SHORT).show();
                }else
                {
                    DialogSuccess();
                }
            }

        });

//        rdo_check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(rdo_check.isChecked())
//                {
//                    btn_register.setEnabled(true);
//                    btn_register.setBackgroundColor(R.drawable.color_btn);
//
//                }
//                else{
//                    btn_register.setEnabled(false);
//
//
//                }
//            }
//        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dangky.this , login.class);
                startActivity(intent);
            }
        });
    }
//    public boolean checkEmail(CharSequence target)
//    {
//        String checkEmail = email.getText().toString().trim();
//        return (!checkEmail.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
//    }
public void DialogSuccess()
{
    Dialog dialog = new Dialog(this);
    dialog.setCanceledOnTouchOutside(false);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(R.layout.dialog_success);
    Button login = dialog.findViewById(R.id.signIn);
    Button cancel = dialog.findViewById(R.id.cancel);
    login.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String user = username.getText().toString().trim();
            String password = pass.getText().toString().trim();
                Toast.makeText(dangky.this , "Đăng ký thành công" , Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(dangky.this , login.class);
                intent.putExtra("username" ,user );
                intent.putExtra("pass" ,password );
                startActivity(intent);
        }
    });
    cancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            dialog.dismiss();
        }
    });
    dialog.show();
}
}
