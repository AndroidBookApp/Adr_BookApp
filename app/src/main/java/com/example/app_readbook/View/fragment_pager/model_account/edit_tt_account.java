package com.example.app_readbook.View.fragment_pager.model_account;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
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

import com.example.app_readbook.Class.CustomProgessDialog;
import com.example.app_readbook.Model.User;
import com.example.app_readbook.Model.login;
import com.example.app_readbook.R;
import com.example.app_readbook.ViewModel.Service.ApiInterface;
import com.example.app_readbook.ViewModel.Service.ApiService;
import com.example.app_readbook.ViewModel.Service.RealPathUtil;
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
    private ImageView IMGPAGE, edit_username, edit_name, edit_password, edit_round, edit_date, edit_email;
    private Uri mUri, mUri1;
    String idmember, username, pass, email, name, ngaysinh, gioitinh, imgAVT, updateanhbia;
    String anhdaidien, anhbia;
    private TextView txt_username, txt_name, txtPass, txtEmail, txt_date, txtRound;
    private Bitmap bitmap, bitmap1;
    User user;
    CustomProgessDialog customProgessDialog;
    String strRealPath, strRealPath1;
    File file, file1;
    ApiInterface apiInterface;
    RequestBody requestBody, requestBody1;
    UpdateMemberViewModel updateMemberViewModel;
    MultipartBody.Part body, body1;
    private Toast toast;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chinhsuathongtin);
        customProgessDialog = new CustomProgessDialog(this);
        toast = new Toast(this);
        iniAnhXa();
        loadUser();
        ActivityCompat.requestPermissions(edit_tt_account.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
        iniUpdateMember();
    }

    private void iniUpdateMember() {
        updateMemberViewModel = new ViewModelProvider(this).get(UpdateMemberViewModel.class);
        updateMemberViewModel.getUpdate().observe(this, new Observer<login>() {
            @Override
            public void onChanged(login login) {
                if (login != null) {
                    DataManager.saveUserName(login.getUser());
                    Toast( "Update Th??nh C??ng");
                    customProgessDialog.dismiss();
                    loadUser();

                } else {
                    Toast("Update Kh??ng Th??nh C??ng");
                    customProgessDialog.dismiss();
                }
            }
        });
    }

    private void loadUser() {
        // load user v??o c??c edittext textview image view
        user = DataManager.loadUser();
        imgAVT = user.getImgAvatar();
        updateanhbia = user.getImgBia();
        txt_name.setText(user.getMemberName());
        txt_date.setText(user.getNgaysinh());
        txtRound.setText(user.getGioitinh());
        txtEmail.setText(user.getEmail());
        txtPass.setText(user.getPassword());
        txt_username.setText(user.getUsername());
        if(user.getImgAvatar() !=null || user.getImgBia()!=null)
        {
        if (!user.getImgAvatar().equals("") && !user.getImgBia().equals("")) {
            Log.e("AAA", imgAVT);
            Log.e("AAA", updateanhbia);
            Picasso.get().load(user.getImgAvatar()).into(avatar);
            Picasso.get().load(user.getImgBia()).into(IMGPAGE);
            }
        else if(!user.getImgAvatar().equals(""))
        {
            Picasso.get().load(user.getImgAvatar()).into(avatar);
        }else  if(!user.getImgBia().equals("") )
        {
            Picasso.get().load(user.getImgBia()).into(IMGPAGE);

        }
        }
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
        //??nh x??? c??c view
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
        customProgessDialog.show();
        checkUrl();
        checkString();
//        checkFile();
        requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        requestBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), file1);
        body = MultipartBody.Part.createFormData("upload_file", "anhdaidien" + System.currentTimeMillis() + "_" + file.getName(), requestBody);
        body1 = MultipartBody.Part.createFormData("upload_file", "anhbia" + System.currentTimeMillis() + "_" + file1.getName(), requestBody1);
        idmember = DataManager.loadUser().getIdMember();
        ngaysinh = txt_date.getText().toString().trim();
        gioitinh = txtRound.getText().toString().trim();
        username = txt_username.getText().toString().trim();
        pass = txtPass.getText().toString().trim();
        email = txtEmail.getText().toString().trim();
        name = txt_name.getText().toString().trim();
        apiInterface = ApiService.apiInterface();
        if (mUri1 !=null && mUri!=null) {
            Call<String> loginCall = apiInterface.UploadPhoto(body);
            loginCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    anhdaidien = response.body();
                    if (response.isSuccessful()) {
                        Call<String> callIMG_PAGE = apiInterface.UploadPhotoIMGPAGE(body1);
                        callIMG_PAGE.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response1) {
                                anhbia = response1.body();

                                    //khai b??o c??? bi???n string v?? cho ch??ng b???ng c??c edittext ...
                                    customProgessDialog.dismiss();
                                    updateMemberViewModel.iniUpdateMember(idmember,
                                            username, name, pass, email
                                            , gioitinh, ngaysinh
                                            , ApiService.base_URL + "images/" + anhdaidien,
                                            ApiService.base_URL + "image_anhbia/" + anhbia);
                            }
                            @Override
                            public void onFailure(Call<String> call, Throwable t) {

                            }
                        });

                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(edit_tt_account.this, "L???i g?? ", Toast.LENGTH_SHORT).show();
                    Log.e("AAA", t.getMessage());
                    customProgessDialog.dismiss();
                }
            });

        } // cho ?????y l??n c??? 2
        else if (mUri !=null && mUri1==null) {
            Call<String> loginCall = apiInterface.UploadPhoto(body);
            loginCall.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    anhdaidien = response.body();
                    customProgessDialog.dismiss();
                    updateMemberViewModel.iniUpdateMember(idmember,
                            username, name, pass, email
                            , gioitinh, ngaysinh
                            , ApiService.base_URL + "images/" + anhdaidien,
                            updateanhbia);
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(edit_tt_account.this, "L???i ", Toast.LENGTH_SHORT).show();
                    Log.e("AAA", t.getMessage());
                    customProgessDialog.dismiss();
                }
            });
        } // cho ?????y l??n 1 c??i
        else if ( mUri==null && mUri1!=null) {
            Call<String> callIMG_PAGE = apiInterface.UploadPhotoIMGPAGE(body1);
            callIMG_PAGE.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    customProgessDialog.dismiss();
                    updateMemberViewModel.iniUpdateMember(idmember,
                            username, name, pass, email
                            , gioitinh, ngaysinh
                            , imgAVT,
                            ApiService.base_URL + "image_anhbia/" + anhbia);
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(edit_tt_account.this, " g?? ", Toast.LENGTH_SHORT).show();
                    Log.e("AAA", t.getMessage());
                    customProgessDialog.dismiss();
                }
            });
        }// cho ?????y l??n 1 c??i
        else {
            customProgessDialog.dismiss();
            updateMemberViewModel.iniUpdateMember(idmember,
                    username, name, pass, email
                    , gioitinh, ngaysinh
                    , imgAVT,
                    updateanhbia);
        } // ?????y l??n ???????ng link hi???n t???i

    }
