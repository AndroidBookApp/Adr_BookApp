package com.example.app_readbook.testAdaptor;

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
import com.example.app_readbook.list_comment.View_Readbook;

import java.util.List;

public class bookAdaptor extends RecyclerView.Adapter<bookAdaptor.bookViewHolder>{
   private List<book_item> mBook;
   private Context mContext;
   public void setData(List<book_item> book_itemList)
   {
       this.mBook  = book_itemList;
       notifyDataSetChanged();
   }
    @NonNull
    @Override
    public bookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adaptor2 , parent  , false);
        return new bookViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull bookViewHolder holder, int position) {
     book_item book_item = mBook.get(position);
     if(book_item == null)
     {
         return;
     }
     holder.imageView.setImageResource(book_item.getImg());
     holder.textView.setText(book_item.getName());
     holder.cardView.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Context context = v.getContext();
             Intent intent = new Intent(context , View_Readbook.class);
             Bundle bundle = new Bundle();
             bundle.putSerializable("img" , book_item.getImg());
             bundle.putString("name_book" , book_item.getName());
             intent.putExtra("object_itemBook", bundle);
             context.startActivity(intent);
         }
     });
//     holder.imageView.setOnClickListener(new View.OnClickListener() {
//         @Override
//         public void onClick(View v) {
//             Context context = v.getContext();
//             Intent intent = new Intent(context , View_Readbook.class);
//             Bundle bundle = new Bundle();
//             bundle.putSerializable("img" , book_item.getImg());
//             bundle.putString("name_book" , book_item.getName());
//             intent.putExtra("object_itemBook", bundle);
//             context.startActivity(intent);
//         }
//     });
    }

    @Override
    public int getItemCount() {
       if(mBook != null)
       {
           return mBook.size();
       }
        return 0;
    }

    public class bookViewHolder extends RecyclerView.ViewHolder{
       private ImageView imageView ;
       private TextView textView;
       private CardView cardView;
        public bookViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.book_adaptor);
            textView = itemView.findViewById(R.id.text_viewAdaptor);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
