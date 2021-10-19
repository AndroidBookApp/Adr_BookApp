package com.example.app_readbook.listreabook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.app_readbook.R;
import com.example.app_readbook.readbook.ReadbookName;

import java.util.ArrayList;
import java.util.List;

public class nameMain extends AppCompatActivity {
    private TextView btn_back , btn_next , txt_book , txt_book_all;
    private ViewPager2 viewPager;
    private List<name> names;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.readbook);
        iniU();
        names = getlistnam();
        nameAdaptor nameAdaptor = new nameAdaptor(this , names);
        viewPager.setAdapter(nameAdaptor);
        txt_book.setText("1");
        txt_book_all.setText(String.valueOf(names.size()));

        viewPager.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                txt_book.setText(String.valueOf(position + 1 ));
                if (position == 0)
                {
                    btn_back.setVisibility(View.GONE);
                    btn_next.setVisibility(View.VISIBLE);
                } else if (position == names.size() - 1)
                {
                    btn_back.setVisibility(View.VISIBLE);
                    btn_next.setVisibility(View.GONE);
                } else {
                    btn_back.setVisibility(View.VISIBLE);
                    btn_next.setVisibility(View.VISIBLE);
                }
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });
    }


    private List<name> getlistnam() {
        List<name> list = new ArrayList<>();
        for(int i = 1 ; i<=10 ; i++)
        {
            list.add(new name("Trang" +i));
        }
        return  list;
    }

    private void iniU() {
        btn_back = findViewById(R.id.before);
        btn_next  = findViewById(R.id.next);
        txt_book = findViewById(R.id.txt1);
        txt_book_all = findViewById(R.id.txt2);
        viewPager = findViewById(R.id.viewpager);
    }
}