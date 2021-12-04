package com.example.app_readbook.View.View_Readbook;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.Model.User;
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
    private AppCompatButton btnRead, btnSend;
    Sach sach;
    String idSach;
    String idUser ;

    private FloatingActionButton favorites;
    User user;
    String load;
    AddFavoriteViewModel favoriteViewModel;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_read);
        sach = new Sach();
        user = DataManager.loadUser();
        sach = DataManager.loadObjectSach();
        load = DataManager.lFavorite();
        initUI();
        loadFavorite();
//        checkFavorite();
//        getDatFavorite();
//        getDataViewSach();
    }
    private void checkFavorite() {
//        if(idUser.equals(user.getIdMember()) && idSach.equals(sach.getIdSach()))
//        {
//            if (load != null && load.equals("like")) {
//                favorites.setImageResource(R.drawable.ic_baseline_favorite_1_24);
//            }
//            else if (load != null && !load.equals("like") ) {
//                favorites.setImageResource(R.drawable.ic_baseline_favorite_24);
//            }
//        }
//        favorites = (FloatingActionButton) getIntent().getExtras().get("favorite");
    }

    @SuppressLint("SetTextI18n")
    private void loadFavorite() {
        //load dữ liệu đã lưu trong sharedPreferences ra
        idUser = user.getIdMember();
        idSach = sach.getIdSach();
        Picasso.get().load(sach.getImgSach()).into(img_book);
        coordinatorLayout.setTitle(sach.getTensach());
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
                idUser = user.getIdMember();
                idSach = sach.getIdSach();
                checkFavorite();
                favoriteViewModel.iniAddFavorite(idUser, idSach);
                loadFavorite();
            }
        });
    }

}
