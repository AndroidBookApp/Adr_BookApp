package com.example.app_readbook.View.fragment_pager.model_search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.R;
import com.example.app_readbook.View.fragment_pager.model_account.IClickSearch;
import com.example.app_readbook.home;

import java.util.ArrayList;
import java.util.List;

public class HistoryAdaptor extends RecyclerView.Adapter<HistoryAdaptor.HistoryViewHolder>{
    private List<history> mList;
    private IClickSearch iClicksearch;
    private Context context;

    public void setData(List<history> mList ) {
        this.mList = mList;
        notifyDataSetChanged();
    }
    public HistoryAdaptor(home home) {
        this.context = context;

    }

    @NonNull
    @Override
    public HistoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_history , parent , false);
        return new HistoryViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull HistoryViewHolder holder, int position) {
        history history = mList.get(position);
        if(history == null)
        {
            return;
        }
        holder.img_time.setImageResource(history.getImg_time());
        holder.txt_nameBook.setText(history.getBook());
        holder.imgClear.setImageResource(history.getImg_clear());
        holder.click_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.imgClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        if(mList != null)
        {
            return mList.size();
        }
        return 0;
    }
public void historyList(ArrayList<history> filterList)
{
    mList = filterList;
    notifyDataSetChanged();
}
    public class HistoryViewHolder extends RecyclerView.ViewHolder {
        private final ImageView img_time;
        private final ImageView imgClear;
        private final TextView txt_nameBook;
        private RelativeLayout click_history;
        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            img_time  = itemView.findViewById(R.id.image_time);
            imgClear = itemView.findViewById(R.id.img_clear);
            txt_nameBook = itemView.findViewById(R.id.tv_namebook);
            click_history = itemView.findViewById(R.id.rlt_history);
        }
    }
}
