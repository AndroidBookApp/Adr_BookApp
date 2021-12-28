package com.example.app_readbook.View.fragment_pager.model_account;

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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app_readbook.Model.User;
import com.example.app_readbook.R;
import com.example.app_readbook.View.ApiLoginOrRegister.dangnhap;
import com.example.app_readbook.home;
import com.example.app_readbook.shareFreferences.DataManager;

import de.hdodenhof.circleimageview.CircleImageView;


public class Account_fragment extends Fragment {
    private TextView textView, name_user;
    private View view;
    private RecyclerView recyclerView;
    private ImageView anh_bia;
    private CircleImageView avatar;
    private LinearLayout linearLayout, layout_out;
    private DataManager dataManager;
    private Context context;
    home mHome;
    User user;
    public Account_fragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_account_fragment, container, false);
        linearLayout = view.findViewById(R.id.lay_out_tt);
        avatar = view.findViewById(R.id.avatar);
        anh_bia = view.findViewById(R.id.background_image_account);
        mHome = new home();
        name_user = view.findViewById(R.id.name_username);
        user = DataManager.loadUser();
        Glide.with(this).load(user.getImgAvatar()).into(avatar);
        Glide.with(this).load(user.getImgBia()).into(anh_bia);
        String username = user.getMemberName();
        name_user.setText(username);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), edit_tt_account.class);
                getActivity().startActivity(intent);
            }
        });
        layout_out = view.findViewById(R.id.lnl_out);
        layout_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DiaLogFail(Gravity.CENTER);
            }
        });

        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        user = DataManager.loadUser();
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
                if (context != null) {
                    Intent intent = new Intent(context, dangnhap.class);
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
