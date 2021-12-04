package com.example.app_readbook;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app_readbook.Model.User;
import com.example.app_readbook.shareFreferences.DataManager;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class home_header extends AppCompatActivity {
    private CircleImageView image_user;
    private TextView textView_user;
    private User user;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_header);
        image_user = findViewById(R.id.img_user);
        textView_user = findViewById(R.id.txt_username);
        user  = DataManager.loadUser();
        Picasso.get().load(user.getImgAvatar()).into(image_user);
        textView_user.setText(user.getMemberName());
    }
}
