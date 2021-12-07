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

    @Override
    public void onClick(View v) {
        if (v == quizFab)  {
            Intent intent = new Intent(v.getContext(),MainVocaQuiz.class);
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
