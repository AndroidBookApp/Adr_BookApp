package com.example.app_readbook.View.fragment_pager.model_favorite;

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
import com.example.app_readbook.shareFreferences.DataManager;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.SearchViewHodel>{
   private List<favorite> mlist;
   private Context context;
   private Sach sach;
   favorite listFavorite;
   private favoriteDeleteData favoriteDeleteData;
   String idMember , idSach;
   private IClickDeleteFavorite iClickDeleteFavorite;
    public interface IClickDeleteFavorite{
        void iClickDelete(favorite mFavorite);
    }
    public FavoriteAdapter( Context context , IClickDeleteFavorite iClickDeleteFavorite) {
        this.context = context;
        this.iClickDeleteFavorite = iClickDeleteFavorite;
    }
    public void setData(List<favorite> mlist)
    {
        this.mlist = mlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_favorite , parent , false) ;
        return new SearchViewHodel(view);
    }


    @Override
    public void onBindViewHolder(@NonNull SearchViewHodel holder, int position) {
         listFavorite = mlist.get(position);
        if (listFavorite == null)
        {
            return;
        }
        Picasso.get().load(listFavorite.getImgSach()).into(holder.mBook);
        holder.tv_1.setText(listFavorite.getTensach());
        holder.tv_2.setText(listFavorite.getTacgia());
        holder.tomtat.setText(listFavorite.getTomtatND());
//        sach = DataManager.loadObjectSach();
//        idSach = sach.getIdSach();
        idMember = DataManager.loadUser().getIdMember();
        holder.mIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickDeleteFavorite.iClickDelete(listFavorite);
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
