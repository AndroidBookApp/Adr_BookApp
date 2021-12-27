package com.example.app_readbook.View.DanhSachBookHome.book;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.R;
import com.example.app_readbook.View.View_Readbook.View_ReadBook;
import com.example.app_readbook.ViewModel.Service.ApiInterface;
import com.example.app_readbook.ViewModel.Service.ApiService;
import com.example.app_readbook.home;
import com.example.app_readbook.shareFreferences.DataManager;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookAdaptor extends RecyclerView.Adapter<BookAdaptor.BookViewHodel> {
    private ArrayList<Sach> sachList;
    private Context mContext;

    public home home;

    public BookAdaptor(ArrayList<Sach> sachList, Context mContext) {
        this.sachList = sachList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public BookViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listbookmain, parent, false);
        return new BookViewHodel(view);
    }


    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull BookViewHodel holder, int position) {
        Sach sach = sachList.get(position);
        if (sach == null) {
            return;
        }

        Glide.with(mContext).load(sach.getImgSach()).into(holder.imageBook);
        holder.txtTitle.setText(sach.getTensach());
        holder.txtTacgia.setText(sach.getTacgia());
        holder.tomtat.setText(sach.getTomtatND());
        holder.img_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface apiInterface = ApiService.apiInterface();
                Call<String> strViewBook = apiInterface.ViewReadBook(sach.getIdSach(), "1");
                strViewBook.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String view = response.body();
                        if (view.equals("Success")) {
                            Intent intent = new Intent(mContext, View_ReadBook.class);
                            DataManager.saveObjectSach(sach);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
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
        if (sachList != null) {
            return sachList.size();
        }
        return 0;
    }

    public class BookViewHodel extends RecyclerView.ViewHolder {
        private RoundedImageView imageBook;
        private TextView txtTitle, txtTacgia, tomtat;
        private ImageView img_main;

        public BookViewHodel(@NonNull View itemView) {
            super(itemView);
            imageBook = itemView.findViewById(R.id.sach_main);
            txtTitle = itemView.findViewById(R.id.text_view);
            txtTacgia = itemView.findViewById(R.id.text_tacgia);
            img_main = itemView.findViewById(R.id.image_book_main);
            tomtat = itemView.findViewById(R.id.tomtatND);

        }
    }

}
