package com.example.app_readbook.fragment_pager.model_account;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_readbook.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.hdodenhof.circleimageview.CircleImageView;

public class edit_tt_account extends AppCompatActivity {

    ActivityResultLauncher<Intent> starForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result != null && result.getResultCode() == RESULT_OK) {
                if (result.getData() != null && result.getData().getStringExtra(Data_edit_account.KEY_NAME) != null) {
                    txt_username.setText(result.getData().getStringExtra(Data_edit_account.KEY_NAME));

                }
                if (result.getData().getStringExtra(Data_edit_name.KEY_NAME_USER) != null && result.getData() != null) {
                    txt_name.setText(result.getData().getStringExtra(Data_edit_name.KEY_NAME_USER));
                }
            }

        }
    });
    private ImageView add_background;
    private FloatingActionButton fl_add_image_1;
    private FloatingActionButton fl_add_image_2;
    private CircleImageView add_view;
    private Button btnUpdate;
    private LinearLayout layoutEdit, layout_userName, layoutRound, layoutDate;
    private static int SELECT_PHOTO = 100;
    private TextView txt_username, txt_name, txtPass, txtEmail, txt_date, txtRound;

    private static final String url = "http://192.168.1.6:8888/demo_app/update_member.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chinhsuathongtin);
//        btnUpdate = findViewById(R.id.btn_update);
//        layout_userName = findViewById(R.id.lnl_userName);
//        layoutEdit = findViewById(R.id.lno_edit);
//        txt_name = findViewById(R.id.txt_name);
//        txtEmail = findViewById(R.id.txt_email);
//        txt_date = findViewById(R.id.date);
//        txtPass = findViewById(R.id.txt_password);
//        txtRound = findViewById(R.id.round);
//        layoutRound = findViewById(R.id.lnl_round);
//        layoutDate = findViewById(R.id.lnl_date);
//        Intent intent = getIntent();
//
////        txtEmail.setText(user.getEmail());
////        txt_name.setText(user.getUsername());
////        txtPass.setText(user.getPass());
//        add_background = findViewById(R.id.image_view_add);
//        fl_add_image_1 = findViewById(R.id.add_image_bg_1);
//        txt_username = findViewById(R.id.txt_username);
//        layoutEdit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pushUsername();
//            }
//        });
//        layout_userName.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                pushName();
//            }
//        });
//        fl_add_image_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Getcontent.launch("Image/*");
//            }
//        });
//        layoutDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Date();
//            }
//        });
//        layoutRound.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogRound(Gravity.CENTER);
//            }
//        });
//        btnUpdate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                UpdateMember();
//            }
//        });

    }
}
//    private void DialogRound(int gravity) {
//        Dialog dialog = new Dialog(this);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setCanceledOnTouchOutside(false);
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
//            @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
//            txt_date.setText(simpleDateFormat.format(calendar.getTime()));
//        }
//    } , year , month , day);
//    pickerDialog.show();
//}
//    private void pushName() {
//        String userName = txt_name.getText().toString().trim();
//        Intent intent = new Intent(edit_tt_account.this , Data_edit_name.class);
//        intent.putExtra("name_userName" , userName);
//        starForResult.launch(intent);
//    }
//
//    private void pushUsername() {
//        String txt_name = txt_username.getText().toString().trim();
//        Intent intent = new Intent(edit_tt_account.this , Data_edit_account.class);
//        intent.putExtra("keyUsername" , txt_name);
////        startActivityForResult(intent , MY_REQUEST_CODE);
//        starForResult.launch(intent);
//
//    }
//    ActivityResultLauncher<String> Getcontent = registerForActivityResult(
//            new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
//                @Override
//                public void onActivityResult(Uri result) {
//                    if (result != null) {
//                        add_background.setImageURI(result);
//                    }
//                    Toast.makeText(edit_tt_account.this , "hihhi" , Toast.LENGTH_LONG).show();
//                }
//            });
//}


