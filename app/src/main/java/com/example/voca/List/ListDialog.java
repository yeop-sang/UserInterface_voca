package com.example.voca.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.voca.MainActivity;
import com.example.voca.R;
import com.example.voca.Voca.Voca;
import com.example.voca.Voca.VocaDao;
import com.example.voca.Voca.VocaViewModel;

public class ListDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog (Bundle savedInstnanceState) {
        LayoutInflater inflater=getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.list_dialog, null);
        VocaViewModel vocaViewModel = new ViewModelProvider(this).get(VocaViewModel.class);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setView(dialogView);
        builder.setPositiveButton("추가", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
//                EditText word = dialogView.findViewById(R.id.word_input_text);
//                EditText mean = dialogView.findViewById(R.id.mean_input_text);
            }
        });
        builder.setNegativeButton("취소", null);
        AlertDialog dialog = builder.create();

        return dialog;
    }
}

