package com.example.voca;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CardTab extends Fragment implements View.OnClickListener{

    private FloatingActionButton cardFab;
    private LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        linearLayout = (LinearLayout) inflater.inflate(R.layout.card_tab,container,false);

        cardFab = (FloatingActionButton) linearLayout.findViewById(R.id.card_fab);
        cardFab.setOnClickListener(this);

        context = container.getContext();   // 임시 Toast 띄워주기 용

        return linearLayout;
    }

    @Override
    public void onClick(View v) {
        if (v == cardFab)  {
            // cardFab 클릭 시

            Toast toast = Toast.makeText(context,"cardFab이 클릭됨!",Toast.LENGTH_SHORT);
            toast.show();

        }
    }

    private Context context; // 임시 Toast 띄워주기 용

}
