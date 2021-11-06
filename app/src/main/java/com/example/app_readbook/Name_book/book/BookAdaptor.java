package com.example.app_readbook.Name_book.book;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.Name_book.IClickItemBook;
import com.example.app_readbook.Name_book.NameBookAdaptor;
import com.example.app_readbook.R;
import com.example.app_readbook.fragment_pager.model_home.Home_fragment;
import com.example.app_readbook.home;

import java.util.List;

public class BookAdaptor extends RecyclerView.Adapter<BookAdaptor.BookViewHodel> {
private List<book> mList;
private Context mContext;
private IClickItemBook iClickItemBook;
public home home;
public NameBookAdaptor nameBookAdaptor;
Home_fragment home_fragment;

    public BookAdaptor(Home_fragment home_fragment) {
        this.home_fragment = home_fragment;
    }

    public RecyclerView recyclerView;

    public BookAdaptor( Context context ) {
        this.mContext = context;
    }
    public void setOnClick(IClickItemBook iClickItem)
{
    iClickItemBook = iClickItem;
}
//
//    public BookAdaptor(Context context , IClickItemBook itemBook) {
//
//        this.mContext  = context;
//        this.iClickItemBook = itemBook;
//    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<book> list ){
    this.mList = list;
    notifyDataSetChanged();
}
    @NonNull
    @Override
    public BookViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listbookmain, parent, false);
        return new BookViewHodel(view , iClickItemBook);
    }


    @Override
    public void onBindViewHolder(@NonNull BookViewHodel holder, int position) {
         final book mBook = mList.get(position);
        if(mBook == null)
        {
            return;
        }
        holder.imageBook.setImageResource(mBook.getResourceId());
        holder.txtTitle.setText(mBook.getTitle());
        holder.relativeLayoutBookMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
////                String name = nameBook.getClass(); ;
//                home_fragment = new Home_fragment();
//                Intent intent = new Intent( mContext , View_Readbook.class);
//                intent.putExtra("img_book", mBook.getResourceId());
//                intent.putExtra("name", mBook.getTitle());
////                intent.putExtra("name_chapter" , name.getBytes());
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                home_fragment.startActivity(intent);
//                mContext.startActivity(intent);
//                IClickItemBook.iClickBook(mBook);

            }
        });
        holder.imageBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
    @Override
    public int getItemCount() {
        if(mList != null)
        {
            return mList.size();
        }
        return 0;
    }

    public class BookViewHodel extends RecyclerView.ViewHolder {
        private ImageView imageBook;
        private TextView txtTitle;
        private RelativeLayout relativeLayoutBookMain;
        public BookViewHodel(@NonNull View itemView , IClickItemBook itemBook) {
            super(itemView);
            imageBook = itemView.findViewById(R.id.book);
            txtTitle = itemView.findViewById(R.id.text_view);
            relativeLayoutBookMain = itemView.findViewById(R.id.rlt_bookMain);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(itemBook!=null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION)
                        {
                            itemBook.iClickListener(position);
                        }
                    }
                }
            });
        }
    }

}
