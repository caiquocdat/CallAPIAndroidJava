package com.example.hocapi.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hocapi.R;
import com.example.hocapi.model.Post;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<Post> mListUser;

    public UserAdapter(List<Post> mListUser) {
        this.mListUser = mListUser;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Post user=mListUser.get(position);
        if (user==null){
            return;
        }
        holder.idTv.setText(String.valueOf(user.getId()));
        holder.titleTv.setText(user.getTitle());
    }

    @Override
    public int getItemCount() {
        if (mListUser!=null){
        return mListUser.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        private TextView idTv,titleTv;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            idTv=itemView.findViewById(R.id.idTv);
            titleTv=itemView.findViewById(R.id.titleTv);
        }
    }
}
