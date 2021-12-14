package com.example.voca.List;


import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.voca.R;
import com.example.voca.Voca.Voca;
import com.example.voca.Voca.VocaViewModel;

public class ListViewDialog extends DialogFragment {


    @Override
    public Dialog onCreateDialog (Bundle savedInstnanceState) {
        LayoutInflater inflater=getLayoutInflater();

        final View dialogView = inflater.inflate(R.layout.activity_list_view_dialog, null);
        VocaViewModel vocaViewModel = new ViewModelProvider(this).get(VocaViewModel.class);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView);


        builder.setPositiveButton("학습완료", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //학습완료
            }
        });
        builder.setNegativeButton("취소", null);
        AlertDialog dialog = builder.create();

        return dialog;
    }
}