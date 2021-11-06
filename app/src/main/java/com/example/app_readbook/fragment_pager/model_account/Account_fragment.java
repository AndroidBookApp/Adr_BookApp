package com.example.app_readbook.fragment_pager.model_account;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.app_readbook.DataLogin.User;
import com.example.app_readbook.R;
import com.example.app_readbook.activity.SignIn;
import com.example.app_readbook.home;


public class Account_fragment extends Fragment {
private TextView textView ;
private View view;
private RecyclerView recyclerView;
private LinearLayout linearLayout , layout_out;
User user;
home mHome;

public static final String url = "http://localhost:8888/demo_app/update_member.php";
    public Account_fragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_account_fragment , container , false);
        linearLayout = view.findViewById(R.id.lay_out_tt);
        mHome = new home();

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity() , edit_tt_account.class);
//                user = new User("" , "" , "");
                Bundle bundle = intent.getBundleExtra("duLieuUser");
                intent.putExtra("objectUser" , bundle);
                getActivity().startActivity(intent);
            }
        });
        layout_out= view.findViewById(R.id.lnl_out);
        layout_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    DiaLogFail(Gravity.CENTER);

            }
        });

        return view;

    }

    private void updateAccount() {
        RequestQueue requestQueue = Volley.newRequestQueue(mHome);

    }

    public void DiaLogFail(int gravity) {
        Dialog dialog = new Dialog(getContext());
        dialog.setCanceledOnTouchOutside(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_out);
        Button button_signUp = dialog.findViewById(R.id.yes);
        Button button_cancel = dialog.findViewById(R.id.no);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);
        button_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getActivity();
                if (context!= null) {
                    Intent intent = new Intent(context, SignIn.class);
                    context.startActivity(intent);
                }
            }
        });
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
