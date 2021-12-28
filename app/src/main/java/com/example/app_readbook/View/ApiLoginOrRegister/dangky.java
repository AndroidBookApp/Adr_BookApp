package com.example.app_readbook.View.ApiLoginOrRegister;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.app_readbook.Model.User;
import com.example.app_readbook.R;
import com.example.app_readbook.View.BroadCastRecivice.NextWorkConnect;
import com.example.app_readbook.ViewModel.RegisterViewModel;

public class dangky extends AppCompatActivity {
    EditText txt_name;
    EditText txt_pass;
    EditText txt_email;
    EditText txt_pass_1;
    Button register , login , no_register;
    TextView tvFocus;
    private CheckBox check_box;
    String name , email , pass , pass_1 , quyen;
    private ProgressBar progressBar;
    RegisterViewModel registerViewModel;
    ProgressDialog progressDialog;
    User user;
    NextWorkConnect nextWorkConnect = new NextWorkConnect();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky);
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        AnhXa();
        iniViewModel();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(dangky.this , dangnhap.class);
                startActivity(intent);
            }
        });
    }
    private void iniViewModel() {
        registerViewModel.getUser().observe(this, new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user == null)
                {
                    progressDialog.dismiss();
                    Toast.makeText(dangky.this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                }
                else {
                    progressDialog.dismiss();
                    Toast.makeText(dangky.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(dangky.this, dangnhap.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void AnhXa() {
        txt_name = findViewById(R.id.user);
        txt_pass = findViewById(R.id.pass);
        tvFocus = findViewById(R.id.tv_focus);
        txt_pass_1 = findViewById(R.id.pass_1);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading.....");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        String text = "Bạn có đồng ý với điều khoản của AndroidBook không"; // tạo đoạn text
        SpannableString ss = new SpannableString(text); // tạo spannable truyền text vào
        ForegroundColorSpan fcsBlue = new ForegroundColorSpan(Color.BLUE); // tạo màu
        UnderlineSpan line = new UnderlineSpan();//tạo kiểu chữ
        ss.setSpan(line , 33, 44, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);// sét thời điểm bắt đầu và kết lúc của màu
        ss.setSpan(fcsBlue,33 , 44 , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );// sét thời điểm bắt đầu và kết lúc của chữ
        tvFocus.setText(ss); // truyền spannable vào textView;
        progressBar = findViewById(R.id.load);
        txt_email = findViewById(R.id.email);
        register = findViewById(R.id.btn_dangky);
        login = findViewById(R.id.dangNhap);
        no_register = findViewById(R.id.btn_no_dangky);
        check_box = findViewById(R.id.check_box);
        check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (check_box.isChecked())
                {
                    register.setVisibility(View.VISIBLE);
                    no_register.setVisibility(View.GONE);
                    register.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            progressDialog.show();
//                register.setVisibility(View.GONE);
                            name = txt_name.getText().toString().trim();
                            email = txt_email.getText().toString().trim();
                            pass = txt_pass.getText().toString().trim();
                            pass_1 = txt_pass_1.getText().toString().trim();
                            if (!name.isEmpty() && !email.isEmpty() && !pass.isEmpty() && !pass_1.isEmpty()) {
                                if (pass.length() > 6 && pass_1.length() > 6) {
                                    if (pass.equals(pass_1)) {
                                        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                                            // mặc định quyền use = 2
                                            quyen = "2";
                                            registerViewModel.initRegister(name  ,pass, email , quyen);
                                        }
                                        else {
                                            progressDialog.dismiss();
                                            Toast.makeText(dangky.this, "Email không chính xác", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                    else {
                                        progressDialog.dismiss();
                                        Toast.makeText(dangky.this, "Mật khẩu xác nhận không chính xác", Toast.LENGTH_SHORT).show();

                                    }
                                }else {
                                    Toast.makeText(dangky.this, "Mật khẩu phải dài hơn 6 ký tự", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            } else {
                                Toast.makeText(dangky.this, "Vui lòng nhập thông tin chính ", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                        }
                    });
                }
                else  if (!check_box.isChecked()){
                    register.setVisibility(View.GONE);
                    no_register.setVisibility(View.VISIBLE);
                }
            }
        });
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
