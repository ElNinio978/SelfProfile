package com.example.selfprofile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HighlightAdapter extends RecyclerView.Adapter<HighlightAdapter.ViewHolder> {

    private final int count;
    private final String[] names = {"Unreal ✨", "Fun", "Travel", "Food", "Games", "Music"};

    public HighlightAdapter(int count) {
        this.count = count;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_highlight, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(names[position % names.length]);

        // Dodanie innych obrazów w highlightach
        // holder.imageView.setImageResource(R.drawable.xxx);
        holder.imageView.setImageResource(android.R.drawable.sym_def_app_icon);
    }

    @Override
    public int getItemCount() {
        return count;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
