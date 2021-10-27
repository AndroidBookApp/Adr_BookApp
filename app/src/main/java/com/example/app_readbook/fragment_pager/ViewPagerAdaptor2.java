package com.example.app_readbook.fragment_pager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.app_readbook.home;

public class ViewPagerAdaptor2 extends FragmentStateAdapter {
    public ViewPagerAdaptor2(@NonNull FragmentActivity fragmentActivity ) {
        super(fragmentActivity);

    }

    public ViewPagerAdaptor2(home fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
              switch (position)
              {
                  case 0 :
                      return new Home_fragment();
                  case 1 :
                      return new Search_fragment();
                  case 2 :
                      return new Favorite_fragment();
                  case 3 :
                      return new Account_fragment();
                  default:
                      return new Home_fragment();
              }
    }


    @Override
    public int getItemCount() {
        return 4;
    }
}
