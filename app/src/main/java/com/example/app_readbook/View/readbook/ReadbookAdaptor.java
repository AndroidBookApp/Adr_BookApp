package com.example.app_readbook.View.readbook;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.app_readbook.Model.Chapter;

import java.util.List;

public class ReadbookAdaptor extends FragmentStatePagerAdapter {
  private List<Chapter> readbookNames ;


    public ReadbookAdaptor(@NonNull FragmentManager fm, int behavior , List<Chapter> nameList) {
        super(fm, behavior);
        this.readbookNames = nameList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(readbookNames == null || readbookNames.isEmpty())
        {
            return null;
        }
        Chapter readbookName = readbookNames.get(position);
        ReadbookFragment readbookFragment = new ReadbookFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("readBook_object", readbookName);
        readbookFragment.setArguments(bundle);

        return readbookFragment;
    }


    @Override
    public int getCount() {
        if (readbookNames != null)
        {
            return  readbookNames.size();
        }
        return 0;
    }
}
