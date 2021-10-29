package com.example.app_readbook.DataLogin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app_readbook.R;
import com.example.app_readbook.home;

public class login extends AppCompatActivity {
    private EditText txt_name, txt_pass;
    private Button btn_sign_in , btn_register;
    private String username  , password ;
   AlertDialog.Builder builder;
    private String URL = "https://192.168.1.6/demo_app/login.php";
    //pass : Quynh@123
    //passdb : Quynhquang@2901

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btn_sign_in = findViewById(R.id.btn_login);
        txt_name = findViewById(R.id.user);
        txt_pass = findViewById(R.id.txt_pass);
        txt_name.setText(getIntent().getStringExtra("username"));
        txt_pass.setText(getIntent().getStringExtra("pass"));
        btn_register = findViewById(R.id.dangky);
        btn_sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = txt_name.getText().toString().trim();
                password = txt_pass.getText().toString().trim();
                if (username.equals("quynh123") && password.equals("123"))
                {
                    Intent intent = new Intent(login.this , home.class);
                    Toast.makeText(login.this , "Đăng Nhập Thành Công" , Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                else if(username.equals("quynh123") && password.equals(""))
                {
                    DiaLog();
                }
                else if(username.equals("") && password.equals("123"))
                {
                    DiaLog();
                }
                else if(username.equals("") || password.equals("")){
                    Toast.makeText(login.this , "Không được bỏ trống tài khoản hoặc mật khẩu" , Toast.LENGTH_LONG).show();
                }
                else{
                    DiaLog();
                }

            }
        });
btn_register.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(login.this , dangky.class);

        startActivity(intent);
    }
});
//        builder = new AlertDialog.Builder(login.this);
//        btn_sign_in.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                username = txt_name.getText().toString().trim();
//                password = txt_pass.getText().toString().trim();
//                if(username.equals("") || password.equals(""))
//                {
//                    builder.setTitle("Something went wrong");
//                    displayAlert("Enter a vaild username and password...");
//                }
//                else
//                {
//                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//                            try {
//                                JSONArray jsonArray = new JSONArray(response);
//                                JSONObject jsonObject = jsonArray.getJSONObject(0);
//                                String code = jsonObject.getString("code");
//                                if(code.equals("failure"))
//                                {
//                                    builder.setTitle("Login Error...");
//                                    displayAlert(jsonObject.getString("message"));
//                                }
//                                else {
//                                    Intent intent = new Intent(login.this , home.class);
//                                    Bundle bundle = new Bundle();
//                                    bundle.putString("ID",jsonObject.getString("ID"));
//                                    bundle.putString("email",jsonObject.getString("email"));
//                                    intent.putExtras(bundle);
//                                    startActivity(intent);
//                                }
//                            }catch (Exception ex){
//                              ex.printStackTrace();
//                            }
//                        }
//                    }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            Toast.makeText(login.this , "Error" , Toast.LENGTH_LONG).show();
//                            error.printStackTrace();
//                        }
//
//                })
//                    {
//
//                        @Nullable
//                        @Override
//                        protected Map<String, String> getParams() throws AuthFailureError {
//                            Map<String , String> stringStringMap = new HashMap<String, String>();
//                            stringStringMap.put("username" ,username);
//                            stringStringMap.put("password",password);
//                            return stringStringMap;
//                        }
//                    };
//
//                }
//            }
//        });

            }
//
//            public void displayAlert(String message) {
//                builder.setMessage(message);
//                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        txt_name.setText("");
//                        txt_pass.setText("");
//                    }
//                });
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
//            }
public void DiaLog()
{

    Dialog dialog = new Dialog(this);
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
    dialog.setContentView(R.layout.dialog_error);
    dialog.setCanceledOnTouchOutside(false);
    Button button_signUp = dialog.findViewById(R.id.signUp);
    Button button_cancel = dialog.findViewById(R.id.login);
    button_signUp.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(login.this , dangky.class);
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


