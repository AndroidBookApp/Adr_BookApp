package com.example.app_readbook.list_comment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.Model.danhgia;
import com.example.app_readbook.R;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentAdaptor extends RecyclerView.Adapter<CommentAdaptor.CommentViewHolder>{
    private List<danhgia> danhgias;
private Context context;
private Date date;
private Calendar calendar;
private SimpleDateFormat dateFormat;
long  timeDay;

String mTime , time;

    public CommentAdaptor( Context context) {

        this.context = context;
    }
    public void setData(List<danhgia> danhgias)
    {
        this.danhgias = danhgias;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_comment , parent , false);
        return new CommentViewHolder(view);
    }


    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        danhgia danhgia = danhgias.get(position);
        if(danhgia == null)
        {
            return;
        }
        Picasso.get().load(danhgia.getImgAvatar()).into(holder.imageView);
        holder.name.setText(danhgia.getMemberName());
        holder.comment.setText(danhgia.getNoidung());
        time = danhgia.getThoigian();

        date = Calendar.getInstance().getTime();
//        dateFormat = new SimpleDateFormat("dd/MM/yy" , Locale.getDefault());
        Log.e("AAA" , String.valueOf(date.getTime()));
//        timeDay = (timeReal - time) / 8640000;
//        mTime = String.valueOf(timeDay);
////        time = dateFormat.format(date.getTime());
        holder.time.setText(time);
    }


    @Override
    public int getItemCount() {
        if(danhgias != null)
        {
            return danhgias.size();
        }
        return 0;
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder{
    private CircleImageView imageView;
    private TextView name;
    private TextView like;
    private TextView feedback;
    private TextView time;
    private TextView comment;
        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cir_account);
            name = itemView.findViewById(R.id.name_user);
            comment = itemView.findViewById(R.id.txt_comment);
            like = itemView.findViewById(R.id.like);
            feedback = itemView.findViewById(R.id.feedback);
            time= itemView.findViewById(R.id.txt_time);

        }
    }
}
