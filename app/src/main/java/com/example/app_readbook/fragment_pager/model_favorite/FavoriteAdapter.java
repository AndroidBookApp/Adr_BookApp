package com.example.app_readbook.fragment_pager.model_favorite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.R;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.SearchViewHodel>{
   private List<ListFavorite> mlist;
   private Context context;

    public FavoriteAdapter( Context context) {

        this.context = context;
    }
    public void setData(List<ListFavorite> list)
    {
        this.mlist = list;
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
        ListFavorite listFavorite = mlist.get(position);
        if (mlist == null)
        {
            return;
        }
        holder.mBook.setImageResource(listFavorite.getResourceId());
        holder.tv_1.setText(listFavorite.getTac_gia());
        holder.tv_2.setText(listFavorite.getTitle_name());
        holder.mIcon.setImageResource(listFavorite.getIcon_clear());
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
        public SearchViewHodel(@NonNull View itemView) {
            super(itemView);
            tv_1 = itemView.findViewById(R.id.tensach);
            tv_2 = itemView.findViewById(R.id.tacgia);
            mBook = itemView.findViewById(R.id.sach);
            mIcon = itemView.findViewById(R.id.icon_clear);
        }
    }
}
