package com.example.app_readbook.testAdaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.R;

import java.util.List;

public class AllItemAdaptor extends RecyclerView.Adapter<AllItemAdaptor.AllViewHolder>{
    public static final String url = "http://192.168.1.6:8888/demo_app/sach.php";
private List<book_item> bookItemList;
private List<name_item> nameItemList;
private List<all_item> allItemList;
public static final int TYPE_NAME =1;
public static final int TYPE_BOOK =2;
public OnClickListener iOnClick;
    public interface OnClickListener{
        void IClick(int position);
    }
private Context mContext;
    public void setOnItemClickListener(OnClickListener listener)
    {
        iOnClick =  listener;
    }
    @Override
    public int getItemViewType(int position) {
        return allItemList.get(position).getType();
    }
    public void setData( Context context,List<all_item> all_items)
{
    this.mContext = context;
    this.allItemList = all_items;
    notifyDataSetChanged();
}
    @NonNull
    @Override
    public AllViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_adaptor_main , parent , false);
        return new AllViewHolder(view , iOnClick);
    }


    @Override
    public void onBindViewHolder(@NonNull AllViewHolder holder, int position) {
        all_item all_item = allItemList.get(position);
        if(all_item == null)
        {
            return;
        }
        if(TYPE_NAME == holder.getItemViewType())
        {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext , LinearLayoutManager.VERTICAL , false);
            holder.recyclerView.setLayoutManager(linearLayoutManager);
            holder.recyclerView.setFocusable(false);
            nameAdaptor nameAdaptor = new nameAdaptor();
            nameAdaptor.setData(all_item.getNameItemList());
            holder.recyclerView.setAdapter(nameAdaptor);

        }else if(TYPE_BOOK == holder.getItemViewType())
        {

                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext , LinearLayoutManager.HORIZONTAL , false);
                    holder.recyclerView.setLayoutManager(linearLayoutManager);
                    holder.recyclerView.setFocusable(false);
                    bookAdaptor bookAdaptor = new bookAdaptor();
                    bookAdaptor.setData(all_item.getBookItemList());
                    holder.recyclerView.setAdapter(bookAdaptor);
    }
        }

    @Override
    public int getItemCount() {
        if (allItemList != null)
        {
            return allItemList.size();
        }
        return 0;
    }

    public static class AllViewHolder extends RecyclerView.ViewHolder{
        private RecyclerView recyclerView;
        public AllViewHolder(@NonNull View itemView , OnClickListener iOnClick) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.ryc_viewAdaptor_main);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (iOnClick != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            iOnClick.IClick(position);
                        }
                    }
                }

            });
        }
    }
}
