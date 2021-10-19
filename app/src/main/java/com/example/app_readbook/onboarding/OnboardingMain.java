package com.example.app_readbook.onboarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.app_readbook.R;
import com.example.app_readbook.onboarding.viewpager_onboarding;

import me.relex.circleindicator.CircleIndicator;

public class OnboardingMain extends AppCompatActivity {
private Button button;
private ViewPager viewPager;
private CircleIndicator circleIndicator;
private RelativeLayout relativeLayout1;
    private RelativeLayout relativeLayout2;
private LinearLayout linearLayout;
private com.example.app_readbook.onboarding.viewpager_onboarding viewpager_onboarding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();
        viewpager_onboarding = new viewpager_onboarding(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        relativeLayout1 = findViewById(R.id.background_1);
        relativeLayout2 = findViewById(R.id.background_2);
        linearLayout = findViewById(R.id.logo);
        viewPager.setAdapter(viewpager_onboarding);
        circleIndicator.setViewPager(viewPager);
        circleIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position == 2)
                {
                    button.setVisibility(View.GONE);
                    relativeLayout1.setVisibility(View.GONE);
                    relativeLayout2.setVisibility(View.GONE);
                    linearLayout.setVisibility(View.GONE);

                }
                else
                {
                    button.setVisibility(View.VISIBLE);
                    relativeLayout1.setVisibility(View.VISIBLE);
                    relativeLayout2.setVisibility(View.VISIBLE);
                    linearLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
    private void initUI()
    {
        button = findViewById(R.id.btn_skip);
        viewPager = findViewById(R.id.view_pager);
        circleIndicator = findViewById(R.id.circle);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager.getCurrentItem()< 2)
                {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                }
            }
        });
    }
}