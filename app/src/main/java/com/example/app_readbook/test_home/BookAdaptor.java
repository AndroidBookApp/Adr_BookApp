package com.example.app_readbook.test_home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.R;
import com.example.app_readbook.View_ReadBook;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookAdaptor extends RecyclerView.Adapter<BookAdaptor.BookViewHodel>{
private List<book_item> bookItemList;
@SuppressLint("NotifyDataSetChanged")
public void setData(List<book_item> list)
{
    this.bookItemList = list;
    notifyDataSetChanged();
}
    @NonNull
    @Override
    public BookViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adaptor2 , parent ,false);
        return new BookViewHodel(view);
    }


    @Override
    public void onBindViewHolder(@NonNull BookViewHodel holder, int position) {
        book_item book_item = bookItemList.get(position);
        if (book_item == null)
        {
            return;
        }
        holder.textView_book.setText(book_item.getTensach());
        Picasso.get().load(book_item.getIMGsach()).into(holder.img_book);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context , View_ReadBook.class);
                Bundle bundle = new Bundle();
                bundle.putString("img_book" , book_item.getIMGsach());
                bundle.putString("name" , book_item.getTensach());
                bundle.putString("tac_gia" , book_item.getTentacgia());
                bundle.putString("feedback" , book_item.getFeedback());
                bundle.putString("page" , book_item.getSotrang());
                bundle.putString("favorite" , book_item.getFavorite());
                bundle.putString("TomTatND" , book_item.getTomtatND());
                bundle.putInt("NgayXB" , book_item.getNgayXB());
                bundle.putString("idSach" , book_item.getIdSach());
                intent.putExtra("put_book",bundle);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (bookItemList != null)
        {
            bookItemList.size();
        }
        return 0;
    }

    public class BookViewHodel extends RecyclerView.ViewHolder{
        private ImageView img_book;
        private TextView textView_book;
        private CardView cardView;
        public BookViewHodel(@NonNull View itemView) {
            super(itemView);
            img_book = itemView.findViewById(R.id.book_adaptor);
            textView_book = itemView.findViewById(R.id.text_viewAdaptor);
            cardView =itemView.findViewById(R.id.cardView);
        }
    }
}
