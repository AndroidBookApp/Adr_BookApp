package com.example.app_readbook.View.fragment_pager.model_search;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.R;
import com.example.app_readbook.home;

import java.util.ArrayList;
import java.util.List;


public class Search_fragment extends Fragment {

   RecyclerView recyclerView;
    home home;
    private List<history> historyList ;
    HistoryAdaptor historyAdaptor;
    private EditText text;
    public Search_fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        home = new home();
        View view = inflater.inflate(R.layout.fragment_search_fragment , container , false);
        recyclerView = view.findViewById(R.id.rcv_listHistory);
        historyList = getlist();
        text = view.findViewById(R.id.txt_search);
        historyAdaptor = new HistoryAdaptor(home);
        historyAdaptor.setData(historyList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(home , LinearLayoutManager.VERTICAL , false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(historyAdaptor);
        text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                fliter(s.toString());

            }
        });
        return view;
    }

//    private void onClickNextPage(history history) {
//        Intent intent = new Intent(getContext(), Main_ListSach_Home.class);
////        Bundle bundle = new Bundle();
////        bundle.putSerializable("object_key" , history);
////        intent.putExtras(bundle);
//        startActivity(intent);
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void fliter(String name) {
        ArrayList<history> filterList = new ArrayList<>();
        for(history item : historyList)
        {
            if(item.getBook().toLowerCase().contains(name.toLowerCase()))
            {
                filterList.add(item);
                history history ;

            }
        }
        historyAdaptor.historyList(filterList);
    }

    private List<history> getlist() {
        List<history> list = new ArrayList<>();
        list.add(new history(R.drawable.ic_baseline_access_time_24, "Đắc Nhân Tâm", R.drawable.ic_baseline_clear_24 ));
        list.add(new history(R.drawable.ic_baseline_access_time_24, "Đắc Nhân Sĩ", R.drawable.ic_baseline_clear_24));
        list.add(new history(R.drawable.ic_baseline_access_time_24, "Kinh Doanh Online", R.drawable.ic_baseline_clear_24));
        list.add(new history(R.drawable.ic_baseline_access_time_24, "Nghĩ giàu làm giàu", R.drawable.ic_baseline_clear_24));
        list.add(new history(R.drawable.ic_baseline_access_time_24, "Đắc Nhân Mỹ", R.drawable.ic_baseline_clear_24));
        list.add(new history(R.drawable.ic_baseline_access_time_24, "Đắc Nhân Lão", R.drawable.ic_baseline_clear_24));
        return list;
    }

}
