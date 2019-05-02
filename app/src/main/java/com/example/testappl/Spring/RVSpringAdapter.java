package com.example.testappl.Spring;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.testappl.R;

import java.util.List;

public class RVSpringAdapter extends RecyclerView.Adapter<RVSpringAdapter.SpringViewHolder>{
    List<SpringUser> userList;

    public RVSpringAdapter(List<SpringUser> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public SpringViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item_spring, viewGroup, false);
        return new SpringViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpringViewHolder viewHolder, int i) {
        viewHolder.textView.setText(userList.get(i).getText());
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class SpringViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        public SpringViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv_spring);
        }
    }
}