package com.example.app_readbook.DataBookNew;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChapterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChapterFragment extends Fragment {
private RecyclerView recyclerView;
private MainChapter mainChapter;
private ChapterNameAdapter chapterNameAdapter;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChapterFragment() {
        // Required empty public constructor
    }

    public static ChapterFragment newInstance(String param1, String param2) {
        ChapterFragment fragment = new ChapterFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chapter, container, false);
        recyclerView = view.findViewById(R.id.rcvchapter);
        mainChapter = (MainChapter) getActivity();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainChapter , LinearLayoutManager.VERTICAL , false);
        recyclerView.setLayoutManager(linearLayoutManager);
        chapterNameAdapter = new ChapterNameAdapter(getListChapter(), new ChapterNameAdapter.IClickPage() {
            @Override
            public void IClick(ChapterName chapterName) {
                mainChapter.goToBack(chapterName);
            }
        });
        recyclerView.setAdapter(chapterNameAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(mainChapter , DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(itemDecoration);
        return view;
    }

    private List<ChapterName> getListChapter() {
        List<ChapterName> list = new ArrayList<>();
        list.add(new ChapterName("Chương 1 : Khát Vọng" , "trang 51 - 76"));
        list.add(new ChapterName("Chương 2 : Niềm tin" , "trang 77 - 104"));
        list.add(new ChapterName("Chương 3 : Tự kỉ ám thị" , "trang 51 - 76"));
        list.add(new ChapterName("Chương 4 : Kiến thức chuyên môn" , "trang 51 - 76"));
        list.add(new ChapterName("Chương 5 : Óc tưởng tượng" , "trang 51 - 76"));
        list.add(new ChapterName("Chương 6 : Lập kế hoạch" , "trang 51 - 76"));
        return list;
    }
}