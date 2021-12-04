package com.example.app_readbook;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.app_readbook.View.ApiLoginOrRegister.dangnhap;
import com.example.app_readbook.View.fragment_pager.model_account.Account_fragment;
import com.example.app_readbook.View.fragment_pager.model_favorite.Favorite_fragment;
import com.example.app_readbook.View.fragment_pager.model_home.Home_fragment;
import com.example.app_readbook.View.fragment_pager.model_search.Search_fragment;
import com.example.app_readbook.shareFreferences.DataManager;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    String idMember, idSach;

    private AppBarLayout appBarLayout;
    private BottomNavigationView bottomNavigationView;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private androidx.appcompat.widget.Toolbar toolbar;
    private static final int FRAGMENTS_HOME = 1;
    private static final int FRAGMENTS_SEARCH = 2;
    private static final int FRAGMENTS_FAVORITE = 3;
    private static final int FRAGMENTS_ACCOUNT = 4;
    private int currentFragments = FRAGMENTS_HOME;

    @SuppressLint({"WrongViewCast", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_home);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.toolbar);
        appBarLayout = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);
        bottomNavigationView = findViewById(R.id.btn_navigatione);
        statusbar();
        idMember = DataManager.loadUser().getIdMember();
        bottomNavigationView.setOnItemSelectedListener(navListener);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navi_open, R.string.navi_close);
        drawerLayout.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(this);
        toggle.syncState();
        navigationView.bringToFront();
        showFragment(new Home_fragment());
        navigationView.setCheckedItem(R.id.btn_home);
        bottomNavigationView.setSelectedItemId(R.id.btn_home);
    }

    private NavigationBarView.OnItemSelectedListener navListener = new NavigationBarView.OnItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.btn_home:
                    DataManager.loadUser();
                    OpenHomeFragments();
                    navigationView.setCheckedItem(R.id.btn_home);
                    break;
                case R.id.btn_search:
                    DataManager.loadUser();
                    OpenSearchFragments();
                    navigationView.setCheckedItem(R.id.btn_search);
                    break;
                case R.id.btn_favorite:
                    DataManager.loadUser();
                    OpenFavoriteFragments();
                    navigationView.setCheckedItem(R.id.btn_favorite);
                    break;
                case R.id.btn_account:
                    DataManager.loadUser();
                    OpenAccountFragments();
                    navigationView.setCheckedItem(R.id.btn_account);
                    break;
            }
            return true;
        }
    };

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void OpenHomeFragments() {
        if (currentFragments != FRAGMENTS_HOME) {
            showFragment(new Home_fragment());
            currentFragments = FRAGMENTS_HOME;
        }
    }

    private void OpenSearchFragments() {
        if (currentFragments != FRAGMENTS_SEARCH) {
            showFragment(new Search_fragment());
            currentFragments = FRAGMENTS_SEARCH;
        }
    }

    private void OpenFavoriteFragments() {
        if (currentFragments != FRAGMENTS_FAVORITE) {
            showFragment(new Favorite_fragment());
            currentFragments = FRAGMENTS_FAVORITE;
        }
    }

    private void OpenAccountFragments() {
        if (currentFragments != FRAGMENTS_ACCOUNT) {
            showFragment(new Account_fragment());
            currentFragments = FRAGMENTS_ACCOUNT;
        }
    }

    private void showFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main, fragment);
        transaction.commit();
    }

    private void statusbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        getWindow().setStatusBarColor(ContextCompat.getColor(home.this, R.color.background_home));
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.btn_home)
        {
            OpenHomeFragments();
            bottomNavigationView.getMenu().findItem(R.id.btn_home).setChecked(true);
        }else if(id == R.id.btn_search)
        {
            OpenSearchFragments();
            bottomNavigationView.getMenu().findItem(R.id.btn_search).setChecked(true);
        }else if(id == R.id.btn_favorite)
        {
            OpenFavoriteFragments();
            bottomNavigationView.getMenu().findItem(R.id.btn_favorite).setChecked(true);
        }else if(id == R.id.btn_account)
        {
            OpenAccountFragments();
            bottomNavigationView.getMenu().findItem(R.id.btn_account).setChecked(true);
        }else if(id == R.id.btn_signOut)
        {
            DiaLogFail(Gravity.CENTER);
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void DiaLogFail(int gravity) {
        Dialog dialog = new Dialog(this);
        dialog.setCanceledOnTouchOutside(false);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_out);
        Button button_signUp = dialog.findViewById(R.id.yes);
        Button button_cancel = dialog.findViewById(R.id.no);
        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity = gravity;
        window.setAttributes(windowAttributes);
        button_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = home.this;
                if (context != null) {
                    Intent intent = new Intent(context, dangnhap.class);
                    context.startActivity(intent);
                }
            }
        });
        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
