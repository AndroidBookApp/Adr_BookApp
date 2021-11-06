package com.example.app_readbook.list_book;

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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.app_readbook.R;
import com.example.app_readbook.View_ReadBook;

import java.util.ArrayList;
import java.util.List;

public class ListBookAdaptor extends RecyclerView.Adapter<ListBookAdaptor.ListViewHolder> {

  private List<list_book> mList;
  private Context context;

    public ListBookAdaptor( Context context , List<list_book> mList ) {
        this.context = context;
        this.mList = mList;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book , parent , false);
      return new ListViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        list_book listBook = mList.get(position);
        if(mList == null)
        {
            return;
        }
        holder.tv_name.setText(listBook.getTenSach());
        holder.tv_tacgia.setText(listBook.getTentacgia());
        holder.comment.setText(listBook.getFeedback());
        holder.textView.setText(listBook.getLuotxem());
        holder.page.setText(listBook.getSotrang());
        Glide.with(context).
                load(listBook.getIMGsach()).apply(new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(20)))
                .into(holder.mBook);
//        Picasso.get().load(listBook.getIMGsach()).into(holder.mBook);

        holder.mBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , View_ReadBook.class);
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

public void searchBook(ArrayList<list_book> search)
{
    if(search !=null)
    {
        mList = search;
        notifyDataSetChanged();
    }

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
        private TextView tv_name;
        private TextView tv_tacgia;
        private TextView comment;
        private TextView textView;
        private TextView page;
        private ImageView mBook;
        private ImageView mIcon;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.txt_tensach);
            tv_tacgia = itemView.findViewById(R.id.txt_tacgia);
            comment = itemView.findViewById(R.id.comment);
            textView = itemView.findViewById(R.id.ic_view);
            page = itemView.findViewById(R.id.icon_book);
            mBook = itemView.findViewById(R.id.bg_sach);
            mIcon = itemView.findViewById(R.id.icon_favorite);
            mIcon.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    list_book listBook = mList.get(position);
                   if (!mIcon.isClickable())
                   {
                       mIcon.setImageResource(R.drawable.ic_baseline_favorite_24);
                       mIcon.isClickable();


                   }else if(mIcon.isClickable()){
                       mIcon.setImageResource(R.drawable.ic_baseline_favorite_1_24);

                   }
                }

            });


        }

  }

}
