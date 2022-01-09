package com.example.app_readbook.View.onboarding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.app_readbook.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_onboarding_one#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_onboarding_one extends Fragment {
private Button btnstart ;
private View mview;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fragment_onboarding_one() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_onboarding_one.
     */
    // TODO: Rename and change types and number of parameters
    public static fragment_onboarding_one newInstance(String param1, String param2) {
        fragment_onboarding_one fragment = new fragment_onboarding_one();
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

       mview = inflater.inflate(R.layout.fragment_onboarding_one, container , false);

        return mview;
    }

    @Override
    public String toString() {
        return "fragment_onboarding_one{" +
                "btnstart=" + btnstart +
                ", mview=" + mview +
                ", mParam1='" + mParam1 + '\'' +
                ", mParam2='" + mParam2 + '\'' +
                '}';
    }
}