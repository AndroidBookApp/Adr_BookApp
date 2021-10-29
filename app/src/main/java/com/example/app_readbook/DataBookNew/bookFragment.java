package com.example.app_readbook.DataBookNew;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.app_readbook.R;

import java.util.ArrayList;
import java.util.List;


public class bookFragment extends Fragment {
public static final String TAG = bookFragment.class.getName();
private TextView textView;
private Button button;
private ViewPager viewPager;
private List<PageModel> pageModels;
private TextView btn_back , btn_next , txt_book , txt_book_all , read;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public bookFragment() {

    }
    public static bookFragment newInstance(String param1, String param2) {
        bookFragment fragment = new bookFragment();
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
    View mview = inflater.inflate(R.layout.fragment_readbook, container, false);
    textView = mview.findViewById(R.id.tv_read);
    btn_back = mview.findViewById(R.id.before);
    btn_next = mview.findViewById(R.id.next);
    txt_book = mview.findViewById(R.id.txt1);
    txt_book_all = mview.findViewById(R.id.txt2);
    viewPager =mview.findViewById(R.id.pager);
    button = mview.findViewById(R.id.btn_back);
//    pageModels = getModelBook();
//    PageAdaptor pageAdaptor = new PageAdaptor(getFragmentManager() , FragmentStatePagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT , pageModels);
//    viewPager.setAdapter(pageAdaptor);
//    txt_book.setText("1");
//    txt_book_all.setText(String.valueOf("value"));
//    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//        @Override
//        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//        }
//
//        @Override
//        public void onPageSelected(int position) {
//            txt_book.setText(String.valueOf(position +1));
//            if(position == 0)
//            {
//                btn_back.setVisibility(View.GONE);
//                btn_next.setVisibility(View.VISIBLE);
//            } else if (position == pageModels.size() - 1)
//            {
//                btn_back.setVisibility(View.VISIBLE);
//                btn_next.setVisibility(View.GONE);
//            } else {
//                btn_back.setVisibility(View.VISIBLE);
//                btn_next.setVisibility(View.VISIBLE);
//            }
//        }
//
//        @Override
//        public void onPageScrollStateChanged(int state) {
//
//        }
//    });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);

            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });
    Bundle bundle = getArguments();
    if(bundle != null)
    {
        ChapterName chapterName = (ChapterName) bundle.get("page_book");
        if(chapterName != null)
        {
            textView.setText(chapterName.getChapter());
        }

    }
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(getFragmentManager() != null)
            {
                getFragmentManager().popBackStack();
            }
        }
    });
        return mview;
    }

    private List<PageModel> getModelBook() {
        List<PageModel> list = new ArrayList<>();
        for(int i = 1 ; i<=10 ; i++)
        {
            list.add(new PageModel("Trang" +i ));
        }
        return  list;
    }
}