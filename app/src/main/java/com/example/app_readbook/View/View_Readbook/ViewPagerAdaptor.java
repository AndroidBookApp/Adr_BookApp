package com.example.app_readbook.View.View_Readbook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdaptor extends FragmentStatePagerAdapter {

    public ViewPagerAdaptor(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0 :
                return new MainFragment();
            case 1 :
                return new CommentFragment();
            default:
                return new MainFragment();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position)
        {
            case 0:
                title = "Giới thiệu";
                break;
            case 1:
                title = "Phản hồi";
                break;
        }
        return title;
    }
}
