package com.example.app_readbook.chapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.R;
import com.example.app_readbook.readbook.MainReadbook;

import java.util.List;

public class ChapterAdaptor extends RecyclerView.Adapter<ChapterAdaptor.ChapterViewHolder>{
   private List<NameChapter> nameChapters;
   private Context context;
public void setData(List<NameChapter>nameChapterList)
{
    this.nameChapters = nameChapterList;
    notifyDataSetChanged();
}
    public ChapterAdaptor(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View mview = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chapter , parent , false);
       return new ChapterViewHolder(mview);
    }


    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {
    NameChapter name = nameChapters.get(position);
            if(nameChapters == null)
            {
                return;
            }
            holder.tv_chaper.setText(name.getChapter());
            holder.tv_book.setText(name.getBook());
            holder.tv_chaper.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context , MainReadbook.class);
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        if(nameChapters != null)
        {
            return nameChapters.size();
        }
        return 0;
    }

    public class ChapterViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_chaper , tv_book;
        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_chaper = itemView.findViewById(R.id.txt_chapter);
            tv_book = itemView.findViewById(R.id.txt_book);

        }
    }
}
