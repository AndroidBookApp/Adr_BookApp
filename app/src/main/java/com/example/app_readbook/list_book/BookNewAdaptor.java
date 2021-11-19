package com.example.app_readbook.list_book;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookNewAdaptor extends RecyclerView.Adapter<BookNewAdaptor.BookNewViewHolder>{
   private ArrayList<Sach> saches;
   private Context context;

    public BookNewAdaptor(Context context, ArrayList<Sach> newList) {
        this.context = context;
        this.saches = newList;
    }


    @NonNull
    @Override
    public BookNewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_new , parent , false);
        return  new BookNewViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull BookNewViewHolder holder, int position) {
        Sach list_bookNew = saches.get(position);
        if(list_bookNew == null)
        {
            return;
        }
        Picasso.get().load(list_bookNew.getImgSach()).into(holder.bookNew);
        holder.nameBookNew.setText(list_bookNew.getTensach());
        holder.tacGiaNew.setText(list_bookNew.getTacgia());
        holder.TrangNew.setText(list_bookNew.getSotrang());
        holder.tomtatND.setText(list_bookNew.getTomtatND());
//        holder.icon_favoriteNew.setImageResource(list_bookNew.getIcon_favorite_new());
    }
    @Override
    public int getItemCount() {
        if(saches != null)
        {
            return saches.size();
        }
        return 0;
    }

    public class BookNewViewHolder extends RecyclerView.ViewHolder{
       private ImageView bookNew;
       private TextView nameBookNew;
       private TextView tacGiaNew;
       private TextView TrangNew , tomtatND;
       private ImageView icon_favoriteNew;
        @SuppressLint("CutPasteId")
        public BookNewViewHolder(@NonNull View itemView) {
            super(itemView);
            bookNew = itemView.findViewById(R.id.bg_sach_new);
            nameBookNew = itemView.findViewById(R.id.txt_tensach_new);
            tacGiaNew = itemView.findViewById(R.id.txt_tacgia_new);
            TrangNew = itemView.findViewById(R.id.icon_book_new);
            icon_favoriteNew = itemView.findViewById(R.id.icon_favorite_new);
            tomtatND = itemView.findViewById(R.id.tomtatNDBookNew);
        }
    }
}

