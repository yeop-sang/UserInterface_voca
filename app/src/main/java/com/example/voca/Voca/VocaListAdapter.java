package com.example.voca.Voca;

import android.app.Dialog;
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

    DialogFragment listDialog;
    FragmentManager manager; //androidx.fragment.app.FragmentManager

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
                final Dialog d = new Dialog(mContext); // 다이얼로그 객체 생성
                d.setTitle("다이얼로그의 제목");
                d.setContentView(R.layout.activity_list_view_dialog); // 다이얼로그 화면 등록

                /*Bundle args = new Bundle();
                args.putSerializable("key", current);

                DialogFragment dialogFragment = new DialogFragment ();
                dialogFragment.setArguments(args);
                dialogFragment.show(dialogFragment.getFragmentManager(), "Sample Dialog Fragment");*/

                d.show(); // 다이얼로그 띄우기

                Toast toast = Toast.makeText(view.getContext(), current.word+"클릭",Toast.LENGTH_SHORT);
                toast.show();
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
