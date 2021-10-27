package com.example.app_readbook.fragment_pager.model_favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.R;
import com.example.app_readbook.home;

import java.util.ArrayList;
import java.util.List;


public class Favorite_fragment extends Fragment {
private List<ListFavorite> mList;
private RecyclerView recyclerView;
    public Favorite_fragment() {
        // Required empty public constructor
    }
    home home;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_fragment, container, false);
        home = new home();
        recyclerView = view.findViewById(R.id.rcv_list);
        mList = getList();
        FavoriteAdapter favoriteAdapter = new FavoriteAdapter(home);
        favoriteAdapter.setData(mList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(home  , LinearLayoutManager.VERTICAL , false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(favoriteAdapter);
        return view;
    }

    private List<ListFavorite> getList() {
        List<ListFavorite> list = new ArrayList<>();
        list.add(new ListFavorite(R.drawable.sach2 , "Nghĩ Giàu Làm Giàu","tác giả 1",
                R.drawable.ic_baseline_clear_24));
        list.add(new ListFavorite(R.drawable.sach2 , "Nghĩ Giàu Làm Giàu","tác giả 1",
                R.drawable.ic_baseline_clear_24));
        list.add(new ListFavorite(R.drawable.sach2 , "Nghĩ Giàu Làm Giàu","tác giả 1",
                R.drawable.ic_baseline_clear_24));
        list.add(new ListFavorite(R.drawable.sach2 , "Nghĩ Giàu Làm Giàu","tác giả 1",
                R.drawable.ic_baseline_clear_24));
        list.add(new ListFavorite(R.drawable.sach2 , "Nghĩ Giàu Làm Giàu","tác giả 1",
                R.drawable.ic_baseline_clear_24));
        return list;
    }
}
