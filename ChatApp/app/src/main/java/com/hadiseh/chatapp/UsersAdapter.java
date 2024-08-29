package com.hadiseh.chatapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserHolder> {


    ArrayList<User> users;
    Context context;
    private OnUserClickListener onUserClickListener;

    interface OnUserClickListener{
        void onUserClicked(int position);
    }


    public UsersAdapter(ArrayList<User> users, Context context, OnUserClickListener onUserClickListener) {
        this.users = users;
        this.context = context;
        this.onUserClickListener = onUserClickListener;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.user_holder , parent , false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        holder.txtusername.setText(users.get(position).getUsername());
        Glide.with(context).load(users.get(position).getProfilePicture()).error(R.drawable.account_img).placeholder(R.drawable.account_img).into(holder.imgView);

    }



    @Override
    public int getItemCount() {
        return users.size();
    }



    class UserHolder extends RecyclerView.ViewHolder {
        TextView txtusername;
        ImageView imgView;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            txtusername = itemView.findViewById(R.id.txtUsername);
            imgView = itemView.findViewById(R.id.img_pro);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onUserClickListener.onUserClicked(getAdapterPosition());
                }
            });
        }
    }
}
