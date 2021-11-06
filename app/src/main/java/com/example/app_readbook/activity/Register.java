package com.example.app_readbook.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_readbook.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;

public class Register extends Activity {
EditText txt_name;
EditText txt_pass;
EditText txt_email;
Button register , login;
TextView tvFocus;
private ProgressDialog progressDialog;
String name , email , pass;
private ProgressBar progressBar;
private static final String url = "http://192.168.1.6:8888/demo_app/dangky.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky);
        ButterKnife.bind(this);
        txt_name = findViewById(R.id.user);
        txt_pass = findViewById(R.id.pass);
        tvFocus = findViewById(R.id.tv_focus);
        String text = "Bạn có đồng ý với điều khoản của AndroidBook không"; // tạo đoạn text
        SpannableString ss = new SpannableString(text); // tạo spannable truyền text vào
        ForegroundColorSpan fcsBlue = new ForegroundColorSpan(Color.BLUE); // tạo màu
        UnderlineSpan line = new UnderlineSpan();//tạo kiểu chữ
        ss.setSpan(line , 33, 44,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);// sét thời điểm bắt đầu và kết lúc của màu
        ss.setSpan(fcsBlue,33 , 44 , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE );// sét thời điểm bắt đầu và kết lúc của chữ
        tvFocus.setText(ss); // truyền spannable vào textView;
        progressBar = findViewById(R.id.load);
        txt_email = findViewById(R.id.email);
        register = findViewById(R.id.btn_dangky);
        login = findViewById(R.id.dangNhap);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this , SignIn.class);
                startActivity(intent);
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               name = txt_name.getText().toString().trim();
                pass = txt_pass.getText().toString().trim();
                email = txt_email.getText().toString().trim();
                if (name.isEmpty() || pass.isEmpty() || email.isEmpty())
                {
                    Toast.makeText(Register.this , "Vui lòng Nhập đầy đủ thông tin " , Toast.LENGTH_LONG).show();
                }else
                {
                    AddUser(url);
                }
            }
        });
    }
    public void AddUser(String url) {
        register.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                        JSONObject jsonObject = new JSONObject(response.trim());
                        String result = jsonObject.getString("success");
                        if (result.equals("1")) {
//                            SharedPreferences sharedPreferences  = getSharedPreferences("data" , MODE_PRIVATE);
//                            SharedPreferences.Editor editor = sharedPreferences.edit();
//                            editor.putString("Username" , user);
//                            editor.putString("Pass" , txt_pass.getText().toString().trim());
//                            editor.putString("Email" , txt_email.getText().toString().trim());
//                            editor.apply();
                            Toast.makeText(Register.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Register.this , SignIn.class);
                            startActivity(intent);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(Register.this, "Đăng ký không thành công" +e.toString() , Toast.LENGTH_SHORT).show();
                    register.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Register.this, "Có lỗi ! Đăng ký thất bại" +error.toString() , Toast.LENGTH_SHORT).show();
                register.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        }){
            @NonNull
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> map = new HashMap<>();

                map.put("Username" , txt_name.getText().toString().trim());
                map.put("Pass", txt_pass.getText().toString().trim());
                map.put("Email" , txt_email.getText().toString().trim());
                return map;
            }
        };
        requestQueue.add(stringRequest);
        }
    public void DialogSuccess(int gravity) {

        Dialog dialog = new Dialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_success);
        Button login = dialog.findViewById(R.id.signIn);
        Button cancel = dialog.findViewById(R.id.cancel);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        dialog.show();
    }

}