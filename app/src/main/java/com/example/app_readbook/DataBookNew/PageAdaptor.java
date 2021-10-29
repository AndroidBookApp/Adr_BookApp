package com.example.app_readbook.DataBookNew;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class PageAdaptor extends FragmentStatePagerAdapter{
    private List<PageModel> modelList;
    public PageAdaptor(@NonNull FragmentManager fm, int behavior , List<PageModel> list) {

        super(fm, behavior);
        this.modelList = list;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(modelList == null && modelList.isEmpty())
        {
            return null;
        }
        PageModel pageModel = modelList.get(position);
        bookFragment bookFragment = new bookFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("page_page",pageModel.getContent());
        bookFragment.setArguments(bundle);
        return bookFragment;
    }

    @Override
    public int getCount() {
        if (modelList != null)
        {
            return modelList.size();
        }
        return 0;
    }
}

