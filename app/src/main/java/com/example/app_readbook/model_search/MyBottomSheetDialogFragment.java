package com.example.app_readbook.model_search;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.List;

public class MyBottomSheetDialogFragment extends BottomSheetDialogFragment {

    private final List<history> mlistHistory;
    private final IClickHistory iClickHistory;
    private ImageButton img_btn ;
    private Context context;
    private BottomNavigationView bottomNavigationView ;
public void BottomSheetDialog(Context context)
{
    this.context = context;
}
    public MyBottomSheetDialogFragment(List<history> list, IClickHistory iClickHistory ) {
        this.mlistHistory = list;
        this.iClickHistory = iClickHistory;

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog bottomSheetDialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_search , null);
        bottomSheetDialog.setContentView(view);
//        View mView = LayoutInflater.from(getContext()).inflate(R.layout.scroll_home , null);
//        bottomNavigationView = mView.findViewById(R.id.btn_navigatione);
        RecyclerView rcvData = view.findViewById(R.id.rcv_listHistory);
        img_btn = view.findViewById(R.id.detail1_toolbar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvData.setLayoutManager(linearLayoutManager);
        img_btn.setOnClickListener(new View.OnClickListener() {
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
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from((View) view.getParent());
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        rcvData.setAdapter(historyAdaptor);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(getContext() , DividerItemDecoration.VERTICAL);
        rcvData.addItemDecoration(itemDecoration);
        return bottomSheetDialog;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.scroll_home , null);



    }

}
