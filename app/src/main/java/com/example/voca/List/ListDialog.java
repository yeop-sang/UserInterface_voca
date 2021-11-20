package com.example.voca.List;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Menu;

import androidx.fragment.app.DialogFragment;

import com.example.voca.R;

public class ListDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog (Bundle savedInstnanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setView(R.layout.list_dialog);
        builder.setPositiveButton("추가",null);
        builder.setNegativeButton("취소", null);
        AlertDialog dialog = builder.create();

        return dialog;
    }
}

