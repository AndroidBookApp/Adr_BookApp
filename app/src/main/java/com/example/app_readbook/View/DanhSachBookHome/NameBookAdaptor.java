package com.example.app_readbook.View.DanhSachBookHome;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.Model.DanhMucSach;
import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.R;
import com.example.app_readbook.View.DanhSachBookHome.book.BookAdaptor;
import com.example.app_readbook.View.fragment_pager.model_home.Home_fragment;
import com.example.app_readbook.View.list_book.Main_ListBook;
import com.example.app_readbook.ViewModel.Service.ApiInterface;
import com.example.app_readbook.ViewModel.Service.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NameBookAdaptor extends RecyclerView.Adapter<NameBookAdaptor.NameBookViewHolder> {
    private ArrayList<DanhMucSach> mDanhmuc;
    private ArrayList<Sach> saches;
    private Context mcontext;
    DanhMucSach danhMucSach;
    List<Sach> sach;
    Home_fragment home_fragment;

    public NameBookAdaptor(Context mcontext  ,ArrayList<DanhMucSach> listDanhMuc) {
        this.mcontext = mcontext;
        this.mDanhmuc = listDanhMuc;
    }

    @NonNull
    @Override
    public NameBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text , parent , false);
        return new NameBookViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull NameBookViewHolder holder, int position) {
      DanhMucSach danhMucSach = mDanhmuc.get(position);
      if(danhMucSach == null)
      {
          return;
      }
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<List<Sach>> mSach = apiInterface.listDanhMuc(danhMucSach.getIdDanhmuc());
        mSach.enqueue(new Callback<List<Sach>>() {
            @Override
            public void onResponse(Call<List<Sach>> call, Response<List<Sach>> response) {
                saches = (ArrayList<Sach>) response.body();
                BookAdaptor bookAdaptor = new BookAdaptor(saches ,mcontext);
                holder.recyclerView.setLayoutManager(new LinearLayoutManager(mcontext  , LinearLayoutManager.HORIZONTAL , false));
                holder.recyclerView.setAdapter(bookAdaptor);
            }

            @Override
            public void onFailure(Call<List<Sach>> call, Throwable t) {

            }
        });

        holder.tvname.setText(danhMucSach.getTendanhmuc());
        holder.tvnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext , Main_ListBook.class);
                intent.putExtra("danhmuc" , mDanhmuc.get(holder.getPosition()));
                mcontext.startActivity(intent);
            }
        });
        holder.tv_sl.setText("("+danhMucSach.getSosach()+")");
    }



    @Override
    public int getItemCount() {
        if(mDanhmuc != null)
        {
            return mDanhmuc.size();
        }
        return 0;
    }

    public static class NameBookViewHolder extends RecyclerView.ViewHolder{
private  RecyclerView recyclerView;
private TextView tvname;
private TextView tv_sl;
private TextView tvnext;
private RelativeLayout relativeLayout;
        public NameBookViewHolder(@NonNull View itemView ) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.ryc_view);
            relativeLayout = itemView.findViewById(R.id.layout_text);
            tvname = itemView.findViewById(R.id.tv_1);
            tv_sl = itemView.findViewById(R.id.tv_2);
            tvnext = itemView.findViewById(R.id.tv_next);
        }
    }
}
