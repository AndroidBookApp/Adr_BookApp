package com.example.app_readbook.listreabook;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.app_readbook.R;
import com.example.app_readbook.readbook.ReadbookName;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link nameFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class nameFragment extends Fragment {
private TextView textView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public nameFragment() {
        // Required empty public constructor
    }


    public static nameFragment newInstance(String param1, String param2) {
        nameFragment fragment = new nameFragment();
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
            name name = (name) bundle.get("readbook_object");
            textView.setText(name.getName());
        }
        return mview;
    }

}