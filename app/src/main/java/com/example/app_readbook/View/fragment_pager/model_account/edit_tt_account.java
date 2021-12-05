package com.example.app_readbook.View.fragment_pager.model_account;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.app_readbook.Model.User;
import com.example.app_readbook.Model.login;
import com.example.app_readbook.R;
import com.example.app_readbook.Service.ApiInterface;
import com.example.app_readbook.Service.ApiService;
import com.example.app_readbook.Service.RealPathUtil;
import com.example.app_readbook.ViewModel.UpdateMemberViewModel;
import com.example.app_readbook.shareFreferences.DataManager;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class edit_tt_account extends AppCompatActivity {
    private static final int MY_REQUEST_CODE = 10;
    public static final String TAG = edit_tt_account.class.getName();
    private CircleImageView avatar;
    private ImageButton btnAnhDaiDien, btnAnhBia;
    private Button button;
    private LinearLayout layoutEdit, layout_userName, layoutRound, layoutDate;
    private static int SELECT_PHOTO = 100;
    private ImageView IMGPAGE, edit_username, edit_name, edit_password, edit_round, edit_date, edit_email;
    private Uri mUri, mUri1;
    String idmember, username, pass, email, name, ngaysinh, gioitinh, imgAVT, updateanhbia;
    private ProgressDialog progressDialog;
    private TextView txt_username, txt_name, txtPass, txtEmail, txt_date, txtRound;
    private Bitmap bitmap, bitmap1;
    User user;
    UpdateMemberViewModel updateMemberViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chinhsuathongtin);
        iniAnhXa();
        loadUser();
        ActivityCompat.requestPermissions(edit_tt_account.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading....");
        iniUpdateMember();
    }

    private void iniUpdateMember() {
        updateMemberViewModel = new ViewModelProvider(this).get(UpdateMemberViewModel.class);
        updateMemberViewModel.getUpdate().observe(this, new Observer<login>() {
            @Override
            public void onChanged(login login) {
                if (login != null) {
                    DataManager.saveUserName(login.getUser());
                    Toast.makeText(edit_tt_account.this, "Update Thành Công", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                    loadUser();

                } else {
                    Toast.makeText(edit_tt_account.this, "Update Không Thành Công", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }
        });
    }

    private void loadUser() {
        // load user vào các edittext textview image view
        user = DataManager.loadUser();
        imgAVT = user.getImgAvatar();
        updateanhbia = user.getImgBia();
        Picasso.get().load(user.getImgAvatar()).into(avatar);
        Picasso.get().load(user.getImgBia()).into(IMGPAGE);
        txt_name.setText(user.getMemberName());
        txt_date.setText(user.getNgaysinh());
        txtRound.setText(user.getGioitinh());
        txtEmail.setText(user.getEmail());
        txtPass.setText(user.getPassword());
        txt_username.setText(user.getUsername());

    }

    private final ActivityResultLauncher<Intent> mActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            , new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    Log.e(TAG, "onActivityResult");
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent data = result.getData();
                        if (data == null) {
                            return;
                        }
                        Uri uri = data.getData();
                        mUri = uri;
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                            avatar.setImageBitmap(bitmap);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
    private final ActivityResultLauncher<Intent> getmActivityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == Activity.RESULT_OK) {
                Intent data = result.getData();
                if (data == null) {
                    return;
                }
                Uri uri1 = data.getData();
                mUri1 = uri1;
                try {
                    bitmap1 = MediaStore.Images.Media.getBitmap(getContentResolver(), uri1);
                    IMGPAGE.setImageBitmap(bitmap1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGalleryAVT();
                openGalleryAVTPAGE();
            }
        }
    }

    private void openGalleryAVT() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"));
    }

    private void openGalleryAVTPAGE() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        getmActivityResultLauncher.launch(Intent.createChooser(intent, "Select Picture"));
    }

    private void iniAnhXa() {
        //ánh xạ các view
        txt_username = findViewById(R.id.userNguoiDung);
        txt_name = findViewById(R.id.tenNguoiDung);
        txtRound = findViewById(R.id.gioitinh);
        txt_date = findViewById(R.id.ngaysinh);
        txtEmail = findViewById(R.id.emailNguoidung);
        txtPass = findViewById(R.id.password_nguoidung);
        avatar = findViewById(R.id.anhdaidien);
        IMGPAGE = findViewById(R.id.anhbia);
        button = findViewById(R.id.btn_chinhsuathongtinh);
        btnAnhDaiDien = findViewById(R.id.picture_anhdaidien);
        btnAnhBia = findViewById(R.id.picture_anhbia);
        edit_username = findViewById(R.id.edit_userNguoiDung);
        edit_name = findViewById(R.id.edit_tenNguoiDung);
        edit_password = findViewById(R.id.edit_password);
        edit_round = findViewById(R.id.edit_gioitinh);
        edit_date = findViewById(R.id.edit_ngaysinh);
        edit_email = findViewById(R.id.edit_emailNguoidung);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callAPIUPDATE();

            }
        });
        edit_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogUsername(Gravity.CENTER);
            }
        });
        edit_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogName(Gravity.CENTER);
            }
        });
        edit_round.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogRound(Gravity.CENTER);
            }
        });
        edit_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date();
            }
        });
        btnAnhDaiDien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGalleryAVT();
            }
        });
        btnAnhBia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGalleryAVTPAGE();
            }
        });
        edit_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPass(Gravity.CENTER);
            }
        });
        edit_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogEmail(Gravity.CENTER);
            }
        });

    }

    private void callAPIUPDATE() {
        progressDialog.show();
        String strRealPath = RealPathUtil.getRealPath(edit_tt_account.this, mUri);
        String strRealPath1 = RealPathUtil.getRealPath(edit_tt_account.this, mUri1);
        assert strRealPath != null;
        File file = new File(strRealPath);
        assert strRealPath1 != null;
        File file1 = new File(strRealPath1);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        RequestBody requestBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), file1);
        MultipartBody.Part body = MultipartBody.Part.createFormData("upload_file", "anhdaidien" + System.currentTimeMillis() + "_" + file.getName(), requestBody);
        MultipartBody.Part body1 = MultipartBody.Part.createFormData("upload_file", "anhbia" + System.currentTimeMillis() + "_" + file1.getName(), requestBody1);
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<String> loginCall = apiInterface.UploadPhoto(body);
        loginCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body() != null) {
                    if (response.isSuccessful()) {
                        String anhdaidien = response.body();
                        ApiInterface apiInterface1 = ApiService.apiInterface();
                        Call<String> callIMG_PAGE = apiInterface1.UploadPhotoIMGPAGE(body1);
                        callIMG_PAGE.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response1) {
                                if (response1.body() != null) {
                                    if (response1.isSuccessful()) {
                                        String anhbia = response1.body();
                                        idmember = DataManager.loadUser().getIdMember();
                                        ngaysinh = txt_date.getText().toString().trim();
                                        gioitinh = txtRound.getText().toString().trim();
                                        username = txt_username.getText().toString().trim();
                                        pass = txtPass.getText().toString().trim();
                                        email = txtEmail.getText().toString().trim();
                                        name = txt_name.getText().toString().trim();
                                        if (anhdaidien.length() > 0 && anhbia.length() > 0) {
                                            if (mUri != null && mUri1 != null)
                                            //khai báo cả biến string và cho chúng bằng các edittext ...
                                            {
                                                progressDialog.dismiss();
                                                updateMemberViewModel.iniUpdateMember(idmember,
                                                        username, name, pass, email
                                                        , gioitinh, ngaysinh
                                                        , ApiService.base_URL + "images/" + anhdaidien,
                                                        ApiService.base_URL + "image_anhbia/" + anhbia);
                                            }
                                        }
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });

                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(edit_tt_account.this, "Lỗi gì đó", Toast.LENGTH_SHORT).show();
                Log.e("AAA", t.getMessage());
                progressDialog.dismiss();
            }
        });

    }

    private void DialogUsername(int gravityUsername) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_edit_username);
        Button btn_edituser = dialog.findViewById(R.id.btn_updateName);
        TextView txt_user = dialog.findViewById(R.id.txt_editUsName);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams dialogAttributes = window.getAttributes();
        dialogAttributes.gravity = gravityUsername;
        window.setAttributes(dialogAttributes);
        txt_user.setText(txt_username.getText().toString());
        btn_edituser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_username.setText(txt_user.getText().toString().trim());
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void DialogName(int gravityName) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_edit_name);
        Button btn_editName = dialog.findViewById(R.id.btn_updateNameName);
        TextView txt_namename = dialog.findViewById(R.id.txt_editname);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams dialogAttributes = window.getAttributes();
        dialogAttributes.gravity = gravityName;
        window.setAttributes(dialogAttributes);
        txt_namename.setText(txt_name.getText().toString());
        btn_editName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_name.setText(txt_namename.getText().toString().trim());
                name = txt_namename.getText().toString().trim();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void DialogEmail(int gravityMain) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_edit_email);
        Button btn_editEmail = dialog.findViewById(R.id.btn_updateEmail);
        TextView txtemail = dialog.findViewById(R.id.txt_editEmail);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams dialogAttributes = window.getAttributes();
        dialogAttributes.gravity = gravityMain;
        window.setAttributes(dialogAttributes);
        txtemail.setText(txtEmail.getText().toString());
        btn_editEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt_name.setText(txtEmail.getText().toString().trim());
                email = txtEmail.getText().toString().trim();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void DialogPass(int gravityPass) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_edit_password);
        Button btn_editpass = dialog.findViewById(R.id.btn_updatePass);
        TextView txt_pass = dialog.findViewById(R.id.txt_editpass);
        TextView txt_pass1 = dialog.findViewById(R.id.txt_editpass1);
        TextView txt_pass2 = dialog.findViewById(R.id.txt_editpass2);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams dialogAttributes = window.getAttributes();
        dialogAttributes.gravity = gravityPass;
        window.setAttributes(dialogAttributes);

        btn_editpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String passCu = txt_pass.getText().toString().trim();
                String pass1 = txt_pass1.getText().toString().trim();
                String pass2 = txt_pass2.getText().toString().trim();
                if (passCu.equals(pass1)) {
                    Toast.makeText(edit_tt_account.this, "Mật khẩu mới không được trùng với mật khẩu cũ", Toast.LENGTH_SHORT).show();
                }
                if (pass1.isEmpty() || pass2.isEmpty()) {
                    Toast.makeText(edit_tt_account.this, "Vui lòng không bỏ trống", Toast.LENGTH_SHORT).show();
                }
                if (pass1.length() < 5) {
                    Toast.makeText(edit_tt_account.this, "Vui lòng nhập mật khẩu lớn hơn 5 ký tự", Toast.LENGTH_SHORT).show();
                }
                if (!pass1.equals(pass2)) {
                    Toast.makeText(edit_tt_account.this, "Mật khẩu nhập lại không chính xác", Toast.LENGTH_SHORT).show();
                } else {
                    txtPass.setText(txt_pass1.getText().toString().trim());
                    pass = txt_pass1.getText().toString().trim();
                    dialog.dismiss();
                }

            }
        });
        dialog.show();
    }

    private void DialogRound(int gravity) {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_round);
        RadioButton rdoMan = dialog.findViewById(R.id.man);
        RadioButton rdoWoman = dialog.findViewById(R.id.woman);
        RadioGroup radioGroup = dialog.findViewById(R.id.radio_group);
        Button btnSubmit = dialog.findViewById(R.id.btn_ok);
        Button btnCancel = dialog.findViewById(R.id.btn_cancel);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams dialogAttributes = window.getAttributes();
        dialogAttributes.gravity = gravity;
        window.setAttributes(dialogAttributes);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.man:
                        break;
                    case R.id.woman:
                        break;
                }
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rdoMan.isChecked()) {
                    txtRound.setText(rdoMan.getText());
                    gioitinh = (String) rdoMan.getText();
                    dialog.dismiss();
                }
                if (rdoWoman.isChecked()) {
                    txtRound.setText(rdoWoman.getText());
                    gioitinh = (String) rdoWoman.getText();
                    dialog.dismiss();
                }
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void Date() {
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);
        DatePickerDialog pickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(year, month, dayOfMonth);
                @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                txt_date.setText(simpleDateFormat.format(calendar.getTime()));
                ngaysinh = simpleDateFormat.format(calendar.getTime());
            }
        }, year, month, day);
        pickerDialog.show();
    }

    @Override
    public void onBackPressed() {
        loadUser();
        super.onBackPressed();
        Log.e("AAA", "onBackPressed");
        finish();
    }

    @Override
    protected void onDestroy() {
        Log.e("AAA", "onDestroy");
        super.onDestroy();
        loadUser();
    }
}




