//package com.example.app_readbook.Lòng_RecyclerView;
//
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.cardview.widget.CardView;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.app_readbook.R;
//import com.example.app_readbook.View_ReadBook;
//import com.example.app_readbook.home;
//import com.squareup.picasso.Picasso;
//
//import java.util.List;
//
//public class bookAdaptor extends RecyclerView.Adapter<bookAdaptor.bookViewHolder>{
//   private List<list_book> mList;
//   private Context mContext;
//   home home;
//   public void setData(List<list_book> book_itemList)
//   {
//       this.mList  = book_itemList;
//       notifyDataSetChanged();
//   }
//    @NonNull
//    @Override
//    public bookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_adaptor2 , parent  , false);
//        return new bookViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull bookViewHolder holder, int position) {
//     list_book listBook = mList.get(position);
//     if(listBook == null)
//     {
//         return;
//     }
//        home = new home();
////        Glide.with(home).
////                load(listBook.getIMGsach()).apply(new RequestOptions().transform(new CenterCrop()).transform(new RoundedCorners(20)))
////                .into(holder.imageView);
//        Picasso.get().load(listBook.getIMGsach()).into(holder.imageView);
//     holder.textView.setText(listBook.getTenSach());
//     holder.cardView.setOnClickListener(new View.OnClickListener() {
//         @Override
//         public void onClick(View v) {
//             Context context = v.getContext();
//             Intent intent = new Intent(context , View_ReadBook.class);
//             Bundle bundle = new Bundle();
//             bundle.putString("img_book" , listBook.getIMGsach());
//             bundle.putString("name" , listBook.getTenSach());
//             bundle.putString("tac_gia" , listBook.getTentacgia());
//             bundle.putString("feedback" , listBook.getFeedback());
//             bundle.putString("page" , listBook.getSotrang());
//             bundle.putString("favorite" , listBook.getFavorite());
//             bundle.putString("idDanhmuc" , listBook.getIdDanhmuc());
//             bundle.putString("TenDanhMuc" , listBook.getTendanhmuc());
//             bundle.putString("TomTatND" , listBook.getTomtatND());
//             bundle.putInt("NgayXB" , listBook.getNgayXB());
//             bundle.putString("idSach" , listBook.getIdSach());
//             intent.putExtra("put_book",bundle);
//             intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//             context.startActivity(intent);
//         }
//     });
////     holder.imageView.setOnClickListener(new View.OnClickListener() {
////         @Override
////         public void onClick(View v) {
////             Context context = v.getContext();
////             Intent intent = new Intent(context , View_Readbook.class);
////             Bundle bundle = new Bundle();
////             bundle.putSerializable("img" , book_item.getImg());
////             bundle.putString("name_book" , book_item.getName());
////             intent.putExtra("object_itemBook", bundle);
////             context.startActivity(intent);
////         }
////     });
//    }
//
//    @Override
//    public int getItemCount() {
//       if(mList != null)
//       {
//           return mList.size();
//       }
//        return 0;
//    }
//
//    public class bookViewHolder extends RecyclerView.ViewHolder{
//       private ImageView imageView ;
//       private TextView textView;
//       private CardView cardView;
//        public bookViewHolder(@NonNull View itemView) {
//            super(itemView);
//            imageView = itemView.findViewById(R.id.book_adaptor);
//            textView = itemView.findViewById(R.id.text_viewAdaptor);
//            cardView = itemView.findViewById(R.id.cardView);
//        }
//    }
//}
