package com.example.app_readbook.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.app_readbook.R;

public class Onboarding_View extends AppCompatActivity {
TextView textView;
LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_view);
        textView = findViewById(R.id.tv_name);
        lottieAnimationView = findViewById(R.id.imageView);

//        textView.animate().translationY(-1000).setDuration(2700).setStartDelay(0);
//        lottieAnimationView.animate().translationY(-500).setDuration(2700).setStartDelay(0);
        lottieAnimationView.animate().scaleX(50).scaleY(50).setDuration(3000).setStartDelay(2500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Onboarding_View.this , OnboardingMain.class);
                startActivity(intent);
            }
        } , 3000);
    }
}