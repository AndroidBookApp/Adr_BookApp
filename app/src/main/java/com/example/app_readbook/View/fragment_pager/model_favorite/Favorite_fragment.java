package com.example.app_readbook.View.fragment_pager.model_favorite;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.Model.User;
import com.example.app_readbook.Model.favorite;
import com.example.app_readbook.R;
import com.example.app_readbook.ViewModel.Service.ApiInterface;
import com.example.app_readbook.ViewModel.Service.ApiService;
import com.example.app_readbook.ViewModel.DeleteFavoriteViewModel;
import com.example.app_readbook.ViewModel.FavoriteViewModel;
import com.example.app_readbook.home;
import com.example.app_readbook.shareFreferences.DataManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Favorite_fragment extends Fragment {

    private RecyclerView recyclerView;
    User user;
    String idMember, idSach;
    home home;
    private TextView tv_thongbao;
    FavoriteViewModel addFavoriteViewModel;
    DeleteFavoriteViewModel deleteFavoriteViewModel;
    FavoriteAdapter favoriteAdapter;

    public Favorite_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_fragment, container, false);
        recyclerView = view.findViewById(R.id.rcv_list);
        tv_thongbao = view.findViewById(R.id.tv_thongbao);
        home = new home();
        user = DataManager.loadUser();
        idMember = user.getIdMember();
        idSach = DataManager.loadObjectSach().getIdSach();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addFavoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
        addFavoriteViewModel.getListFavorite().observe(this, new Observer<List<favorite>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onChanged(List<favorite> favorites) {
                DataManager.saveFavorite(favorites);
                favoriteAdapter = new FavoriteAdapter(getActivity(), new FavoriteAdapter.IClickDeleteFavorite() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public void iClickDelete(favorite mFavorite) {
                        new AlertDialog.Builder(getActivity())
                                .setTitle("Xóa Sách Ra Khỏi Thư Mực Yêu Thích")
                                .setMessage("Bạn Có Chắc Chắn Muốn Xóa")
                                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        ApiInterface apiInterface = ApiService.apiInterface();
                                        Call<String> favoriteCall = apiInterface.deleteFavorite(mFavorite.getIdSach(), mFavorite.getIdMember());
                                        favoriteCall.enqueue(new Callback<String>() {
                                            @SuppressLint("NotifyDataSetChanged")
                                            @Override
                                            public void onResponse(Call<String> call, Response<String> response) {
                                                String ketqua = response.body();
                                                if (ketqua.equals("success")) {

                                                    Log.e("AAAA", ketqua);
                                                    Toast.makeText(getActivity(), "Xóa Thành công", Toast.LENGTH_SHORT).show();
                                                    loadData(favorites);
                                                    addFavoriteViewModel.iniDataFavorite(idMember);
                                                } else {
                                                    Log.e("AAAA", ketqua);
                                                    Toast.makeText(getActivity(), "Xóa Không Thành công", Toast.LENGTH_SHORT).show();
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<String> call, Throwable t) {

                                                Toast.makeText(getActivity(), "lỗi" + t.getMessage(), Toast.LENGTH_SHORT).show();
                                                Log.e("AAAA", t.getMessage());
                                            }
                                        });

                                    }
                                })
                                .setNegativeButton("Không", null)
                                .show();


                    }


                });
                loadData(favorites);
            }
        });
        addFavoriteViewModel.iniDataFavorite(idMember);

    }

    public void loadData(List<favorite> favorite) {
        favoriteAdapter.setData(favorite);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(home, LinearLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(favoriteAdapter);

        }


}
