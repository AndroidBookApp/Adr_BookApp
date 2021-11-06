package com.example.app_readbook.test_home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.R;
import com.example.app_readbook.list_book.Main_ListBook;

import java.util.List;

public class NameAdaptor extends RecyclerView.Adapter<NameAdaptor.NamViewHolder>{
   private List<name_item> name_items;
   @SuppressLint("NotifyDataSetChanged")
   public void setData(List<name_item> name_items)
   {
       this.name_items = name_items;
       notifyDataSetChanged();
   }
    @NonNull
    @Override
    public NamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adaptor1 , parent , false);
        return new NamViewHolder(mView);
    }


    @Override
    public void onBindViewHolder(@NonNull NamViewHolder holder, int position) {
        name_item nameItem = name_items.get(position);
        if(nameItem == null)
        {
            return;
        }
        holder.textView_danhMuc.setText(nameItem.Tendanhmuc);
        holder.textView_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context , Main_ListBook.class);
                Bundle bundle = new Bundle();
                bundle.putString("TenDanhMuc" , nameItem.getTendanhmuc());
                intent.putExtra("put_book",bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
       if (name_items != null)
       {
           return name_items.size();
       }
        return 0;
    }

    public class NamViewHolder extends RecyclerView.ViewHolder{
        private TextView textView_danhMuc , textView_all;
        public NamViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_danhMuc = itemView.findViewById(R.id.tv_1Adaptor);
            textView_all = itemView.findViewById(R.id.tv_nextAdaptor);
        }
    }
}
