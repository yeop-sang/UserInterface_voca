package com.example.voca.Voca;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.voca.R;

public class VocaViewHolder extends RecyclerView.ViewHolder{
    private final TextView wordView;
    private final TextView meanView;

    private VocaViewHolder(View itemView) {
        super(itemView);
        wordView = itemView.findViewById(R.id.word);
        meanView = itemView.findViewById(R.id.mean);
    }

    public void bind(Voca voca) {
        wordView.setText(voca.word);
        meanView.setText(voca.mean);
    }

    static VocaViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.voca_recycler_view, parent, false);
        return new VocaViewHolder(view);
    }
}
