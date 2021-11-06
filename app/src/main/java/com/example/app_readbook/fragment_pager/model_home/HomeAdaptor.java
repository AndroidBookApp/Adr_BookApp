package com.example.app_readbook.fragment_pager.model_home;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdaptor extends RecyclerView.Adapter<HomeAdaptor.HomeViewHolder>{

    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout)
        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder{
        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