private void checkUrl()
    {
        if (mUri != null && mUri1 != null) {
            strRealPath = RealPathUtil.getRealPath(edit_tt_account.this, mUri);
            strRealPath1 = RealPathUtil.getRealPath(edit_tt_account.this, mUri1);
        } else if (mUri == null && mUri1 != null) {
            strRealPath = null;
            strRealPath1 = RealPathUtil.getRealPath(edit_tt_account.this, mUri1);
        } else if (mUri != null && mUri1 == null) {
            strRealPath = RealPathUtil.getRealPath(edit_tt_account.this, mUri);
            strRealPath1 = null;
        } else {
            strRealPath = null;
            strRealPath1 = null;
        }
    }
    private void checkString (){
        if (strRealPath != null && strRealPath1 != null) {
            file = new File(strRealPath);
            file1 = new File(strRealPath1);
        } else if (strRealPath != null && strRealPath1 == null) {
            file = new File(strRealPath);
            file1 = null;
        } else if (strRealPath == null && strRealPath1 != null) {
            file = new File("null");
            file1 = new File(strRealPath1);
        } else {
            file = new File("null");
            file1 = new File("null");
        }
    }
    private void checkFile()
    {
        if (file != null && file1 != null) {
            requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            requestBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), file1);
        } else if (file == null && file1 != null) {
            requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), "null");
            requestBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), file1);
        } else if (file != null && file1 == null) {
            requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            requestBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), "null");
        } else {
            requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), "null");
            requestBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), "null");
        }
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
                    Toast.makeText(edit_tt_account.this, "M???t kh???u m???i kh??ng ???????c tr??ng v???i m???t kh???u c??", Toast.LENGTH_SHORT).show();
                }
                if (pass1.isEmpty() || pass2.isEmpty()) {
                    Toast.makeText(edit_tt_account.this, "Vui l??ng kh??ng b??? tr???ng", Toast.LENGTH_SHORT).show();
                }
                if (pass1.length() < 5) {
                    Toast.makeText(edit_tt_account.this, "Vui l??ng nh???p m???t kh???u l???n h??n 5 k?? t???", Toast.LENGTH_SHORT).show();
                }
                if (!pass1.equals(pass2)) {
                    Toast.makeText(edit_tt_account.this, "M???t kh???u nh???p l???i kh??ng ch??nh x??c", Toast.LENGTH_SHORT).show();
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




