package com.example.app_readbook.fragment_pager.model_account;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_readbook.DataLogin.User;
import com.example.app_readbook.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.hdodenhof.circleimageview.CircleImageView;

public class edit_tt_account extends AppCompatActivity {

    private ImageView add_background;
    private FloatingActionButton fl_add_image_1;
    private FloatingActionButton fl_add_image_2;
    private CircleImageView add_view;
    private Button Click;
    private TextView txtEmail;
    private LinearLayout layoutEdit , layout_userName;
    ActivityResultLauncher<Intent> starForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result!= null && result.getResultCode() == RESULT_OK)
            {
                if(result.getData() != null && result.getData().getStringExtra(Data_edit_account.KEY_NAME) !=null )
                {
                    txt_username.setText(result.getData().getStringExtra(Data_edit_account.KEY_NAME));

                }
                if(result.getData().getStringExtra(Data_edit_name.KEY_NAME_USER) !=null && result.getData() != null)
                {
                    txt_name.setText(result.getData().getStringExtra(Data_edit_name.KEY_NAME_USER));
                }
            }

        }
    });

    private static int SELECT_PHOTO = 100;
private TextView txt_username , txt_name , txtPass;
User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_tt);
        layout_userName = findViewById(R.id.lnl_userName);
        layoutEdit = findViewById(R.id.lno_edit);
        txt_name = findViewById(R.id.txt_name);
        txtEmail = findViewById(R.id.txt_email);
        txtPass = findViewById(R.id.txt_password);
//        Intent intent = getIntent();
//        Bundle bundle = intent.getBundleExtra("duLieu")
        txt_name.setText(getIntent().getStringExtra("username"));
        txtPass.setText(getIntent().getStringExtra("pass"));
        txtEmail.setText(getIntent().getStringExtra("email"));
        user= new User(txt_name.getText().toString().trim() , txtPass.getText().toString().trim() ,txtEmail.getText().toString().trim());
        add_background = findViewById(R.id.image_view_add);
        fl_add_image_1 = findViewById(R.id.add_image_bg_1);
        txt_username = findViewById(R.id.txt_username);
        layoutEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushUsername();
            }
        });
        layout_userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushName();
            }
        });
        fl_add_image_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Getcontent.launch("Image/*");
            }
        });
    }

    private void pushName() {
        String userName = txt_name.getText().toString().trim();
        Intent intent = new Intent(edit_tt_account.this , Data_edit_name.class);
        intent.putExtra("name_userName" , userName);
        starForResult.launch(intent);
    }

    private void pushUsername() {
        String txt_name = txt_username.getText().toString().trim();
        Intent intent = new Intent(edit_tt_account.this , Data_edit_account.class);
        intent.putExtra("keyUsername" , txt_name);
//        startActivityForResult(intent , MY_REQUEST_CODE);
        starForResult.launch(intent);

    }
    ActivityResultLauncher<String> Getcontent = registerForActivityResult(
            new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if (result != null) {
                        add_background.setImageURI(result);
                    }
                    Toast.makeText(edit_tt_account.this , "hihhi" , Toast.LENGTH_LONG).show();
                }
            });
}


