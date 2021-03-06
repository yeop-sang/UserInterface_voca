package com.example.voca.Voca;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.voca.List.ListAddDialog;
import com.example.voca.List.ListTab;
import com.example.voca.List.ListViewDialog;
import com.example.voca.R;

import java.io.Serializable;
import java.util.ArrayList;

public class VocaListAdapter extends ListAdapter<Voca, VocaViewHolder> {

    private DiffUtil.ItemCallback<Voca> vocaList;
    protected Context mContext;

    public VocaListAdapter(@NonNull DiffUtil.ItemCallback<Voca> diffCallback, Context context) {
        super(diffCallback);

        mContext = context;
        vocaList = diffCallback;
    }


    @Override
    public VocaViewHolder onCreateViewHolder(ViewGroup parent, int ViewType) {
        return VocaViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull VocaViewHolder holder, int position) {
        Voca current = getItem(position);
        holder.bind(current);

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent();

                ComponentName componentName = new ComponentName (
                        "com.example.voca",
                        "com.example.voca.List.ListViewDialog"
                );
                intent.setComponent(componentName);

                intent.putExtra("Voca_Data", current);

                mContext.startActivity(intent);
            }
        });
    }

    public static class VocaDiff extends DiffUtil.ItemCallback<Voca>  {

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
