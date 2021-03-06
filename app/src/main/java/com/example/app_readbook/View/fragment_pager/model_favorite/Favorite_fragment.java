package com.example.app_readbook.View.fragment_pager.model_favorite;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.app_readbook.Class.CustomProgessDialog;
import com.example.app_readbook.Model.User;
import com.example.app_readbook.Model.favorite;
import com.example.app_readbook.R;
import com.example.app_readbook.ViewModel.FavoriteViewModel;
import com.example.app_readbook.ViewModel.Service.ApiInterface;
import com.example.app_readbook.ViewModel.Service.ApiService;
import com.example.app_readbook.home;
import com.example.app_readbook.shareFreferences.DataManager;

import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Favorite_fragment extends Fragment {

    private RecyclerView recyclerView;
    User user;
    String idMember;
    home home;
    private List<favorite> mFavorite;
    private ImageView tv_thongbao;
    FavoriteViewModel addFavoriteViewModel;
    FavoriteAdapter favoriteAdapter;
    CustomProgessDialog customProgessDialog;
    private SwipeRefreshLayout refreshLayout;
    public Favorite_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_fragment, container, false);
        recyclerView = view.findViewById(R.id.rcv_list);
        tv_thongbao = view.findViewById(R.id.img_rong);
//        refreshLayout = view.findViewById(R.id.refresh_favorite);
//        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                loadData(mFavorite);
//                refreshLayout.setRefreshing(false);
//            }
//        });
        home = new home();
        user = DataManager.loadUser();
        idMember = user.getIdMember();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        customProgessDialog = new CustomProgessDialog(Objects.requireNonNull(getContext()));
        customProgessDialog.show();
        addFavoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
        addFavoriteViewModel.getListFavorite().observe(this, new Observer<List<favorite>>() {
            @SuppressLint({"NotifyDataSetChanged", "SetTextI18n"})
            @Override
            public void onChanged(List<favorite> favorites) {
                if (favorites!=null) {
                    favoriteAdapter = new FavoriteAdapter(getActivity(), new FavoriteAdapter.IClickDeleteFavorite() {
                        @SuppressLint("NotifyDataSetChanged")
                        @Override
                        public void iClickDelete(favorite mFavorite) {
                            new AlertDialog.Builder(getActivity())
                                    .setTitle("X??a S??ch Ra Kh???i Th?? M???c Y??u Th??ch")
                                    .setMessage("B???n C?? Ch???c Ch???n Mu???n X??a")
                                    .setPositiveButton("C??", new DialogInterface.OnClickListener() {
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
                                                        Toast.makeText(getActivity(), "X??a Th??nh c??ng", Toast.LENGTH_SHORT).show();
                                                        loadData(favorites);
                                                        addFavoriteViewModel.iniDataFavorite(idMember);
                                                    } else {
                                                        Log.e("AAAA", ketqua);
                                                        Toast.makeText(getActivity(), "X??a Kh??ng Th??nh c??ng", Toast.LENGTH_SHORT).show();
                                                    }
                                                }

                                                @Override
                                                public void onFailure(Call<String> call, Throwable t) {

                                                    Toast.makeText(getActivity(), "l???i" + t.getMessage(), Toast.LENGTH_SHORT).show();
                                                    Log.e("AAAA", t.getMessage());
                                                }
                                            });

                                        }
                                    })
                                    .setNegativeButton("Kh??ng", null)
                                    .show();
                        }

                    });
                    recyclerView.setVisibility(View.VISIBLE);
                    tv_thongbao.setVisibility(View.GONE);
                    loadData(favorites);
                    customProgessDialog.dismiss();
                }else {
                    customProgessDialog.dismiss();
                    recyclerView.setVisibility(View.GONE);
                    tv_thongbao.setVisibility(View.VISIBLE);

                }
            }
        });
        addFavoriteViewModel.iniDataFavorite(idMember);


    }

    public void loadData(List<favorite>favorites) {
        favoriteAdapter.setData(favorites);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(home, LinearLayoutManager.VERTICAL, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(favoriteAdapter);

        }


}
