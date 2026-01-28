package com.example.selfprofile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HighlightAdapter
        extends RecyclerView.Adapter<HighlightAdapter.ViewHolder> {

    private final String[] names = {
            "Unreal ✨",
            "Fun 🎉",
            "Travel 🧭",
            "Food 🍕",
            "Games 👑",
            "Music 🔥"
    };

    private final int[] images = {
            R.drawable.cat,
            R.drawable.fun,
            R.drawable.travel,
            R.drawable.food,
            R.drawable.games,
            R.drawable.music
    };

    public HighlightAdapter(int i) {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_highlight, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ViewHolder holder,
            int position
    ) {
        holder.textView.setText(names[position]);
        holder.imageView.setImageResource(images[position]);
    }

    @Override
    public int getItemCount() {
        return names.length;
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
