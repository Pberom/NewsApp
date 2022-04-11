package com.example.sqlitenews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    interface OnStateClickListener{
        void onStateClick(item items, int position);
    }
    private final OnStateClickListener onClickListener;

    Context context;
    List<item> items;

    public MyAdapter(OnStateClickListener onClickListener, Context context, List<item> items) {
        this.onClickListener = onClickListener;
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_view, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        item model = items.get(position);
        holder.nameNewsView.setText(model.getName());
        holder.textNewsView.setText(model.getText());
        holder.itemView.setOnClickListener(view -> {
            onClickListener.onStateClick(model, position);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
