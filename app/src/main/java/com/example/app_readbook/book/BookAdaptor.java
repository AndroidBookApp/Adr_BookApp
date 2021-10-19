package com.example.app_readbook.book;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.R;

import java.util.List;

public class BookAdaptor extends RecyclerView.Adapter<BookAdaptor.BookViewHodel> {
private List<book> mList;
public void setData(List<book> list){
    this.mList = list;
    notifyDataSetChanged();
}
    @NonNull
    @Override
    public BookViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list , parent, false);
        return new BookViewHodel(view);
    }


    @Override
    public void onBindViewHolder(@NonNull BookViewHodel holder, int position) {
        book book = mList.get(position);
        if(book == null)
        {
            return;
        }
        holder.imageBook.setImageResource(book.getResourceId());
        holder.txtTitle.setText(book.getTitle());
    }

    @Override
    public int getItemCount() {
        if(mList != null)
        {
            return mList.size();
        }
        return 0;
    }

    public class BookViewHodel extends RecyclerView.ViewHolder {
        private ImageView imageBook;
        private TextView txtTitle;

        public BookViewHodel(@NonNull View itemView) {
            super(itemView);
            imageBook = itemView.findViewById(R.id.book);
            txtTitle = itemView.findViewById(R.id.text_view);
        }
    }
}
