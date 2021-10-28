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

import com.example.app_readbook.R;

import java.util.List;

public class BookNewAdaptor extends RecyclerView.Adapter<BookNewAdaptor.BookNewViewHolder>{
   private List<list_bookNew> listNew;
   private Context context;

    public BookNewAdaptor( Context context) {
        this.context = context;
    }

    public void setData(List<list_bookNew> list)
   {
      this.listNew = list;
      notifyDataSetChanged();
   }

    @NonNull
    @Override
    public BookNewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_new , parent , false);
        return  new BookNewViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull BookNewViewHolder holder, int position) {
        list_bookNew list_bookNew = listNew.get(position);
        if(list_bookNew == null)
        {
            return;
        }
        holder.bookNew.setImageResource(list_bookNew.getBoo_new());
        holder.nameBookNew.setText(list_bookNew.getName_new());
        holder.tacGiaNew.setText(list_bookNew.getTacgia_new());
        holder.TrangNew.setText(list_bookNew.getSoTrang());
        holder.icon_favoriteNew.setImageResource(list_bookNew.getIcon_favorite_new());
    }


    @Override
    public int getItemCount() {
        if(listNew != null)
        {
            return listNew.size();
        }
        return 0;
    }

    public class BookNewViewHolder extends RecyclerView.ViewHolder{
       private ImageView bookNew;
       private TextView nameBookNew;
       private TextView tacGiaNew;
       private TextView TrangNew;
       private ImageView icon_favoriteNew;
        @SuppressLint("CutPasteId")
        public BookNewViewHolder(@NonNull View itemView) {
            super(itemView);
            bookNew = itemView.findViewById(R.id.bg_sach_new);
            nameBookNew = itemView.findViewById(R.id.txt_tensach_new);
            tacGiaNew = itemView.findViewById(R.id.txt_tacgia_new);
            TrangNew = itemView.findViewById(R.id.icon_book_new);
            icon_favoriteNew = itemView.findViewById(R.id.icon_favorite_new);
        }
    }
}

