package com.example.voca.Quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.voca.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class QuizTab extends Fragment implements View.OnClickListener{

    private FloatingActionButton quizFab;
    private LinearLayout linearLayout;

    private Button quizBtn11;
    private Button quizBtn21;
    private Button quizBtn12;
    private Button quizBtn22;
    private Button quizBtn13;
    private Button quizBtn23;

    private Boolean option1 = true;
    private Boolean option2 = true;
    private Boolean option3 = true;

    private EditText editText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        linearLayout = (LinearLayout) inflater.inflate(R.layout.quiz_tab,container,false);

        quizBtn11 = linearLayout.findViewById(R.id.quiz_btn11);
        quizBtn21 = linearLayout.findViewById(R.id.quiz_btn21);
        quizBtn12 = linearLayout.findViewById(R.id.quiz_btn12);
        quizBtn22 = linearLayout.findViewById(R.id.quiz_btn22);
        quizBtn13 = linearLayout.findViewById(R.id.quiz_btn13);
        quizBtn23 = linearLayout.findViewById(R.id.quiz_btn23);

        quizBtn23.setBackgroundColor(getResources().getColor(R.color.background_color));
        quizBtn22.setBackgroundColor(getResources().getColor(R.color.background_color));
        quizBtn21.setBackgroundColor(getResources().getColor(R.color.background_color));

        quizBtn11.setOnClickListener(this);
        quizBtn21.setOnClickListener(this);
        quizBtn12.setOnClickListener(this);
        quizBtn22.setOnClickListener(this);
        quizBtn13.setOnClickListener(this);
        quizBtn23.setOnClickListener(this);

        editText= linearLayout.findViewById(R.id.quiz_count);
        editText.setText("10");
        editText.setGravity(Gravity.CENTER);

        quizFab = (FloatingActionButton) linearLayout.findViewById(R.id.quiz_fab);
        quizFab.setOnClickListener(this);

        return linearLayout;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button button;

    public QuizTab() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlacesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizTab newInstance(String param1, String param2) {
        QuizTab fragment = new QuizTab();
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
    public void onClick(View v) {
        if (v == quizFab)  {
            Intent intent = new Intent(v.getContext(), option1 ? MainVocaQuizOX.class : MainVocaQuiz.class);
            int quizCount = 0;
            try {
                quizCount = Integer.parseInt(editText.getText().toString());
            } catch (NumberFormatException e){
                Toast toast = Toast.makeText(getContext(),"숫자만 입력해주세요",Toast.LENGTH_LONG);
                return;
            }

            if(quizCount == 0)
                quizCount = 10;

            intent.putExtra("option1",option1);
            intent.putExtra("option2",option2);
            intent.putExtra("option3",option3);
            intent.putExtra("문제 수", quizCount);

            startActivity(intent);

        } else {
            v.setBackgroundColor(getResources().getColor(R.color.primary_color));

            if (v == quizBtn11)  {
                quizBtn21.setBackgroundColor(getResources().getColor(R.color.background_color));
                option1 = true;
            }
            if (v == quizBtn21)  {
                quizBtn11.setBackgroundColor(getResources().getColor(R.color.background_color));
                option1 = false;
            }
            if (v == quizBtn12)  {
                quizBtn22.setBackgroundColor(getResources().getColor(R.color.background_color));
                option2 = true;
            }
            if (v == quizBtn22)  {
                quizBtn12.setBackgroundColor(getResources().getColor(R.color.background_color));
                option2 = false;
            }
            if (v == quizBtn13)  {
                quizBtn23.setBackgroundColor(getResources().getColor(R.color.background_color));
                option3 = true;
            }
            if (v == quizBtn23)  {
                quizBtn13.setBackgroundColor(getResources().getColor(R.color.background_color));
                option3 = false;
            }
        }
    }
}
