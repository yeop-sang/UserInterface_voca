package com.example.voca.card;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.voca.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CardTab extends Fragment implements View.OnClickListener{

    private FloatingActionButton cardFab;
    private LinearLayout linearLayout;
    private Button cardBtn11;
    private Button cardBtn21;
    private Button cardBtn12;
    private Button cardBtn22;

    private Boolean wordOrMean = true;
    private Boolean notLearned = true;
    private Boolean option3 = true;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CardTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MusicFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CardTab newInstance(String param1, String param2) {
        CardTab fragment = new CardTab();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        linearLayout = (LinearLayout) inflater.inflate(R.layout.card_tab,container,false);

        cardBtn11 = linearLayout.findViewById(R.id.card_btn11);
        cardBtn21 = linearLayout.findViewById(R.id.card_btn21);
        cardBtn12 = linearLayout.findViewById(R.id.card_btn12);
        cardBtn22 = linearLayout.findViewById(R.id.card_btn22);

        cardBtn22.setBackgroundColor(getResources().getColor(R.color.background_color));
        cardBtn21.setBackgroundColor(getResources().getColor(R.color.background_color));

        cardBtn11.setOnClickListener(this);
        cardBtn21.setOnClickListener(this);
        cardBtn12.setOnClickListener(this);
        cardBtn22.setOnClickListener(this);

        cardFab = (FloatingActionButton) linearLayout.findViewById(R.id.card_fab);
        cardFab.setOnClickListener(this);

        cardBtn11 = linearLayout.findViewById(R.id.card_btn11);
        //cardBtn11.setBackgroundResource(R.drawable.unselect_btn);

        return linearLayout;
    }

    @Override
    public void onClick(View v) {
        if (v == cardFab)  {
            // cardFab 클릭 시
            Intent intent = new Intent(v.getContext(), CardActivity.class);
            intent.putExtra("option1", wordOrMean);
            // t:영어, f:뜻
            intent.putExtra("option2", notLearned);
            // t:미완료, f:완료

            startActivity(intent);
        }
        else {
            v.setBackgroundColor(getResources().getColor(R.color.primary_color));

            if (v == cardBtn11)  {
                cardBtn21.setBackgroundColor(getResources().getColor(R.color.background_color));
                wordOrMean = true;
            }
            if (v == cardBtn21)  {
                cardBtn11.setBackgroundColor(getResources().getColor(R.color.background_color));
                wordOrMean = false;
            }
            if (v == cardBtn12)  {
                cardBtn22.setBackgroundColor(getResources().getColor(R.color.background_color));
                notLearned = true;
            }
            if (v == cardBtn22)  {
                cardBtn12.setBackgroundColor(getResources().getColor(R.color.background_color));
                notLearned = false;
            }
        }
    }

}
