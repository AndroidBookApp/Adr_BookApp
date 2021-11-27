//package com.example.app_readbook.View.fragment_pager.model_account;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.app.DatePickerDialog;
//import android.app.Dialog;
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.net.Uri;
//import android.os.Bundle;
//import android.provider.MediaStore;
//import android.util.Base64;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.View;
//import android.view.Window;
//import android.view.WindowManager;
//import android.widget.Button;
//import android.widget.DatePicker;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RadioButton;
//import android.widget.RadioGroup;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.activity.result.ActivityResult;
//import androidx.activity.result.ActivityResultCallback;
//import androidx.activity.result.ActivityResultLauncher;
//import androidx.activity.result.contract.ActivityResultContracts;
//import androidx.annotation.NonNull;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.example.app_readbook.Model.User;
//import com.example.app_readbook.Model.login;
//import com.example.app_readbook.R;
//import com.example.app_readbook.Service.ApiInterface;
//import com.example.app_readbook.Service.ApiService;
//import com.example.app_readbook.shareFreferences.DataManager;
//import com.squareup.picasso.Picasso;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class edit_tt_account extends AppCompatActivity {
//    private static final int MY_REQUEST_CODE = 10;
//    public static final String TAG = edit_tt_account.class.getName();
//    private CircleImageView avatar;
//    private ImageButton btnAnhDaiDien , btnAnhBia;
//    private Button button;
//    private LinearLayout layoutEdit, layout_userName, layoutRound, layoutDate;
//    private static int SELECT_PHOTO = 100;
//    private ImageView  anhbia , edit_username , edit_name , edit_password,edit_round , edit_date , edit_email;
//    private Uri mUri;
//    String idmember ,username , pass , email , name , ngaysinh , gioitinh , anhdaidien , updateanhbia;
//    private ProgressDialog progressDialog;
//    private TextView txt_username, txt_name, txtPass, txtEmail, txt_date, txtRound;
//    private Bitmap bitmap;
//    public final int BACK_FRAGMENTS  = 100;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.chinhsuathongtin);
//        iniAnhXa();
//        User user = DataManager.loadUser();
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Loading....");
//        txt_username.setText(user.getUsername());
//        txt_name.setText(user.getMemberName());
//        txt_date.setText(user.getNgaysinh());
//        txtRound.setText(user.getGioitinh());
//        txtEmail.setText(user.getEmail());
//        txtPass.setText(user.getPassword());
//        Picasso.get().load(user.getImgAvatar()).into(avatar);
//        Picasso.get().load(user.getImgBia()).into(anhbia);
//        username = txt_username.getText().toString().trim();
//        pass = txtPass.getText().toString().trim();
//        email = txtEmail.getText().toString().trim();
//        name = txt_name.getText().toString().trim();
//        ngaysinh = txt_date.getText().toString().trim();
//        gioitinh = txtRound.getText().toString().trim();
//        idmember = DataManager.loadUser().getIdMember();
//
//    }
//private final ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
//        , new ActivityResultCallback<ActivityResult>() {
//    @Override
//    public void onActivityResult(ActivityResult result) {
//        Log.e(TAG,"onActivityResult");
//        if(result.getResultCode() == Activity.RESULT_OK) {
//            Intent data = result.getData();
//            if (data == null) {
//                return;
//            }
//            Uri uri = data.getData();
//            mUri = uri;
//            try {
//                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
//                avatar.setImageBitmap(bitmap);
//            } catch (IOException e)
//            {
//                e.printStackTrace();
//            }
//        }
//
//    }
//});
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        if(requestCode == MY_REQUEST_CODE) {
//            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                openGallery();
//            }
//        }
//    }
//    private void openGallery() {
//        Intent intent = new Intent();
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        mActivityResultLauncher.launch(Intent.createChooser(intent , "Select Picture"));
//    }
//    private void iniAnhXa() {
//        txt_username = findViewById(R.id.userNguoiDung);
//        txt_name = findViewById(R.id.tenNguoiDung);
//        txtRound = findViewById(R.id.gioitinh);
//        txt_date = findViewById(R.id.ngaysinh);
//        txtEmail = findViewById(R.id.emailNguoidung);
//        txtPass = findViewById(R.id.password_nguoidung);
//        avatar = findViewById(R.id.anhdaidien);
//        anhbia = findViewById(R.id.anhbia);
//        button = findViewById(R.id.btn_chinhsuathongtinh);
//        btnAnhDaiDien = findViewById(R.id.picture_anhdaidien);
//        btnAnhBia = findViewById(R.id.picture_anhbia);
//        edit_username = findViewById(R.id.edit_userNguoiDung);
//        edit_name = findViewById(R.id.edit_tenNguoiDung);
//        edit_password = findViewById(R.id.edit_password);
//        edit_round = findViewById(R.id.edit_gioitinh);
//        edit_date = findViewById(R.id.edit_ngaysinh);
//        edit_email = findViewById(R.id.edit_emailNguoidung);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mUri !=null) {
//                    callAPIUPDATE();
//                }
//            }
//        });
//        edit_username.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogUsername(Gravity.CENTER);
//            }
//        });
//        edit_name.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogName(Gravity.CENTER);
//            }
//        });
//        edit_round.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogRound(Gravity.CENTER);
//            }
//        });
//        edit_date.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Date();
//            }
//        });
//        btnAnhDaiDien.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openGallery();
//            }
//        });
//        btnAnhBia.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                openGallery();
//            }
//        });
//        edit_password.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogPass(Gravity.CENTER);
//            }
//        });
//        edit_email.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogEmail(Gravity.CENTER);
//            }
//        });
//
//    }
//
//
//    private void callAPIUPDATE() {
//        progressDialog.show();
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG , 75 , byteArrayOutputStream);
//        byte [] imageInbyte = byteArrayOutputStream.toByteArray();
//        byte [] imageAnhBia = byteArrayOutputStream.toByteArray();
//        String encodeImage = android.util.Base64.encodeToString(imageInbyte , Base64.DEFAULT);
//        String encodeImageBia = android.util.Base64.encodeToString(imageAnhBia , Base64.DEFAULT);
//        Toast.makeText(this, encodeImage, Toast.LENGTH_SHORT).show();
//        ApiInterface apiInterface = ApiService.apiInterface();
//        Call<login> loginCall = apiInterface.getUpdate(idmember,encodeImage);
//        loginCall.enqueue(new Callback<login>() {
//            @Override
//            public void onResponse(Call<login> call, Response<login> response) {
//                login login = response.body();
//                if (response.isSuccessful() ) {
//                    if(login!=null) {
//                        if (login.getSuccess().equals("200")) {
//                            DataManager.saveUserName(login.getUser());
//                            DataManager.loadUser();
//                            Toast.makeText(edit_tt_account.this, login.getMessage(), Toast.LENGTH_SHORT).show();
//
//                            progressDialog.dismiss();
//                        } else {
//                            Toast.makeText(edit_tt_account.this, login.getMessage(), Toast.LENGTH_SHORT).show();
//                            progressDialog.dismiss();
//                        }
//                    }
//                }else {
//                    Toast.makeText(edit_tt_account.this,"update không thành công", Toast.LENGTH_SHORT).show();
//                    progressDialog.dismiss();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<login> call, Throwable t) {
//                Toast.makeText(edit_tt_account.this,"Lỗi gì đó", Toast.LENGTH_SHORT).show();
//                progressDialog.dismiss();
//            }
//        });
//    }
//
//    private void DialogUsername(int gravityUsername)
//{
//    Dialog dialog = new Dialog(this);
//    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//    dialog.setCanceledOnTouchOutside(false);
//    dialog.setContentView(R.layout.dialog_edit_username);
//    Button btn_edituser = dialog.findViewById(R.id.btn_updateName);
//    TextView txt_user = dialog.findViewById(R.id.txt_editUsName);
//    Window window = dialog.getWindow();
//    if(window == null)
//    {
//        return;
//    }
//    window.setLayout(WindowManager.LayoutParams.MATCH_PARENT , WindowManager.LayoutParams.WRAP_CONTENT);
//    window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//    WindowManager.LayoutParams dialogAttributes = window.getAttributes();
//    dialogAttributes.gravity = gravityUsername;
//    window.setAttributes(dialogAttributes);
//    txt_user.setText(txt_username.getText().toString());
//    btn_edituser.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            txt_username.setText(txt_user.getText().toString().trim());
//            dialog.dismiss();
//        }
//    });
//    dialog.show();
//}
//    private void DialogName(int gravityName)
//    {
//        Dialog dialog = new Dialog(this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setContentView(R.layout.dialog_edit_name);
//        Button btn_editName = dialog.findViewById(R.id.btn_updateNameName);
//        TextView txt_namename = dialog.findViewById(R.id.txt_editname);
//        Window window = dialog.getWindow();
//        if(window == null)
//        {
//            return;
//        }
//        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT , WindowManager.LayoutParams.WRAP_CONTENT);
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        WindowManager.LayoutParams dialogAttributes = window.getAttributes();
//        dialogAttributes.gravity = gravityName;
//        window.setAttributes(dialogAttributes);
//        txt_namename.setText(txt_name.getText().toString());
//        btn_editName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                txt_name.setText(txt_namename.getText().toString().trim());
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//    }
//    private void DialogEmail(int gravityMain)
//    {
//        Dialog dialog = new Dialog(this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setContentView(R.layout.dialog_edit_email);
//        Button btn_editEmail = dialog.findViewById(R.id.btn_updateEmail);
//        TextView txtemail = dialog.findViewById(R.id.txt_editEmail);
//        Window window = dialog.getWindow();
//        if(window == null)
//        {
//            return;
//        }
//        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT , WindowManager.LayoutParams.WRAP_CONTENT);
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        WindowManager.LayoutParams dialogAttributes = window.getAttributes();
//        dialogAttributes.gravity = gravityMain;
//        window.setAttributes(dialogAttributes);
//        txtemail.setText(txtEmail.getText().toString());
//        btn_editEmail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                txt_name.setText(txtEmail.getText().toString().trim());
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//    }
//    private void DialogPass(int gravityPass)
//    {
//        Dialog dialog = new Dialog(this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCanceledOnTouchOutside(false);
//        dialog.setContentView(R.layout.dialog_edit_password);
//        Button btn_editpass = dialog.findViewById(R.id.btn_updatePass);
//        TextView txt_pass1 = dialog.findViewById(R.id.txt_editpass1);
//        TextView txt_pass2= dialog.findViewById(R.id.txt_editpass2);
//        Window window = dialog.getWindow();
//        if(window == null)
//        {
//            return;
//        }
//        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT , WindowManager.LayoutParams.WRAP_CONTENT);
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        WindowManager.LayoutParams dialogAttributes = window.getAttributes();
//        dialogAttributes.gravity = gravityPass;
//        window.setAttributes(dialogAttributes);
//
//        btn_editpass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String pass1 = txt_pass1.getText().toString().trim();
//                String pass2 =txt_pass2.getText().toString().trim();
//                if (pass1.isEmpty() || pass2.isEmpty())
//                {
//                    Toast.makeText(edit_tt_account.this,"Vui lòng không bỏ trống", Toast.LENGTH_SHORT).show();
//                }if(pass1.length() < 5 )
//                {
//                    Toast.makeText(edit_tt_account.this, "Vui lòng nhập mật khẩu lớn hơn 5 ký tự", Toast.LENGTH_SHORT).show();
//                }
//                if(!pass1.equals(pass2)){
//                    Toast.makeText(edit_tt_account.this, "Mật khẩu nhập lại không chính xác", Toast.LENGTH_SHORT).show();
//                }else {
//                    txtPass.setText(txt_pass1.getText().toString().trim());
//                    dialog.dismiss();
//                }
//
//            }
//        });
//        dialog.show();
//    }
//    private void DialogRound(int gravity) {
//        Dialog dialog = new Dialog(this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
////        dialog.setCanceledOnTouchOutside(false);
//        dialog.setContentView(R.layout.dialog_round);
//        RadioButton rdoMan = dialog.findViewById(R.id.man);
//        RadioButton rdoWoman = dialog.findViewById(R.id.woman);
//        RadioGroup radioGroup = dialog.findViewById(R.id.radio_group);
//        Button btnSubmit = dialog.findViewById(R.id.btn_ok);
//        Button btnCancel = dialog.findViewById(R.id.btn_cancel);
//        Window window = dialog.getWindow();
//        if(window == null)
//        {
//            return;
//        }
//        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT , WindowManager.LayoutParams.WRAP_CONTENT);
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        WindowManager.LayoutParams dialogAttributes = window.getAttributes();
//        dialogAttributes.gravity = gravity;
//        window.setAttributes(dialogAttributes);
//        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId)
//                {
//                    case R.id.man:
//                        break;
//                    case R.id.woman:
//                        break;
//                }
//            }
//        });
//        btnSubmit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(rdoMan.isChecked())
//                {
//                    txtRound.setText(rdoMan.getText());
//                    dialog.dismiss();
//                }
//                if(rdoWoman.isChecked())
//                {
//                    txtRound.setText(rdoWoman.getText());
//                    dialog.dismiss();
//                }
//            }
//        });
//        btnCancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//        dialog.show();
//    }
//
//    public void Date()
//{
//    final Calendar calendar = Calendar.getInstance();
//    int day = calendar.get(Calendar.DATE);
//    int month = calendar.get(Calendar.MONTH);
//    int year = calendar.get(Calendar.YEAR);
//    DatePickerDialog pickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//            calendar.set(year , month , dayOfMonth);
//            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
//            txt_date.setText(simpleDateFormat.format(calendar.getTime()));
//        }
//    } , year , month , day);
//    pickerDialog.show();
//}
//    @Override
//    public void onBackPressed() {
//            DataManager.loadUser();
//            super.onBackPressed();
//            finish();
//    }
//}
//
//
//
//
