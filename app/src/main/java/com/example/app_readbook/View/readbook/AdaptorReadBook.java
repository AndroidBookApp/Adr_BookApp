package com.example.app_readbook.View.readbook;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.Model.Chuong;
import com.example.app_readbook.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdaptorReadBook extends RecyclerView.Adapter<AdaptorReadBook.ReadBookViewHoled>{
    private List<Chuong> chaptersl;
    @SuppressLint("NotifyDataSetChanged")

    public void setData(List<Chuong> chaptersl)
    {
        this.chaptersl = chaptersl;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ReadBookViewHoled onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reabook , parent , false);
        return new ReadBookViewHoled(mView);
    }


    @Override
    public void onBindViewHolder(@NonNull ReadBookViewHoled holder, int position) {
        Chuong chapter = chaptersl.get(position);
        if(chapter == null)
        {
            return;
        }
        holder.txt_readBook.setText(chapter.getNoidung());
        Picasso.get().load(chapter.getImgSach()).into(holder.img_sach);
    }


    @Override
    public int getItemCount() {
        if (chaptersl!=null)
        {
            return chaptersl.size();
        }
        return 0;
    }

    public class ReadBookViewHoled extends RecyclerView.ViewHolder{
        private TextView txt_readBook;
        private ImageView img_sach;
        public ReadBookViewHoled(@NonNull View itemView) {
            super(itemView);
            txt_readBook = itemView.findViewById(R.id.txt_readBook);
            img_sach = itemView.findViewById(R.id.img_sach);
        }
    }
}
