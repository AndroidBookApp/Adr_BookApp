package com.example.app_readbook.DataBookNew;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.app_readbook.R;

public class MainChapter extends AppCompatActivity {
    private TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chapter);
        textView = findViewById(R.id.txt_name);
        textView.setText(getIntent().getStringExtra("nameBook"));
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame_chapter , new ChapterFragment());
        fragmentTransaction.commit();

    }
    public void goToBack(ChapterName chapterName)
    {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        bookFragment readbookFragment = new bookFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("page_book" , chapterName);
        readbookFragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.frame_chapter , readbookFragment);
        fragmentTransaction.addToBackStack(bookFragment.TAG);
        fragmentTransaction.commit();
    }
}
