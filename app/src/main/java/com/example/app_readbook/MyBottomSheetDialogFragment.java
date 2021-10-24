package com.example.app_readbook;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.model_search.HistoryAdaptor;
import com.example.app_readbook.model_search.IClickHistory;
import com.example.app_readbook.model_search.history;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class MyBottomSheetDialogFragment extends BottomSheetDialogFragment {


private ImageButton img_back;
    private final List<history> mlistHistory;
    private final IClickHistory iClickHistory;


    public MyBottomSheetDialogFragment(List<history> list, IClickHistory iClickHistory) {
        this.mlistHistory = list;
        this.iClickHistory = iClickHistory;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_search , null);
        bottomSheetDialog.setContentView(view);
        RecyclerView rcvData = view.findViewById(R.id.rcv_listHistory);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvData.setLayoutManager(linearLayoutManager);
        initView(view);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();


            }
        });
        HistoryAdaptor historyAdaptor = new HistoryAdaptor(mlistHistory, new IClickHistory() {
            @Override
            public void clickItem(history history) {
                iClickHistory.clickItem(history);

            }
        });

        rcvData.setAdapter(historyAdaptor);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext() , DividerItemDecoration.VERTICAL);
        rcvData.addItemDecoration(itemDecoration);
        return bottomSheetDialog;
    }

    private void initView(View view) {
        img_back = view.findViewById(R.id.img_btn);
    }


}
