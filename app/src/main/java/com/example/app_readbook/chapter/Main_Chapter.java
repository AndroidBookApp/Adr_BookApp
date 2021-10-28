package com.example.app_readbook.chapter;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.R;

import java.util.ArrayList;
import java.util.List;

public class Main_Chapter extends AppCompatActivity {
private RecyclerView recyclerView;
private List<NameChapter> nameChapters;
private TextView text_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chapter);
        recyclerView = findViewById(R.id.rcv_chapter);
        text_name = findViewById(R.id.txt_name);
        text_name.setText(getIntent().getStringExtra("name_book"));
        nameChapters = getListChapter();
        ChapterAdaptor chapterAdaptor = new ChapterAdaptor(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this , LinearLayoutManager.VERTICAL , false);
        recyclerView.setLayoutManager(linearLayoutManager);
        chapterAdaptor.setData(getListChapter());
        recyclerView.setAdapter(chapterAdaptor);

    }

    private List<NameChapter> getListChapter() {
        List<NameChapter> list = new ArrayList<>();
        list.add(new NameChapter("Chương 1 : Khát Vọng" , "trang 51 - 76"));
        list.add(new NameChapter("Chương 2 : Niềm tin" , "trang 77 - 104"));
        list.add(new NameChapter("Chương 3 : Tự kỉ ám thị" , "trang 51 - 76"));
        list.add(new NameChapter("Chương 4 : Kiến thức chuyên môn" , "trang 51 - 76"));
        list.add(new NameChapter("Chương 5 : Óc tưởng tượng" , "trang 51 - 76"));
        list.add(new NameChapter("Chương 6 : Lập kế hoạch" , "trang 51 - 76"));
        return list;
    }
}