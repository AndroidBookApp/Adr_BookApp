package com.example.app_readbook.View.onboarding;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.app_readbook.View.ApiLoginOrRegister.dangky;
import com.example.app_readbook.View.ApiLoginOrRegister.dangnhap;
import com.example.app_readbook.R;


public class fragment_onboarding_three extends Fragment {
    private View mview ;
private Button btn_dangnhap;
private Button btn_dangky;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_onboarding_three() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mview = inflater.inflate(R.layout.fragment_onboarding_three, container, false);
        btn_dangnhap=mview.findViewById(R.id.btn_login);
        btn_dangky = mview.findViewById(R.id.btn_dki);
        btn_dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity() , dangnhap.class);
                getActivity().startActivity(intent);
            }
        });
        btn_dangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity() , dangky.class);
                getActivity().startActivity(intent);
            }
        });
        return mview ;
    }
}