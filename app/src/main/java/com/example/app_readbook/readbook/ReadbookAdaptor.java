package com.example.app_readbook.readbook;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class ReadbookAdaptor extends FragmentStatePagerAdapter {
  private List<ReadbookName> readbookNames ;


    public ReadbookAdaptor(@NonNull FragmentManager fm, int behavior , List<ReadbookName> nameList) {
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
        ReadbookName readbookName = readbookNames.get(position);
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
