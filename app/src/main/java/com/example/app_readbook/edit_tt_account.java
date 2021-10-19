package com.example.app_readbook;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import de.hdodenhof.circleimageview.CircleImageView;

public class edit_tt_account extends AppCompatActivity {
    private ImageView add_background;
    private FloatingActionButton fl_add_image_1;
    private FloatingActionButton fl_add_image_2;
    private CircleImageView add_view;
    private Button Click;
    private static int SELECT_PHOTO = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_tt);
        add_background = findViewById(R.id.image_view_add);
        fl_add_image_1 = findViewById(R.id.add_image_bg_1);
        fl_add_image_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Getcontent.launch("Image/*");
            }
        });
    }

    ActivityResultLauncher<String> Getcontent = registerForActivityResult(
            new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
                @Override
                public void onActivityResult(Uri result) {
                    if (result != null) {
                        add_background.setImageURI(result);
                    }
                }
            });
}


