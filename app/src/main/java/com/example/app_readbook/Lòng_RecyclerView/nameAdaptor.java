package com.example.app_readbook.Lòng_RecyclerView;

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
import com.example.app_readbook.list_book.list_book;

import java.util.List;

public class nameAdaptor extends RecyclerView.Adapter<nameAdaptor.nameViewHolder>{
public List<list_book> list_books;
private Context context;
public void setData(List<list_book> list_book)
{
    this.list_books = list_book;
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
        list_book listBook = list_books.get(position);
        if(listBook == null)
        {
            return;
        }
        holder.name.setText(listBook.getTendanhmuc());
        holder.all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context , Main_ListBook.class);
                Bundle bundle = new Bundle();
                bundle.putString("img_book" , listBook.getIMGsach());
                bundle.putString("name" , listBook.getTenSach());
                bundle.putString("tac_gia" , listBook.getTentacgia());
                bundle.putString("feedback" , listBook.getFeedback());
                bundle.putString("page" , listBook.getSotrang());
                bundle.putString("favorite" , listBook.getFavorite());
                bundle.putString("idDanhmuc" , listBook.getIdDanhmuc());
                bundle.putString("TenDanhMuc" , listBook.getTendanhmuc());
                bundle.putString("TomTatND" , listBook.getTomtatND());
                bundle.putInt("NgayXB" , listBook.getNgayXB());
                bundle.putString("idSach" , listBook.getIdSach());
                intent.putExtra("put_book",bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (list_books != null)
        {
            return list_books.size();
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
