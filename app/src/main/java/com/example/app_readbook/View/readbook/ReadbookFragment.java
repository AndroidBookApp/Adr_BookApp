package com.example.app_readbook.View.readbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.app_readbook.R;


public class ReadbookFragment extends Fragment {
    public static final String TAG =ReadbookFragment.class.getName() ;


    private TextView textView , tv_page;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReadbookFragment() {

    }


    public static ReadbookFragment newInstance(String param1, String param2) {
        ReadbookFragment fragment = new ReadbookFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
    View mview = inflater.inflate(R.layout.fragment_readbook, container, false);
    textView = mview.findViewById(R.id.tv_read);

    Bundle bundle = getArguments();
    if(bundle != null)
    {
        ReadbookName readbookName = (ReadbookName) bundle.get("readBook_object");
        textView.setText(readbookName.getName());
    }
        return mview;
    }
}