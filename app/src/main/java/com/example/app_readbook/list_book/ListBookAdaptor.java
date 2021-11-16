package com.example.app_readbook.list_book;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.Model.User;
import com.example.app_readbook.R;
import com.example.app_readbook.Service.ApiInterface;
import com.example.app_readbook.Service.ApiService;
import com.example.app_readbook.View_ReadBook;
import com.example.app_readbook.shareFreferences.DataManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListBookAdaptor extends RecyclerView.Adapter<ListBookAdaptor.ListViewHolder> {

  private ArrayList<Sach> mSach;
  private Context context;



    public ListBookAdaptor( Context context , ArrayList<Sach> mList ) {
        this.context = context;
        this.mSach = mList;
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
        Sach sach = mSach.get(position);
        if(sach == null)
        {
            return;
        }
        holder.tv_name.setText(sach.getTensach());
        holder.tv_tacgia.setText(sach.getTacgia());
        holder.comment.setText(sach.getFeedback());
        holder.textView.setText(sach.getLuotxem());
        holder.page.setText(sach.getSotrang());
        Glide.with(context).
                load(sach.getImgSach()).apply(new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(20)))
                .into(holder.mBook);
//        Picasso.get().load(listBook.getIMGsach()).into(holder.mBook);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , View_ReadBook.class);
                DataManager.saveSach(mSach);
                DataManager.saveObjectSach(sach);
//                intent.putExtra("sach" , mSach.get(holder.getPosition()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });


        holder.mIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(context, mSach.get(holder.getPosition()).getTensach(), Toast.LENGTH_SHORT).show();
                User user = DataManager.loadUser();
                ApiInterface apiInterface = ApiService.apiInterface();
                Call<String> callback = apiInterface.UpdateFavorite(user.getIdMember(), mSach.get(holder.getPosition()).getIdSach());
                callback.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String ketqua = response.body();
                        if (ketqua.equals("like"))
                        {
                            holder.mIcon.setImageResource(R.drawable.ic_baseline_favorite_1_24);
                            Toast.makeText(context, "Đã thích", Toast.LENGTH_SHORT).show();
                        }else if(ketqua.equals("unlike")){
                            holder.mIcon.setImageResource(R.drawable.ic_baseline_favorite_24);
                            Toast.makeText(context, "Bỏ thích", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }

        });
    }

public void searchBook(ArrayList<Sach> search)
{
    if(search !=null)
    {
        mSach = search;
        notifyDataSetChanged();
    }

}
    @Override
    public int getItemCount() {
      if(mSach != null)
      {
          return mSach.size();
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
        private CardView cardView;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.txt_tensach);
            tv_tacgia = itemView.findViewById(R.id.txt_tacgia);
            comment = itemView.findViewById(R.id.comment);
            textView = itemView.findViewById(R.id.ic_view);
            page = itemView.findViewById(R.id.icon_book);
            mBook = itemView.findViewById(R.id.bg_sach);
            mIcon = itemView.findViewById(R.id.icon_favorite);
            cardView = itemView.findViewById(R.id.card_viewBook);



        }

  }

}
