package com.example.app_readbook.testAdaptor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.R;
import com.example.app_readbook.list_book.Main_ListBook;

import java.util.List;

public class nameAdaptor extends RecyclerView.Adapter<nameAdaptor.nameViewHolder>{
public List<name_item> name_items;
private Context context;
public void setData(List<name_item> name_items)
{
    this.name_items = name_items;
    notifyDataSetChanged();
}
    @NonNull
    @Override
    public nameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adaptor1 , parent , false);
        return new nameViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull nameViewHolder holder, int position) {
        name_item nameItem = name_items.get(position);
        if(nameItem == null)
        {
            return;
        }
        holder.name.setText(nameItem.getNameBook());
        holder.sl.setText(nameItem.getSl());
        holder.all.setText(nameItem.getAll());
        holder.all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context , Main_ListBook.class);
                intent.putExtra("name_title", nameItem.getNameBook());
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

    public class nameViewHolder extends RecyclerView.ViewHolder{
        private TextView name , sl , all ;
        public nameViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.tv_1Adaptor);
            sl = itemView.findViewById(R.id.tv_2Adaptor);
            all = itemView.findViewById(R.id.tv_nextAdaptor);
        }
    }
}
