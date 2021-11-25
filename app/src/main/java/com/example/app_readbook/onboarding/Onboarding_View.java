package com.example.app_readbook.onboarding;

import static com.example.app_readbook.onboarding.OnboardingMain.SAVE_OPEN_APP;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.app_readbook.ApiLoginOrRegister.dangnhap;
import com.example.app_readbook.R;
import com.example.app_readbook.shareFreferences.MySharePreferences;

public class Onboarding_View extends AppCompatActivity {
TextView textView;
LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding_view);
        textView = findViewById(R.id.tv_name);
        lottieAnimationView = findViewById(R.id.imageView);
        final MySharePreferences mySharePreferences = new MySharePreferences(this);
        lottieAnimationView.animate().scaleX(50).scaleY(50).setDuration(3000).setStartDelay(2500);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mySharePreferences.saveOpenApp(SAVE_OPEN_APP)) {
                    startActivity(dangnhap.class);
                } else{
                    startActivity(OnboardingMain.class);
                    mySharePreferences.putBooleanValue(SAVE_OPEN_APP , true);
                }

            }
        } , 3000);
    }
    private void startActivity(Class<?> cls)
    {
        Intent intent = new Intent(this,cls);
        startActivity(intent);
        finish();
    }
}