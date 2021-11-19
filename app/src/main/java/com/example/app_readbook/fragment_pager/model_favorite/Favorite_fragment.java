package com.example.app_readbook.fragment_pager.model_favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.Model.User;
import com.example.app_readbook.Model.testfavorite;
import com.example.app_readbook.R;
import com.example.app_readbook.Service.ApiInterface;
import com.example.app_readbook.Service.ApiService;
import com.example.app_readbook.home;
import com.example.app_readbook.shareFreferences.DataManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Favorite_fragment extends Fragment {
private ArrayList<Sach> mlist ;
private RecyclerView recyclerView;
private testfavorite testfavorite;
private Sach sach;
User user;
String idMember , idSach ;
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
        mlist = new ArrayList<>();
//        mlist = DataManager.loadSach();
        user = DataManager.loadUser();
        idMember = user.getIdMember();
//        sach = DataManager.loadObjectSach();
//        idSach = sach.getIdSach();
        getDataListFavorite();
        return view;
    }

    private void getDataListFavorite() {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<testfavorite> listCall = apiInterface.getListFavorite(idMember);
        listCall.enqueue(new Callback<testfavorite>() {
            @Override
            public void onResponse(Call<testfavorite> call, Response<testfavorite> response) {
                mlist = response.body().getSach();
//                DataManager.loadObjectSach();
                if(response.isSuccessful())
                {
                    FavoriteAdapter favoriteAdapter = new FavoriteAdapter(mlist , getActivity());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity()  , LinearLayoutManager.VERTICAL , false);
                    DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity() , DividerItemDecoration.VERTICAL);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.addItemDecoration(itemDecoration);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(favoriteAdapter);
                }

            }

            @Override
            public void onFailure(Call<testfavorite> call, Throwable t) {

            }
        });
    }


}
