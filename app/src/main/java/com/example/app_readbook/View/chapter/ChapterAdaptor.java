package com.example.app_readbook.View.chapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.Model.Chuong;
import com.example.app_readbook.R;
import com.example.app_readbook.View.readbook.MainReadbook;

import java.util.ArrayList;

public class ChapterAdaptor extends RecyclerView.Adapter<ChapterAdaptor.ChapterViewHolder>{
    private ArrayList<Chuong> chuongList;
    private Context context;

    public ChapterAdaptor(ArrayList<Chuong> chuongList, Context context) {
        this.chuongList = chuongList;
        this.context = context;
    }


    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mview = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chapter , parent , false);
        return new ChapterViewHolder(mview);
    }


    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {
        Chuong namChuong = chuongList.get(position);
        if(namChuong == null) {
            return;
        }
        holder.tv_chaper.setText(namChuong.getTenChuong());
        holder.tv_book.setText(namChuong.getSotrang());
//        holder.tv_max.setText(namChuong.getBook_max());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , MainReadbook.class);
                intent.putExtra("value" , holder.tv_chaper.getText());
                intent.putExtra("page_min" , holder.tv_book.getText());
                intent.putExtra("page_max" , holder.tv_max.getText());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
//            holder.tv_chaper.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context , MainReadbook.class);
//                    context.startActivity(intent);
//                }
//            });

    }

    @Override
    public int getItemCount() {
        if(chuongList != null)
        {
            return chuongList.size();
        }
        return 0;
    }

    public class ChapterViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_chaper , tv_book , tv_max;
        private RelativeLayout relativeLayout;
        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_chaper = itemView.findViewById(R.id.txt_chapter);
            tv_book = itemView.findViewById(R.id.txt_book_min);
            tv_max = itemView.findViewById(R.id.txt_book_max);
            relativeLayout = itemView.findViewById(R.id.rltChapter);

        }
    }
}