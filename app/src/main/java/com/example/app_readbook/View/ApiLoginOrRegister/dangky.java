package com.example.app_readbook.View.ApiLoginOrRegister;

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
import android.view.Gravity;
import android.view.LayoutInflater;
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

import com.example.app_readbook.Class.CustomProgessDialog;
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
    CustomProgessDialog customProgessDialog;
    Toast toast;
    NextWorkConnect nextWorkConnect = new NextWorkConnect();
    @Override

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky);
        customProgessDialog = new CustomProgessDialog(this);
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        AnhXa();
        toast = new Toast(this);
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
                    customProgessDialog.dismiss();
                    Toast("????ng k?? kh??ng th??nh c??ng");
                }
                else {
                    customProgessDialog.dismiss();
                    Toast("????ng k?? th??nh c??ng");
                    Intent intent = new Intent(dangky.this, dangnhap.class);
                    intent.putExtra("name" , name);
                    intent.putExtra("pass" , pass);
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
        String text = "B???n c?? ?????ng ?? v???i ??i???u kho???n c???a AndroidBook kh??ng"; // t???o ??o???n text
        SpannableString ss = new SpannableString(text); // t???o spannable truy???n text v??o
        ForegroundColorSpan fcsBlue = new ForegroundColorSpan(Color.BLUE); // t???o m??u
        UnderlineSpan line = new UnderlineSpan();//t???o ki???u ch???
        ss.setSpan(line , 33, 44, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);// s??t th???i ??i???m b???t ?????u v?? k???t l??c c???a m??u
        ss.setSpan(fcsBlue,33 , 44 , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );// s??t th???i ??i???m b???t ?????u v?? k???t l??c c???a ch???
        tvFocus.setText(ss); // truy???n spannable v??o textView;
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
                            customProgessDialog.show();
                            name = txt_name.getText().toString().trim();
                            email = txt_email.getText().toString().trim();
                            pass = txt_pass.getText().toString().trim();
                            pass_1 = txt_pass_1.getText().toString().trim();
                            if (!name.isEmpty() && !email.isEmpty() && !pass.isEmpty() && !pass_1.isEmpty()) {
                                if (pass.length() > 6 && pass_1.length() > 6) {
                                    if (pass.equals(pass_1)) {
                                        if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                                            // m???c ?????nh quy???n use = 2
                                            quyen = "2";
                                            registerViewModel.initRegister(name  ,pass, email , quyen);
                                        }
                                        else {
                                            customProgessDialog.dismiss();
                                            Toast.makeText(dangky.this, "Email kh??ng ch??nh x??c", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                    else {
                                        customProgessDialog.dismiss();
                                        Toast.makeText(dangky.this, "M???t kh???u x??c nh???n kh??ng ch??nh x??c", Toast.LENGTH_SHORT).show();

                                    }
                                }else {
                                    Toast.makeText(dangky.this, "M???t kh???u ph???i d??i h??n 6 k?? t???", Toast.LENGTH_SHORT).show();
                                    customProgessDialog.dismiss();
                                }
                            } else {
                                Toast.makeText(dangky.this, "Vui l??ng nh???p th??ng tin ch??nh ", Toast.LENGTH_SHORT).show();
                                customProgessDialog.dismiss();
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
