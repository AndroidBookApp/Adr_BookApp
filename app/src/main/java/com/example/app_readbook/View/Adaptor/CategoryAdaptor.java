package com.example.app_readbook.View.Adaptor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.CategoryViewHolder> {
    private List<Sach> saches;
    private Context context;

    @SuppressLint("NotifyDataSetChanged")
    public CategoryAdaptor(List<Sach> saches, Context context) {
        this.saches = saches;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Sach sach = saches.get(position);
        if (sach == null) {
            return;
        }
        holder.tv_tacGia.setText(sach.getTacgia());
        Glide.with(context).load(sach.getImgSach()).into(holder.img_book);
    }

    @Override
    public int getItemCount() {
        if (saches != null) {
            return saches.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private RoundedImageView img_book;
        private TextView tv_tacGia;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            img_book = itemView.findViewById(R.id.bg_category);
            tv_tacGia = itemView.findViewById(R.id.title_tac_gia);
        }
    }
}
