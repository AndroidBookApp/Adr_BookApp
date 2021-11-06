package com.example.app_readbook.activity;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_readbook.R;
import com.example.app_readbook.home;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignIn extends AppCompatActivity {
    EditText txt_username;
    EditText txt_password;
    User mUser;
    String user , pass;
    private ProgressDialog progressDialog;
    private CheckBox mCheckbox;
    private TextView tvForgetPass;
    private static final String url = "http://192.168.1.6:8888/demo_app/loginn.php";
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN , WindowManager.LayoutParams.FLAG_FULLSCREEN);
        txt_username = findViewById(R.id.user);
        txt_password = findViewById(R.id.txt_pass);
        tvForgetPass = findViewById(R.id.tv_forgetPass);
        // đổi kiểu text
        String text = "Quên mật khẩu?";
        SpannableString ss  = new SpannableString(text);
        UnderlineSpan underlineSpan = new UnderlineSpan();
        ss.setSpan(underlineSpan ,0 , 14 , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tvForgetPass.setText(ss);// truyền SpannableString vào textView
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading.....");   // load khi bấm btn_login
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        Intent intent  = getIntent();
        Bundle bundle = intent.getBundleExtra("object");
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.rlt);
        Button signup = (Button) findViewById(R.id.dangky);
        Button signin = (Button) findViewById(R.id.btn_login);
        mCheckbox = findViewById(R.id.checkbox);
//        SharedPreferences preferences = getSharedPreferences("checkbox" , MODE_PRIVATE);
//        String checkbox = preferences.getString("remember" , "");
//        if (checkbox.equals("true"))
//        {
//            Intent intent1 = new Intent(SignIn.this , home.class);
//            startActivity(intent1);
//            Toast.makeText(SignIn.this, "Lưu Thành Công", Toast.LENGTH_SHORT).show();
//
//        }else if(checkbox.equals("false"))
//        {
//            Toast.makeText(SignIn.this , "Bỏ lưu" , Toast.LENGTH_SHORT).show();
//
//        }
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user = txt_username.getText().toString().trim();
                pass = txt_password.getText().toString().trim();
                if (user.equals("")|| pass.equals(""))
                {
                    Toast.makeText(SignIn.this , "Vui Lòng Nhập Đầy Đủ Thông Tin" , Toast.LENGTH_SHORT).show();
                }else
                    CheckLogin(url);
                }

        });
//        mCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(buttonView.isChecked())
//                {
//                    SharedPreferences sharedPreferences = getSharedPreferences("checkbox" , MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("remember" , "true");
//                    editor.apply();
//                    Toast.makeText(SignIn.this, "Lưu Thành Công", Toast.LENGTH_SHORT).show();
//                }else if(!buttonView.isChecked())
//                {
//                    SharedPreferences sharedPreferences = getSharedPreferences("checkbox" , MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putString("remember" , "false");
//                    editor.apply();
//                    Toast.makeText(SignIn.this, "Bỏ Lưu", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this , Register.class);
                startActivity(intent);
            }
        });
    }


    private void CheckLogin(String url) {
        progressDialog.show();
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("status");
                    if(success.equals("success"))
                    {
                        Intent intent = new Intent(SignIn.this , home.class);
                        Toast.makeText(SignIn.this , "Đăng Nhập Thành Công" , Toast.LENGTH_LONG).show();
                        startActivity(intent);
                        progressDialog.dismiss();

                    }
                    else{
                        progressDialog.dismiss();
                        Toast.makeText(SignIn.this , "Lỗi Đăng Nhập" , Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(SignIn.this , "Lỗi" +error.toString() , Toast.LENGTH_LONG).show();
            }
        }){
            @NonNull
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> params = new HashMap<>();
                mUser = new User(null , null , txt_username.getText().toString().trim() , txt_password.getText().toString().trim() , null , null , null);
                params.put("Username" , txt_username.getText().toString().trim());
                params.put("Pass" , txt_password.getText().toString().trim());
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }
    public void DiaLogFail(int gravity)
    {
        Dialog dialog = new Dialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_error);
        Button button_signUp = dialog.findViewById(R.id.signUp);
        Button button_cancel = dialog.findViewById(R.id.login);
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
        button_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignIn.this , Register.class);
                startActivity(intent);
            }
        });
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}