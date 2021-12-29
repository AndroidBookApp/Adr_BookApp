package com.example.app_readbook.View.fragment_pager.model_home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.app_readbook.Class.CustomProgessDialog;
import com.example.app_readbook.Model.DanhMucSach;
import com.example.app_readbook.Model.Sach;
import com.example.app_readbook.R;
import com.example.app_readbook.View.DanhSachBookHome.NameBookAdaptor;
import com.example.app_readbook.View.fragment_pager.PhotoAdaptor;
import com.example.app_readbook.View.list_book.Main_BookNew;
import com.example.app_readbook.ViewModel.Service.ApiInterface;
import com.example.app_readbook.ViewModel.Service.ApiService;
import com.example.app_readbook.home;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import me.relex.circleindicator.CircleIndicator3;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Home_fragment extends Fragment{
    private View mView;
    home mHome;
    private CircleIndicator3 indicator;
    private ViewPager2  view;
    private ArrayList<DanhMucSach> danhMucSaches;
    private NameBookAdaptor nameBookAdaptor;
    private TextView list_bookNew;
    private LinearLayout layout_bookNew;
    private RecyclerView recyclerView;
    int currentItem;

    private ProgressDialog dialog;
    private  Handler mHandler ;
    private  Runnable mRunnable ;
    CustomProgessDialog customProgessDialog;
    public Home_fragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home_fragment, container, false);
        mHome = new home();
        customProgessDialog = new CustomProgessDialog(Objects.requireNonNull(getContext()));
        customProgessDialog.show();
        indicator = mView.findViewById(R.id.cr);
        list_bookNew = mView.findViewById(R.id.all_bookNew);
        view = mView.findViewById(R.id.view_2);
        layout_bookNew = mView.findViewById(R.id.id3);
        recyclerView = mView.findViewById(R.id.rcv_name);
        getDataDanhMuc();
        view.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mHandler.removeCallbacks(mRunnable);
                mHandler.postDelayed(mRunnable, 3000);
            }
        });
        layout_bookNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = getActivity();
                Intent intent = new Intent(context , Main_BookNew.class);
                context.startActivity(intent);
            }
        });
        getDataImg();
        return mView;
    }

    private void getDataDanhMuc() {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<List<DanhMucSach>> mDanhMuc = apiInterface.TenDanhMuc();
        mDanhMuc.enqueue(new Callback<List<DanhMucSach>>() {
            @Override
            public void onResponse(Call<List<DanhMucSach>> call, Response<List<DanhMucSach>> response) {
                customProgessDialog.dismiss();
                danhMucSaches = (ArrayList<DanhMucSach>) response.body();
                nameBookAdaptor = new NameBookAdaptor(getActivity(),danhMucSaches);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                recyclerView.setAdapter(nameBookAdaptor);
            }

            @Override
            public void onFailure(Call<List<DanhMucSach>> call, Throwable t) {

            }
        });
    }
    private void getDataImg() {
        ApiInterface apiInterface = ApiService.apiInterface();
        Call<List<Sach>> listCall = apiInterface.responseSach();
        listCall.enqueue(new Callback<List<Sach>>() {
            @Override
            public void onResponse(Call<List<Sach>> call, Response<List<Sach>> response) {

                ArrayList<Sach> saches = (ArrayList<Sach>) response.body();
                PhotoAdaptor photoAdaptor = new PhotoAdaptor(saches , getActivity());
                view.setAdapter(photoAdaptor);
                indicator.setViewPager(view);
                mHandler = new Handler(Looper.getMainLooper());
                mRunnable = new Runnable() {
                    @Override
                    public void run() {
                        currentItem = view.getCurrentItem();
                        currentItem++;

                        if(currentItem >=view.getAdapter().getItemCount())
                        {
                            currentItem = 0;
                        }
                        view.setCurrentItem(currentItem, true);
                    }
                };
            }

            @Override
            public void onFailure(Call<List<Sach>> call, Throwable t) {
                Toast.makeText(mHome, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }


}


