package com.example.app_readbook.View.View_Readbook;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.Model.User;
import com.example.app_readbook.Model.favoriteDeleteData;
import com.example.app_readbook.R;
import com.example.app_readbook.ViewModel.AddFavoriteViewModel;
import com.example.app_readbook.shareFreferences.DataManager;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

public class View_ReadBook extends AppCompatActivity {


    private TabLayout tableLayout;
    private ViewPager viewPager;
    private ImageView img_book;
    ViewPagerAdaptor viewPagerAdaptor;
    private CollapsingToolbarLayout coordinatorLayout;
    Sach sach;
    String idSach;
    String idUser ;
    private FloatingActionButton favorites;
    User user;
    boolean like ;
    AddFavoriteViewModel favoriteViewModel;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_read);
        sach = new Sach();
        sach = DataManager.loadObjectSach();
        user = DataManager.loadUser();
        idUser = DataManager.loadUser().getIdMember();
        idSach = DataManager.loadObjectSach().getIdSach();
        initUI();
        loadFavorite();
        favoriteViewModel = new ViewModelProvider(this).get(AddFavoriteViewModel.class);
        favoriteViewModel.getAddFavorite().observe(this, new Observer<favoriteDeleteData>() {
            @Override
            public void onChanged(favoriteDeleteData favoriteDeleteData) {
                like = favoriteDeleteData.getMessage();
                if(like && favoriteDeleteData.getSuccess().equals("like"))
                {
                    Toast.makeText(View_ReadBook.this, "Thích", Toast.LENGTH_SHORT).show();
                    favorites.setImageResource(R.drawable.ic_baseline_favorite_1_24);
                    like = true;
                    DataManager.Favorite(true , idUser);
                }else if(!like){
                    Toast.makeText(View_ReadBook.this, "Bỏ Thích", Toast.LENGTH_SHORT).show();
                    favorites.setImageResource(R.drawable.ic_baseline_favorite_24);
                    like = false;
                    DataManager.Favorite(false , idUser);
                }
            }
        });
    }
    @SuppressLint("SetTextI18n")
    private void loadFavorite() {
        //load dữ liệu đã lưu trong sharedPreferences ra
//        idUser = user.getIdMember();
//        idSach = sach.getIdSach();
        Picasso.get().load(sach.getImgSach()).into(img_book);
        coordinatorLayout.setTitle(sach.getTensach());
        like = DataManager.LFavorite();
        if(like && idUser.equals(user.getIdMember()) && idSach.equals(sach.getIdSach()))
        {
            favorites.setImageResource(R.drawable.ic_baseline_favorite_1_24);
        }else {
            favorites.setImageResource(R.drawable.ic_baseline_favorite_24);
        }
    }
    private void initUI() {
        tableLayout = findViewById(R.id.table_view);
        viewPager = findViewById(R.id.viewpager_view_book);
        viewPagerAdaptor = new ViewPagerAdaptor(getSupportFragmentManager() , FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdaptor);
        tableLayout.setupWithViewPager(viewPager);
        coordinatorLayout = findViewById(R.id.collapsingToolbarLayout);
        img_book = findViewById(R.id.image_book);
        favorites = findViewById(R.id.btn_favoriteView);
        favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                idUser = user.getIdMember();
//                idSach = sach.getIdSach();
                favoriteViewModel.iniAddFavorite(idUser, idSach);
            }
        });
    }

    private void DataFavorite() {

    }

}
