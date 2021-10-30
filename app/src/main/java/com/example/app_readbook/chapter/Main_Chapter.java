package com.example.app_readbook.chapter;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.R;

import java.util.ArrayList;
import java.util.List;

public class Main_Chapter extends AppCompatActivity {
private RecyclerView recyclerView;
private List<NameChapter> nameChapters;
private TextView text_name;
private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chapter);
        recyclerView = findViewById(R.id.rcv_chapter);
        text_name = findViewById(R.id.txt_name);
        text_name.setText(getIntent().getStringExtra("nameBook"));
        nameChapters = getListChapter();
        ChapterAdaptor chapterAdaptor = new ChapterAdaptor(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        chapterAdaptor.setData(getListChapter());
        toolbar = findViewById(R.id.toolbar_chapter);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle(getIntent().getStringExtra("name_book"));
        recyclerView.setAdapter(chapterAdaptor);


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getStringExtra("nameBook"));
        super.onBackPressed();
    }

    private List<NameChapter> getListChapter() {
        List<NameChapter> list = new ArrayList<>();
        list.add(new NameChapter("Chương 1 : Khát Vọng" , "1" , "76"));
        list.add(new NameChapter("Chương 2 : Niềm tin" , "77" , "104"));
        list.add(new NameChapter("Chương 3 : Tự kỉ ám thị" , "105" , "120"));
        list.add(new NameChapter("Chương 4 : Kiến thức chuyên môn" , "121" , "140"));
        list.add(new NameChapter("Chương 5 : Óc tưởng tượng" , "141" , "155"));
        list.add(new NameChapter("Chương 6 : Lập kế hoạch" , "156" , "170"));
        return list;
    }
}