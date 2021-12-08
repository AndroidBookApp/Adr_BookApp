package com.example.app_readbook.View.View_Readbook;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.Model.User;
import com.example.app_readbook.Model.favoriteDeleteData;
import com.example.app_readbook.R;
import com.example.app_readbook.View.BroadCastRecivice.NextWorkConnect;
import com.example.app_readbook.ViewModel.AddFavoriteViewModel;
import com.example.app_readbook.shareFreferences.DataManager;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

public class View_ReadBook extends AppCompatActivity {

    private static final String SAVE_FAVORITE ="SAVE_FAVORITE" ;
    private Toolbar toolbar;
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
    String dataLike = "";
    boolean like  = false;
    AddFavoriteViewModel favoriteViewModel;
    NextWorkConnect nextWorkConnect = new NextWorkConnect();
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
        BackView();
        loadFavorite();
        dataLike = loadFavorites();
        if(idSach==null && idUser==null && dataLike.equals("like"))
        {
            favorites.setImageResource(R.drawable.ic_baseline_favorite_24);
        }else if(dataLike.equals("unlike"))
        {
            favorites.setImageResource(R.drawable.ic_baseline_favorite_1_24);
        }
        favoriteViewModel = new ViewModelProvider(this).get(AddFavoriteViewModel.class);
        favoriteViewModel.getAddFavorite().observe(this, new Observer<favoriteDeleteData>() {
            @Override
            public void onChanged(favoriteDeleteData favoriteDeleteData) {
                if(favoriteDeleteData.getSuccess().equals("like"))
                {
                    Toast.makeText(View_ReadBook.this, "Thích", Toast.LENGTH_SHORT).show();
                    favorites.setImageResource(R.drawable.ic_baseline_favorite_1_24);
                    saveFavorite(dataLike);

                }else if(favoriteDeleteData.getSuccess().equals("unlike")){
                    Toast.makeText(View_ReadBook.this, "Bỏ Thích", Toast.LENGTH_SHORT).show();
                    favorites.setImageResource(R.drawable.ic_baseline_favorite_24);
                    saveFavorite(dataLike);

                }
            }
        });
    }
    public void saveFavorite(String idMember)
    {
        SharedPreferences sharedPreferences = this.getSharedPreferences(SAVE_FAVORITE , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(dataLike , "");
        editor.apply();
    }
    public String loadFavorites(){
        SharedPreferences sharedPreferences = this.getSharedPreferences(SAVE_FAVORITE , Context.MODE_PRIVATE);
        return sharedPreferences.getString(dataLike, "");
    }

    private void BackView() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    @SuppressLint("SetTextI18n")
    private void loadFavorite() {
        //load dữ liệu đã lưu trong sharedPreferences ra
        Picasso.get().load(sach.getImgSach()).into(img_book);
        coordinatorLayout.setTitle(sach.getTensach());


    }
    private void initUI() {
        tableLayout = findViewById(R.id.table_view);
        viewPager = findViewById(R.id.viewpager_view_book);
        viewPagerAdaptor = new ViewPagerAdaptor(getSupportFragmentManager() , FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(viewPagerAdaptor);
        tableLayout.setupWithViewPager(viewPager);
        toolbar = findViewById(R.id.DanhMuc);
        coordinatorLayout = findViewById(R.id.collapsingToolbarLayout);
        img_book = findViewById(R.id.image_book);
        favorites = findViewById(R.id.btn_favoriteView);
        favorites.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                idUser = user.getIdMember();
//                idSach = sach.getIdSach();
                loadFavorite();
                favoriteViewModel.iniAddFavorite(idUser, idSach);
            }
        });
    }

    @Override
    protected void onStart() {
        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(nextWorkConnect , intentFilter);
        super.onStart();

    }

    @Override
    protected void onStop() {
        unregisterReceiver(nextWorkConnect);
        super.onStop();
    }

}
