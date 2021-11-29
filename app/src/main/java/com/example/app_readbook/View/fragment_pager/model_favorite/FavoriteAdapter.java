package com.example.app_readbook.View.fragment_pager.model_favorite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.Model.favorite;
import com.example.app_readbook.Model.favoriteDeleteData;
import com.example.app_readbook.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.SearchViewHodel> {
    private List<favorite> mlist;
    private Context context;
    private Sach sach;
    favorite listFavorite;
    private favoriteDeleteData favoriteDeleteData;
    String idMember, idSach;
    String success;
    private IClickDeleteFavorite iClickDeleteFavorite;

    public interface IClickDeleteFavorite {
        void iClickDelete(favorite mFavorite);
    }

    public FavoriteAdapter(Context context , IClickDeleteFavorite iClickDeleteFavorite ) {
        this.iClickDeleteFavorite = iClickDeleteFavorite;
        this.context = context;
    }

    public void setData(List<favorite> mlist) {
        this.mlist = mlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_favorite, parent, false);
        return new SearchViewHodel(view);
    }


    @Override
    public void onBindViewHolder(@NonNull SearchViewHodel holder, @SuppressLint("RecyclerView") int position) {
        listFavorite = mlist.get(position);
        if (listFavorite == null) {
            return;
        }
        Picasso.get().load(listFavorite.getImgSach()).into(holder.mBook);
        holder.tv_1.setText(listFavorite.getTensach());
        holder.tv_2.setText(listFavorite.getTacgia());
        holder.tomtat.setText(listFavorite.getTomtatND());
        idSach = mlist.get(position).getIdSach();
        idMember = mlist.get(position).getIdMember();
        holder.mIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ApiInterface apiInterface = ApiService.apiInterface();
//                Call<String> favoriteCall = apiInterface.deleteFavorite(idMember, idSach);
//                favoriteCall.enqueue(new Callback<String>() {
//                    @SuppressLint("NotifyDataSetChanged")
//                    @Override
//                    public void onResponse(Call<String> call, Response<String> response) {
//                        String ketqua = response.body();
//                        if (ketqua.equals("success")) {
//                            setData(mlist);
//                            Log.e("AAAA", ketqua);
//                            notifyDataSetChanged();
//                            Toast.makeText(context, "Xóa Thành công", Toast.LENGTH_SHORT).show();
//
//                        } else {
//                            setData(mlist);
//                            Log.e("AAAA", ketqua);
//                            Toast.makeText(context, "Xóa Không Thành công", Toast.LENGTH_SHORT).show();
//                            notifyDataSetChanged();
//                        }
//
//                    }
//
//                    @Override
//                    public void onFailure(Call<String> call, Throwable t) {
//                        setData(mlist);
//                        Toast.makeText(context, "lỗi" + t.getMessage(), Toast.LENGTH_SHORT).show();
//                        Log.e("AAAA", t.getMessage());
//                    }
//                });

                iClickDeleteFavorite.iClickDelete(mlist.get(position));
            }
        });
    }


    @Override
    public int getItemCount() {
        if (mlist != null) {
            return mlist.size();
        }
        return 0;
    }

    public class SearchViewHodel extends RecyclerView.ViewHolder {
        private TextView tv_1;
        private TextView tv_2;
        private ImageButton mIcon;
        private ImageView mBook;
        private TextView tomtat;

        public SearchViewHodel(@NonNull View itemView) {
            super(itemView);
            tv_1 = itemView.findViewById(R.id.tensach);
            tv_2 = itemView.findViewById(R.id.tacgia);
            mBook = itemView.findViewById(R.id.sach);
            mIcon = itemView.findViewById(R.id.icon_clear);
            tomtat = itemView.findViewById(R.id.tomtatND);
        }
    }

}
