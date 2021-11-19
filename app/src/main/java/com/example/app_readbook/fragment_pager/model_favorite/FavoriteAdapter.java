package com.example.app_readbook.fragment_pager.model_favorite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.Model.favoriteDeleteData;
import com.example.app_readbook.R;
import com.example.app_readbook.Service.ApiInterface;
import com.example.app_readbook.Service.ApiService;
import com.example.app_readbook.shareFreferences.DataManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.SearchViewHodel>{
   private ArrayList<Sach> mlist;
   private Context context;
   private Sach sach;
   private favoriteDeleteData favoriteDeleteData;
   String idMember , idSach;

    public FavoriteAdapter(ArrayList<Sach> mlist, Context context) {
        this.mlist = mlist;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_favorite , parent , false) ;
        return new SearchViewHodel(view);
    }


    @Override
    public void onBindViewHolder(@NonNull SearchViewHodel holder, int position) {
        Sach listFavorite = mlist.get(position);
        if (listFavorite == null)
        {
            return;
        }
        Picasso.get().load(listFavorite.getImgSach()).into(holder.mBook);
        holder.tv_1.setText(listFavorite.getTensach());
        holder.tv_2.setText(listFavorite.getTacgia());
        holder.tomtat.setText(listFavorite.getTomtatND());

//        mSach = new ArrayList<>();
        sach = DataManager.loadObjectSach();
        idSach = sach.getIdSach();
//        idSach = DataManager.loadSach().get();
        idMember = DataManager.loadUser().getIdMember();
//        idSach = mSach.get(holder.getPosition()).getIdSach();
        holder.mIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ApiInterface apiInterface = ApiService.apiInterface();
                Call<favoriteDeleteData> deleteDataCall = apiInterface.deleteFavorite(idMember,idSach);
                deleteDataCall.enqueue(new Callback<favoriteDeleteData>() {
                    @Override
                    public void onResponse(Call<favoriteDeleteData> call, Response<favoriteDeleteData> response) {
                        favoriteDeleteData = response.body();
                        if(response.isSuccessful())
                        {
                            if(favoriteDeleteData!=null) {
                                if (favoriteDeleteData.getMessage().equals("200")) {
                                   DataManager.loadSach();
                                    Toast.makeText(context, favoriteDeleteData.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }else {

                            Toast.makeText(context, favoriteDeleteData.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<favoriteDeleteData> call, Throwable t) {
                        Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });
            }
        });
    }


    @Override
    public int getItemCount() {
        if(mlist != null)
        {
            return mlist.size();
        }
        return 0;
    }

    public class SearchViewHodel extends RecyclerView.ViewHolder{
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
