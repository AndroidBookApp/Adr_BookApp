package com.example.app_readbook.View.list_book;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.Model.User;
import com.example.app_readbook.Model.favoriteDeleteData;
import com.example.app_readbook.Model.listFavorite;
import com.example.app_readbook.R;
import com.example.app_readbook.Class.TachText;
import com.example.app_readbook.View.View_Readbook.View_ReadBook;
import com.example.app_readbook.ViewModel.Service.ApiInterface;
import com.example.app_readbook.ViewModel.Service.ApiService;
import com.example.app_readbook.shareFreferences.DataManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookNewAdaptor extends RecyclerView.Adapter<BookNewAdaptor.BookNewViewHolder>{
   private ArrayList<Sach> saches;
   private Context context;
    String text , idmember , idBook;
    private TachText tachText;
    private favoriteDeleteData favoriteDeleteData;
    public BookNewAdaptor(Context context, ArrayList<Sach> newList) {
        this.context = context;
        this.saches = newList;
    }


    @NonNull
    @Override
    public BookNewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_new , parent , false);
        return  new BookNewViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull BookNewViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Sach list_bookNew = saches.get(position);
        if(list_bookNew == null)
        {
            return;
        }
        Glide.with(context).
                load(list_bookNew.getImgSach()).apply(new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(20)))
                .into(holder.bookNew);
        holder.nameBookNew.setText(list_bookNew.getTensach());
        holder.tacGiaNew.setText(list_bookNew.getTacgia());
        holder.TrangNew.setText(list_bookNew.getSochuong());
        holder.view_new.setText(list_bookNew.getLuotxem());
        holder.comment_new.setText(list_bookNew.getFeedback());
        //
        tachText = new TachText();
        // truyền vô
        text = list_bookNew.getTomtatND();
        // gán giá trị
        text = tachText.tach(text);
        // truyền vào tách
        holder.tomtatND.setText(text);
        User user = DataManager.loadUser();
        idmember = user.getIdMember();
        idBook = saches.get(position).getIdSach();
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<ArrayList<listFavorite>> listCall = apiInterface.loadFavorite(idmember);
        listCall.enqueue(new Callback<ArrayList<listFavorite>>() {
            @Override
            public void onResponse(Call<ArrayList<listFavorite>> call, Response<ArrayList<listFavorite>> response) {
                List<listFavorite> favorite = response.body();
                if(favorite!=null)
                {
                    String idSach = list_bookNew.getIdSach();
                    for (listFavorite listFavorite: favorite) {
                        String id  = listFavorite.getIdSach();
                        if(idSach.equals(id))
                        {
                            Log.e("AAA" , id);
                            holder.icon_favoriteNew.setImageResource(R.drawable.ic_baseline_favorite_1_24);

                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<listFavorite>> call, Throwable t) {

            }
        });
        holder.bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface apiInterface = ApiService.apiInterface();
                Call<String> strViewBook = apiInterface.ViewReadBook(saches.get(position).getIdSach(), "1");
                strViewBook.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String view = response.body();
                        if (view.equals("Success")) {
                            Intent intent = new Intent(context, View_ReadBook.class);
                            DataManager.saveObjectSach(list_bookNew);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {

                    }
                });
            }
        });
        holder.icon_favoriteNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface apiInterface = ApiService.apiInterface();
                Call<favoriteDeleteData> callFavorite = apiInterface.UpdateFavorites(idmember, saches.get(position).getIdSach());
                callFavorite.enqueue(new Callback<favoriteDeleteData>() {
                    @Override
                    public void onResponse(Call<favoriteDeleteData> call, Response<favoriteDeleteData> response) {
                        favoriteDeleteData = response.body();
                        if (response.isSuccessful() && favoriteDeleteData!=null) {
                            if (favoriteDeleteData.getSuccess().equals("like")) {
                                holder.icon_favoriteNew.setImageResource(R.drawable.ic_baseline_favorite_1_24);
                                Toast.makeText(context, "Thích", Toast.LENGTH_SHORT).show();
                            } else if (favoriteDeleteData.getSuccess().equals("unlike")) {
                                holder.icon_favoriteNew.setImageResource(R.drawable.ic_baseline_favorite_24);
                                Toast.makeText(context, "Bỏ Thích", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<favoriteDeleteData> call, Throwable t) {

                    }
                });

            }

        });

    }
    @Override
    public int getItemCount() {
        if(saches != null)
        {
            return saches.size();
        }
        return 0;
    }

    public class BookNewViewHolder extends RecyclerView.ViewHolder{
       private ImageView bookNew;
       private TextView nameBookNew;
       private TextView tacGiaNew;
       private TextView TrangNew , tomtatND , comment_new , view_new ;
       private ImageView icon_favoriteNew , bg;
        @SuppressLint("CutPasteId")
        public BookNewViewHolder(@NonNull View itemView) {
            super(itemView);
            bookNew = itemView.findViewById(R.id.bg_sach_new);
            nameBookNew = itemView.findViewById(R.id.txt_tensach_new);
            tacGiaNew = itemView.findViewById(R.id.txt_tacgia_new);
            TrangNew = itemView.findViewById(R.id.icon_book_new);
            icon_favoriteNew = itemView.findViewById(R.id.icon_favorite_new);
            tomtatND = itemView.findViewById(R.id.tomtatNDBookNew);
            comment_new = itemView.findViewById(R.id.comment_new);
            view_new = itemView.findViewById(R.id.ic_view_new);
            bg = itemView.findViewById(R.id.imageView2);
        }
    }
}

