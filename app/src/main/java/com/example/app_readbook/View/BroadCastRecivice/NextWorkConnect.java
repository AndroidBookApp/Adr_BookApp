package com.example.app_readbook.View.BroadCastRecivice;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.widget.AppCompatButton;

import com.example.app_readbook.R;

public class NextWorkConnect extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(!Common.isConnectedToInternet(context))
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View layout_dialog = LayoutInflater.from(context).inflate(R.layout.item_connected , null);
            builder.setView(layout_dialog);
            AppCompatButton btn_connect = layout_dialog.findViewById(R.id.btn_connect);
            AlertDialog dialog = builder.create();
            dialog.show();
            dialog.getWindow().setGravity(Gravity.CENTER);
            dialog.setCancelable(false);
            btn_connect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    onReceive(context ,intent);
                }
            });
        }
    }
}
