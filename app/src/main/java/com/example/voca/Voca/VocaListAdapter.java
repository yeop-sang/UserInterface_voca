package com.example.voca.Voca;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

public class VocaListAdapter extends ListAdapter<Voca, VocaViewHolder> {

    public VocaListAdapter(@NonNull DiffUtil.ItemCallback<Voca> diffCallback) {
        super(diffCallback);
    }

    @Override
    public VocaViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        return VocaViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull VocaViewHolder holder, int position) {
        Voca current = getItem(position);
        holder.bind(current);
    }

    public static class VocaDiff extends DiffUtil.ItemCallback<Voca> {

        @Override
        public boolean areItemsTheSame(@NonNull Voca oldItem, @NonNull Voca newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Voca oldItem, @NonNull Voca newItem) {
            return oldItem.word.equals(newItem.word);
        }
    }
}
