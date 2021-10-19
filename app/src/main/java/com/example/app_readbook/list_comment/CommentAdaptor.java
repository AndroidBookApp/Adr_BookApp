package com.example.app_readbook.list_comment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommentAdaptor extends RecyclerView.Adapter<CommentAdaptor.CommentViewHolder>{
    private List<Comment> mList;
private Context context;
    public CommentAdaptor(Context context1) {
       this.context = context1;
    }
public void setData(List<Comment> list)
{
    this.mList = list;
    notifyDataSetChanged();
}
    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_comment , parent , false);
        return new CommentViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        Comment comment = mList.get(position);
        if(mList == null)
        {
            return;
        }
        holder.imageView.setImageResource(comment.getIdResource());
        holder.name.setText(comment.getUsername());
        holder.comment.setText(comment.getUser_chat());
        holder.like.setText(comment.getIcon_like());
        holder.feedback.setText(comment.getIcon_feedback());
        holder.time.setText(comment.getTime());
    }


    @Override
    public int getItemCount() {
        if(mList != null)
        {
            return mList.size();
        }
        return 0;
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder{
    private CircleImageView imageView;
    private TextView name;
    private TextView like;
    private TextView feedback;
    private TextView time;
    private EditText comment;
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
