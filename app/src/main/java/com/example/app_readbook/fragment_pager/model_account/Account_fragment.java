package com.example.app_readbook.fragment_pager.model_account;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.R;


public class Account_fragment extends Fragment {
private TextView textView ;
private View view;
private RecyclerView recyclerView;
private LinearLayout linearLayout;

    public Account_fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_account_fragment , container , false);
        linearLayout = view.findViewById(R.id.lay_out_tt);
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity() , edit_tt_account.class);
                getActivity().startActivity(intent);
            }
        });
        return view;

    }
}
