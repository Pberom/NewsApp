package com.example.sqlitenews;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView nameNewsView, textNewsView;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        nameNewsView = itemView.findViewById(R.id.txtNameNews);
        textNewsView = itemView.findViewById(R.id.txtTextNews);
    }
}
