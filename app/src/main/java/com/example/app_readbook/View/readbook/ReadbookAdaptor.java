package com.example.app_readbook.View.readbook;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.app_readbook.Model.Chuong;

import java.util.List;

public class ReadbookAdaptor extends FragmentStatePagerAdapter {
  private List<Chuong> chuongList ;
  private boolean isDark;

    public ReadbookAdaptor(@NonNull FragmentManager fm, int behavior , List<Chuong> nameList ,boolean isDark) {
        super(fm, behavior);
        this.chuongList = nameList;
        this.isDark = isDark;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(chuongList == null || chuongList.isEmpty())
        {
            return null;
        }
        Chuong Chuong = chuongList.get(position);
        ReadbookFragment readbookFragment = new ReadbookFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("readBook_object", Chuong);
        bundle.putBoolean("dark" , isDark);
        readbookFragment.setArguments(bundle);

        return readbookFragment;
    }

    @Override
    public int getCount() {
        if (chuongList != null)
        {
            return  chuongList.size();
        }
        return 0;
    }
}
