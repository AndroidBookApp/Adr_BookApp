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
import com.example.app_readbook.ViewModel.AddFavoriteViewModel;
import com.example.app_readbook.ViewModel.Service.ApiInterface;
import com.example.app_readbook.ViewModel.Service.ApiService;
import com.example.app_readbook.shareFreferences.DataManager;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListBookAdaptor extends RecyclerView.Adapter<ListBookAdaptor.ListViewHolder> {
    private List<Sach> mSach;
    private Context context;
    public Main_ListBook main_listBook;
    String idmember, idBook , text;
    private TachText tachText;
    boolean favorite = false;
    private List<com.example.app_readbook.Model.favorite> mListFavorite;
    AddFavoriteViewModel favoriteViewModel;
    private favoriteDeleteData favoriteDeleteData;
    public ListBookAdaptor(Context context) {
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Sach> mSach) {
        this.mSach = mSach;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book, parent, false);
        return new ListViewHolder(view);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Sach sach = mSach.get(position);

        if (sach == null) {
            return;
        }

        // truyền các dữ liệu từ db vào list thông qua các hàm setter
        holder.tv_name.setText(sach.getTensach());
        holder.tv_tacgia.setText(sach.getTacgia());
        holder.comment.setText(sach.getFeedback());
        holder.textView.setText(sach.getLuotxem());
        holder.page.setText(sach.getSochuong());
        //
        tachText = new TachText();
        // truyền vô
        text = sach.getTomtatND();
        // gán giá trị
        text = tachText.tach(text);
        // truyền vào tách
        holder.tomtatND.setText(text);
        //load dữ liệu vào icon
        ApiInterface apiInterface = ApiService.apiInterface();
        Glide.with(context).
                load(sach.getImgSach()).apply(new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(20)))
                .into(holder.mBook);
        User user = DataManager.loadUser();
        idmember = user.getIdMember();
        idBook = mSach.get(position).getIdSach();
        Log.e("AAA" , idBook);
        Call<ArrayList<listFavorite>> listCall = apiInterface.loadFavorite(idmember);
        listCall.enqueue(new Callback<ArrayList<listFavorite>>() {
            @Override
            public void onResponse(Call<ArrayList<listFavorite>> call, Response<ArrayList<listFavorite>> response) {
                List<listFavorite> favorite = response.body();
                if(favorite!=null)
                {
                String idSach = sach.getIdSach();
                    for (listFavorite listFavorite: favorite) {
                        String id  = listFavorite.getIdSach();
                        if(idSach.equals(id))
                        {
                            holder.mIcon.setImageResource(R.drawable.ic_baseline_favorite_1_24);

                        }
                    }
                }
            }
            @Override
            public void onFailure(Call<ArrayList<listFavorite>> call, Throwable t) {

            }
        });


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface apiInterface = ApiService.apiInterface();
                Call<String> strViewBook = apiInterface.ViewReadBook(mSach.get(position).getIdSach(), "1");
                strViewBook.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String view = response.body();
                        if (view.equals("Success")) {
                            Intent intent = new Intent(context, View_ReadBook.class);
                            DataManager.saveObjectSach(sach);
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

        holder.mIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApiInterface apiInterface = ApiService.apiInterface();
                Call<favoriteDeleteData> callFavorite = apiInterface.UpdateFavorites(idmember, mSach.get(position).getIdSach());
                callFavorite.enqueue(new Callback<favoriteDeleteData>() {
                    @Override
                    public void onResponse(Call<favoriteDeleteData> call, Response<favoriteDeleteData> response) {
                        favoriteDeleteData = response.body();
                        if (response.isSuccessful() && favoriteDeleteData!=null) {
                            if (favoriteDeleteData.getSuccess().equals("like")) {
                                holder.mIcon.setImageResource(R.drawable.ic_baseline_favorite_1_24);
                                Toast.makeText(context, "Thích", Toast.LENGTH_SHORT).show();
                            } else if (favoriteDeleteData.getSuccess().equals("unlike")) {
                                holder.mIcon.setImageResource(R.drawable.ic_baseline_favorite_24);
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

    public void searchBook(ArrayList<Sach> search) {
        if (search != null) {
            mSach = search;
            notifyDataSetChanged();
        }

    }

    @Override
    public int getItemCount() {
        if (mSach != null) {
            return mSach.size();
        }
        return 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private TextView tv_tacgia;
        private TextView comment;
        private TextView textView;
        private TextView page, tomtatND;
        private RoundedImageView mBook;
        private ImageView mIcon;
        private ImageView cardView;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.txt_tensach);
            tv_tacgia = itemView.findViewById(R.id.txt_tacgia);
            comment = itemView.findViewById(R.id.comment);
            textView = itemView.findViewById(R.id.ic_view);
            page = itemView.findViewById(R.id.icon_book);
            mBook = itemView.findViewById(R.id.bg_sach);
            mIcon = itemView.findViewById(R.id.icon_favorite);
            cardView = itemView.findViewById(R.id.imageView2);
            tomtatND = itemView.findViewById(R.id.tomtatNDlistBook);


        }

    }

}
