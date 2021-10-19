package com.example.app_readbook.listreabook;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class nameAdaptor extends FragmentStateAdapter {
 private List<name> nameList;
    public nameAdaptor(@NonNull FragmentActivity fragmentActivity , List<name> names) {
        super(fragmentActivity);
        this.nameList = names;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       if (nameList == null || nameList.isEmpty())
       {
           return null;
       }
       name name = nameList.get(position);
       nameFragment nameFragment = new nameFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("readbook_object", name);
        nameFragment.setArguments(bundle);
        return nameFragment;

    }


    @Override
    public int getItemCount() {
        if (nameList != null)
        {
            return nameList.size();
        }
        return 0;
    }
}
