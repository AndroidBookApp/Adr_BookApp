package com.example.app_readbook.View.fragment_pager.model_favorite;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.Model.User;
import com.example.app_readbook.Model.favorite;
import com.example.app_readbook.R;
import com.example.app_readbook.ViewModel.DeleteFavoriteViewModel;
import com.example.app_readbook.ViewModel.FavoriteViewModel;
import com.example.app_readbook.home;
import com.example.app_readbook.shareFreferences.DataManager;

import java.util.List;
import java.util.Objects;


public class Favorite_fragment extends Fragment {

private RecyclerView recyclerView;
User user;
String idMember  , idSach;
home home;
FavoriteViewModel addFavoriteViewModel;
DeleteFavoriteViewModel deleteFavoriteViewModel;
FavoriteAdapter favoriteAdapter ;

    public Favorite_fragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_fragment, container, false);
        recyclerView = view.findViewById(R.id.rcv_list);
        home = new home();
        user = DataManager.loadUser();
        DataManager.loadFavorite();
        idMember = user.getIdMember();
//        idSach = DataManager.loadObjectSach().getIdSach();
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        addFavoriteViewModel = new ViewModelProvider(this).get(FavoriteViewModel.class);
        addFavoriteViewModel.getListFavorite().observe(this, new Observer<List<favorite>>() {
            @Override
            public void onChanged(List<favorite> favorites) {
                 favoriteAdapter = new FavoriteAdapter(getActivity(), new FavoriteAdapter.IClickDeleteFavorite() {
                    @Override
                    public void iClickDelete(favorite mFavorite) {
                        new AlertDialog.Builder(getActivity())
                                .setTitle("Xóa sách ra khỏi thư mục yêu thích")
                                .setMessage("Bạn có chắc chắn muốn xóa")
                                .setPositiveButton("Có", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        deleteFavoriteViewModel = new ViewModelProvider(Objects.requireNonNull(getActivity())).get(DeleteFavoriteViewModel.class);
                                        deleteFavoriteViewModel.getDeleteFavorite().observe(getActivity(), new Observer<List<favorite>>() {
                                            @Override
                                            public void onChanged(List<favorite> favorites) {
                                                if(favorites != null)
                                                {
                                                    favoriteAdapter.setData(favorites);
                                                    recyclerView.setAdapter(favoriteAdapter);
                                                }
                                            }
                                        });
//                                        idSach = DataManager.loadSach().get();
                                        deleteFavoriteViewModel.iniDeleteData(idMember ,idSach);
                                    }
                                }).setNegativeButton("Không" , null)
                                .show();

                    }
                });
                favoriteAdapter.setData(favorites);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(home, LinearLayoutManager.VERTICAL, false);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(favoriteAdapter);
            }
        });
        addFavoriteViewModel.iniDataFavorite(idMember);

    }

}
