package com.example.app_readbook.list_comment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.Model.danhgia;
import com.example.app_readbook.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentAdaptor extends RecyclerView.Adapter<CommentAdaptor.CommentViewHolder>{
    private ArrayList<danhgia> danhgias;
private Context context;

    public CommentAdaptor(ArrayList<danhgia> danhgias, Context context) {
        this.danhgias = danhgias;
        this.context = context;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_comment , parent , false);
        return new CommentViewHolder(view);
    }


    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        danhgia danhgia = danhgias.get(position);
        if(danhgia == null)
        {
            return;
        }
        Picasso.get().load(danhgia.getImgAvatar()).into(holder.imageView);
        if(danhgia.getMemberName() == null)
        {
            holder.name.setText("chưa thiết lập");
        }else{
            holder.name.setText(danhgia.getMemberName());
        }

        holder.comment.setText(danhgia.getNoidung());
        holder.time.setText(danhgia.getThoigian());
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
