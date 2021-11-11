package com.example.voca;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

public class ListDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog (Bundle savedInstnanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setView(R.layout.list_dialog);
        builder.setPositiveButton("OK",null);
        AlertDialog dialog = builder.create();

        return dialog;
    }
}

