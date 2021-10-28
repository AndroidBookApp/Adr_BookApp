package com.example.app_readbook.fragment_pager.model_account;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_readbook.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.hdodenhof.circleimageview.CircleImageView;

public class edit_tt_account extends AppCompatActivity {
    private static final int MY_REQUEST_CODE = 10;
    private ImageView add_background;
    private FloatingActionButton fl_add_image_1;
    private FloatingActionButton fl_add_image_2;
    private CircleImageView add_view;
    private Button Click;
    private static int SELECT_PHOTO = 100;
private TextView txt_username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_tt);
        add_background = findViewById(R.id.image_view_add);
        fl_add_image_1 = findViewById(R.id.add_image_bg_1);
        txt_username = findViewById(R.id.txt_username);
        txt_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pushUsername();
            }
        });
        fl_add_image_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Getcontent.launch("Image/*");
            }
        });
    }

    private void pushUsername() {

        String txt_name = txt_username.getText().toString().trim();
        Intent intent = new Intent(edit_tt_account.this , Data_edit_account.class);
        intent.putExtra("key_username" , txt_name );
        startActivityForResult(intent , MY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(MY_REQUEST_CODE == requestCode && resultCode == Activity.RESULT_OK)
        {
            txt_username.setText(data.getStringExtra("key_username"));
        }
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


