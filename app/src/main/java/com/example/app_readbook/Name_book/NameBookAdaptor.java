package com.example.app_readbook.Name_book;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.Name_book.book.BookAdaptor;
import com.example.app_readbook.Name_book.book.book;
import com.example.app_readbook.R;
import com.example.app_readbook.home;
import com.example.app_readbook.list_book.Main_ListBook;
import com.example.app_readbook.list_comment.View_Readbook;

import java.util.List;

public class NameBookAdaptor extends RecyclerView.Adapter<NameBookAdaptor.NameBookViewHolder> {
    private List<Name> mName;
    private List<book> mBook;
    private Context mcontext;
    private home home;
    private book book;

    public NameBookAdaptor(Context mcontext  ) {

        this.mcontext = mcontext;
    }
    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Name> list)
    {
        this.mName = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public NameBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text , parent , false);
        return new NameBookViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull NameBookViewHolder holder, int position) {
      Name name = mName.get(position);
      if(name == null)
      {
          return;
      }
      home = new home();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mcontext , LinearLayoutManager.HORIZONTAL, false);
      holder.recyclerView.setLayoutManager(linearLayoutManager);
      holder.tvname.setText(name.getName());
      holder.tv_sl.setText(name.getSl());
      holder.tvnext.setText(name.getAll());


      holder.recyclerView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
                Intent intent = new Intent(mcontext , View_Readbook.class);
//                intent.putExtra("tac_gia" ,);
          }
      });
      BookAdaptor bookAdaptor = new BookAdaptor(new IClickItemBook() {
          @Override
          public void ClickItem(book book) {

          }
      } , mcontext);
        bookAdaptor.setData(name.getNameBook());
        holder.recyclerView.setAdapter(bookAdaptor);

        holder.tvnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext , Main_ListBook.class);
                intent.putExtra("object_nameBook" , name.getName());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        if(mName != null)
        {
            return mName.size();
        }
        return 0;
    }

    public static class NameBookViewHolder extends RecyclerView.ViewHolder{
private  RecyclerView recyclerView;
private TextView tvname;
private TextView tv_sl;
private TextView tvnext;
private RelativeLayout relativeLayout;
        public NameBookViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.ryc_view);
            relativeLayout = itemView.findViewById(R.id.layout_text);
            tvname = itemView.findViewById(R.id.tv_1);
            tv_sl = itemView.findViewById(R.id.tv_2);
            tvnext = itemView.findViewById(R.id.tv_next);

        }
    }
}
