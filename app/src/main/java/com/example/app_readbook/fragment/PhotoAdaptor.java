package com.example.app_readbook.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.R;

import java.util.List;

public class PhotoAdaptor extends RecyclerView.Adapter<PhotoAdaptor.PhotoViewHolder> {
    private List<photo> mList;
    public PhotoAdaptor(List<photo> mList)
    {
        this.mList = mList;
    }
    public class PhotoViewHolder extends RecyclerView.ViewHolder
    {
        private ImageView imageView;
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
        photo pt = mList.get(position);
        if(pt == null)
        {
            return;
        }
        holder.imageView.setImageResource(pt.getResoucreID());
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


