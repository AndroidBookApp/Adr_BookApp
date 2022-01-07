package com.example.app_readbook.View.fragment_pager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.R;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PhotoAdaptor extends RecyclerView.Adapter<PhotoAdaptor.PhotoViewHolder> {
    private List<Sach> mList;
    private Context context;

    @SuppressLint("NotifyDataSetChanged")
    public PhotoAdaptor(List<Sach> mList, Context context) {
        this.mList = mList;
        this.context = context;
        notifyDataSetChanged();
    }
    public class PhotoViewHolder extends RecyclerView.ViewHolder
    {
        private RoundedImageView imageView;
        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView  = itemView.findViewById(R.id.image_view);
        }
    }
    @NonNull
    @Override
    public PhotoAdaptor.PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_photo , parent , false);
        return new PhotoViewHolder(view);

    }
    @Override
    public void onBindViewHolder(@NonNull PhotoAdaptor.PhotoViewHolder holder, int position) {
        Sach pt = mList.get(position);
        if(pt == null)
        {
            return;
        }
        Picasso.get().load(pt.getImgSach()).into(holder.imageView);
    }


    @Override
    public int getItemCount() {
        if(mList != null)
        {
            return mList.size();
        }
        return 0;
    }

}


