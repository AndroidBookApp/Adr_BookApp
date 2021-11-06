package com.example.app_readbook.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_readbook.R;

import java.util.List;

public class UserAdaptor extends RecyclerView.Adapter<UserAdaptor.UserViewHolder>{
   private List<User> mUser;
   private Context mContext;

    public UserAdaptor( Context mContext ,List<User> mUser) {
        this.mUser = mUser;
        this.mContext = mContext;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user , parent , false);
        return new UserViewHolder(mView);
    }


    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = mUser.get(position);
        if (user == null)
        {
            return;
        }
        holder.username.setText(user.getUsername());
        holder.password.setText(user.getPass());
        holder.email.setText(user.getEmail());
    }


    @Override
    public int getItemCount() {
        if(mUser != null)
        {
            return mUser.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{
        private TextView  id , username , password , email;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            username= itemView.findViewById(R.id.username);
            password = itemView.findViewById(R.id.pass);
            email = itemView.findViewById(R.id.userEmail);
        }
    }
}
