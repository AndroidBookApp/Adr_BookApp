package com.example.app_readbook.list_book;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.R;
import com.example.app_readbook.list_comment.View_Readbook;

import java.util.List;

public class ListBookAdaptor extends RecyclerView.Adapter<ListBookAdaptor.ListViewHolder> {

  private List<list_book> mList;
  private Context context;

    public void setData(List<list_book> mList ) {
        this.mList = mList;
        notifyDataSetChanged();
    }
    public ListBookAdaptor( Context context ) {
        this.context = context;

    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book , parent , false);
      return new ListViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        list_book listBook = mList.get(position);
        if(mList == null)
        {
            return;
        }
        holder.mBook.setImageResource(listBook.getResourceId());
        holder.mIcon.setImageResource(listBook.getIcon());
        holder.tv_1.setText(listBook.getTitle());
        holder.tv_2.setText(listBook.getTac_gia());
        holder.tv_3.setText(listBook.getIcon_comment());
        holder.tv_4.setText(listBook.getIcon_view());
        holder.tv_4.setText(listBook.getIcon_book());
        holder.mBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , View_Readbook.class);
                context.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
      if(mList != null)
      {
          return mList.size();
      }
        return 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_1;
        private TextView tv_2;
        private TextView tv_3;
        private TextView tv_4;
        private TextView tv_5;
        private ImageButton mIcon;
        private ImageView mBook;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_1 = itemView.findViewById(R.id.txt_tensach);
            tv_2 = itemView.findViewById(R.id.txt_tacgia);
            tv_3 = itemView.findViewById(R.id.comment);
            tv_4 = itemView.findViewById(R.id.ic_view);
            tv_5 = itemView.findViewById(R.id.icon_book);
            mBook = itemView.findViewById(R.id.bg_sach);
            mIcon = itemView.findViewById(R.id.icon_favorite);
            mIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    list_book listBook = mList.get(position);
                   if (v == null)
                   {
                       mIcon.setImageResource(R.drawable.ic_baseline_favorite_24);
                   }
                   mIcon.setImageResource(R.drawable.ic_baseline_favorite_1_24);


                }

            });


        }

  }

}
