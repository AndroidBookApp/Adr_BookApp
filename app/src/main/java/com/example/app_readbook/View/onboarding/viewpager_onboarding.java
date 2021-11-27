package com.example.app_readbook.View.onboarding;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class viewpager_onboarding extends FragmentStatePagerAdapter {


    public viewpager_onboarding(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
       switch (position){
           case 0 :
               return new fragment_onboarding_one();
           case 1 :
               return new fragment_onboarding_two();
           case 2 :
               return new fragment_onboarding_three();
           default:
               return new fragment_onboarding_one();

       }
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return 3;
    }
}
