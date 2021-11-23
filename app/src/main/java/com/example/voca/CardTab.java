package com.example.voca;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CardTab extends Fragment implements View.OnClickListener{

    private FloatingActionButton cardFab;
    private LinearLayout linearLayout;
    private Button cardBtn11;
    private Button cardBtn21;
    private Button cardBtn12;
    private Button cardBtn22;
    private Button cardBtn13;
    private Button cardBtn23;

    private Boolean option1 = true;
    private Boolean option2 = true;
    private Boolean option3 = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        linearLayout = (LinearLayout) inflater.inflate(R.layout.card_tab,container,false);

        cardBtn11 = linearLayout.findViewById(R.id.card_btn11);
        cardBtn21 = linearLayout.findViewById(R.id.card_btn21);
        cardBtn12 = linearLayout.findViewById(R.id.card_btn12);
        cardBtn22 = linearLayout.findViewById(R.id.card_btn22);
        cardBtn13 = linearLayout.findViewById(R.id.card_btn13);
        cardBtn23 = linearLayout.findViewById(R.id.card_btn23);

        cardBtn23.setBackgroundColor(getResources().getColor(R.color.background_color));
        cardBtn22.setBackgroundColor(getResources().getColor(R.color.background_color));
        cardBtn21.setBackgroundColor(getResources().getColor(R.color.background_color));

        cardBtn11.setOnClickListener(this);
        cardBtn21.setOnClickListener(this);
        cardBtn12.setOnClickListener(this);
        cardBtn22.setOnClickListener(this);
        cardBtn13.setOnClickListener(this);
        cardBtn23.setOnClickListener(this);

        cardFab = (FloatingActionButton) linearLayout.findViewById(R.id.card_fab);
        cardFab.setOnClickListener(this);

        cardBtn11 = linearLayout.findViewById(R.id.card_btn11);
        //cardBtn11.setBackgroundResource(R.drawable.unselect_btn);

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
        else {
            v.setBackgroundColor(getResources().getColor(R.color.primary_color));

            if (v == cardBtn11)  {
                cardBtn21.setBackgroundColor(getResources().getColor(R.color.background_color));
                option1 = true;
            }
            if (v == cardBtn21)  {
                cardBtn11.setBackgroundColor(getResources().getColor(R.color.background_color));
                option1 = false;
            }
            if (v == cardBtn12)  {
                cardBtn22.setBackgroundColor(getResources().getColor(R.color.background_color));
                option2 = true;
            }
            if (v == cardBtn22)  {
                cardBtn12.setBackgroundColor(getResources().getColor(R.color.background_color));
                option2 = false;
            }
            if (v == cardBtn13)  {
                cardBtn23.setBackgroundColor(getResources().getColor(R.color.background_color));
                option3 = true;
            }
            if (v == cardBtn23)  {
                cardBtn13.setBackgroundColor(getResources().getColor(R.color.background_color));
                option3 = false;
            }
        }
    }

    private Context context; // 임시 Toast 띄워주기 용

}
