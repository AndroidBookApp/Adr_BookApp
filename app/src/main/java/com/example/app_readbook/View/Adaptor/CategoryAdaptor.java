package com.example.app_readbook.View.Adaptor;

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
import com.example.app_readbook.shareFreferences.DataManager;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryAdaptor extends RecyclerView.Adapter<CategoryAdaptor.CategoryViewHolder> {
    private List<Sach> saches;
    private Context context;

    @SuppressLint("NotifyDataSetChanged")
    public CategoryAdaptor(List<Sach> saches, Context context) {
        this.saches = saches;
        this.context = context;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Sach sach = saches.get(position);
        if (sach == null) {
            return;
        }
        holder.tv_tacGia.setText(sach.getTensach());
        Glide.with(context).load(sach.getImgSach()).into(holder.img_book);
        holder.img_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface apiInterface = ApiService.apiInterface();
                Call<String> strViewBook = apiInterface.ViewReadBook(sach.getIdSach(), "1");
                strViewBook.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String view = response.body();
                        if(view!=null) {
                            if (view.equals("Success")) {
                                Intent intent = new Intent(context, View_ReadBook.class);
                                DataManager.saveObjectSach(sach);
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        if (saches != null) {
            return saches.size();
        }
        return 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        private RoundedImageView img_book;
        private TextView tv_tacGia;
        private ImageView img_bg;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            img_book = itemView.findViewById(R.id.bg_category);
            tv_tacGia = itemView.findViewById(R.id.title_tac_gia);
            img_bg = itemView.findViewById(R.id.img_bg);
        }
    }
}
