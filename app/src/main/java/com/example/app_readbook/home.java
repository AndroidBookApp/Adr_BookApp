package com.example.app_readbook;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.app_readbook.fragment_pager.model_account.Account_fragment;
import com.example.app_readbook.fragment_pager.model_favorite.Favorite_fragment;
import com.example.app_readbook.fragment_pager.model_home.Home_fragment;
import com.example.app_readbook.fragment_pager.model_search.Search_fragment;
import com.example.app_readbook.list_comment.View_Readbook;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class home extends AppCompatActivity {

    private AppBarLayout appBarLayout;
    private BottomNavigationView bottomNavigationView;


    @SuppressLint({"WrongViewCast", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_home);
        appBarLayout  =findViewById(R.id.bar_footer);
        bottomNavigationView = findViewById(R.id.btn_navigatione);
        statusbar();

        showFragment(new Home_fragment());
        bottomNavigationView.setSelectedItemId(R.id.btn_home);
        bottomNavigationView.setOnItemSelectedListener(navListener);
        View_Readbook view_readbook = new View_Readbook();
    }
private NavigationBarView.OnItemSelectedListener navListener = new NavigationBarView.OnItemSelectedListener() {
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
         switch (item.getItemId()){

            case R.id.btn_home:
                appBarLayout.setVisibility(View.VISIBLE);
                fragment = new Home_fragment();
                break;
            case R.id.btn_search:
                appBarLayout.setVisibility(View.GONE);
                fragment = new Search_fragment();

                break;
            case R.id.btn_favorite:
                appBarLayout.setVisibility(View.GONE);
                fragment = new Favorite_fragment();

                break;
            case R.id.btn_account:
                appBarLayout.setVisibility(View.GONE);
                fragment = new Account_fragment();


                break;
            default:
                appBarLayout.setVisibility(View.VISIBLE);

                fragment = new Home_fragment();

                break;
        }
        return showFragment(fragment);
    }
    };

    @Override
    public void onBackPressed() {
        if (bottomNavigationView.getSelectedItemId() == R.id.btn_home) {
            super.onBackPressed();
            finish();
        }else{
            bottomNavigationView.setSelectedItemId(R.id.btn_home);
        }
    }

    private boolean showFragment(Fragment fragment) {
        if(fragment!=null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main ,fragment).addToBackStack(null).commit();
        }
        return true;
    }

    private void statusbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        getWindow().setStatusBarColor(ContextCompat.getColor(home.this, R.color.background_color));
    }

}
