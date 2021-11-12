package com.example.app_readbook.DanhSachBookHome.book;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.R;
import com.example.app_readbook.View_ReadBook;
import com.example.app_readbook.home;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BookAdaptor extends RecyclerView.Adapter<BookAdaptor.BookViewHodel> {
private ArrayList<Sach> sachList;
private Context mContext;

public home home;

    public BookAdaptor(ArrayList<Sach> sachList, Context mContext) {
        this.sachList = sachList;
        this.mContext = mContext;
    }


//
//    public BookAdaptor(Context context , IClickItemBook itemBook) {
//
//        this.mContext  = context;
//        this.iClickItemBook = itemBook;
//    }

    @NonNull
    @Override
    public BookViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listbookmain, parent, false);
        return new BookViewHodel(view );
    }




    @Override
    public void onBindViewHolder(@NonNull BookViewHodel holder, int position) {
        Sach sach = sachList.get(position);
        if(sach == null)
        {
            return;
        }
        Picasso.get().load(sach.getImgSach()).into(holder.imageBook);
        holder.txtTitle.setText(sach.getTensach());
        holder.relativeLayoutBookMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( mContext , View_ReadBook.class);
                intent.putExtra("sach" , sachList.get(holder.getPosition()));
                mContext.startActivity(intent);
            }
        });
        holder.imageBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    @Override
    public int getItemCount() {
        if(sachList != null)
        {
            return sachList.size();
        }
        return 0;
    }

    public class BookViewHodel extends RecyclerView.ViewHolder {
        private ImageView imageBook;
        private TextView txtTitle;
        private RelativeLayout relativeLayoutBookMain;
        private CardView cardView;
        public BookViewHodel(@NonNull View itemView) {
            super(itemView);
            imageBook = itemView.findViewById(R.id.book);
            txtTitle = itemView.findViewById(R.id.text_view);
            cardView = itemView.findViewById(R.id.cardView);
            relativeLayoutBookMain = itemView.findViewById(R.id.rlt_bookMain);
        }
    }

}
