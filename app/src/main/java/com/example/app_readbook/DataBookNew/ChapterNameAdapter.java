package com.example.app_readbook.DataBookNew;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.R;

import java.util.List;

public class ChapterNameAdapter extends RecyclerView.Adapter<ChapterNameAdapter.ChapterViewHolder>{
private List<ChapterName> list;
private IClickPage mIClickPage;
public interface IClickPage{
    void IClick(ChapterName chapterName);
}
    public ChapterNameAdapter(List<ChapterName> listChapter , IClickPage iClickPage) {
    this.mIClickPage = iClickPage;
        this.list = listChapter;
    }

    @NonNull
    @Override
    public ChapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_chapter , parent , false);
        return new ChapterViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ChapterViewHolder holder, int position) {
        final ChapterName chapterName = list.get(position);
        if(chapterName == null)
        {
            return;
        }
        holder.chapter.setText(chapterName.getChapter());
        holder.page.setText(chapterName.getBook());
        holder.chapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIClickPage.IClick(chapterName);
            }
        });

    }


    @Override
    public int getItemCount() {
        if(list != null)
        {
            return list.size();
        }
        return 0;
    }

    public class ChapterViewHolder extends RecyclerView.ViewHolder{
        private TextView chapter , page;
        private RelativeLayout relativeLayout;
        private Button button;
        public ChapterViewHolder(@NonNull View itemView) {
            super(itemView);
            chapter = itemView.findViewById(R.id.txt_chapter);
            page = itemView.findViewById(R.id.txt_book);
            relativeLayout = itemView.findViewById(R.id.rltChapter);
        }

    }
}
