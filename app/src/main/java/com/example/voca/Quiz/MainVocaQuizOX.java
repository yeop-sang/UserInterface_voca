package com.example.voca.Quiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.voca.R;

import java.util.Random;

public class MainVocaQuizOX extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;
    private Button Btn1;
    private Button Btn2;
    private Button BtnR;
    private int cnt = 1;
    private int correct = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_voca_quiz_ox);

        Btn1 = findViewById(R.id.B_1);
        Btn2 = findViewById(R.id.B_2);
        BtnR = findViewById(R.id.B_R);

        Btn1.setOnClickListener(this);
        Btn2.setOnClickListener(this);
        BtnR.setOnClickListener(this);

        textView = findViewById(R.id.quiz_text);
        textView.setText("문제 "+cnt);
    }

    @Override
    public void onClick(View view) {
        if (view == BtnR) {
            Intent intent = new Intent(this, QuizResult.class);
            intent.putExtra("correct", correct);
            intent.putExtra("total", cnt - 1);
            startActivity(intent);
        } else {
            textView.setText("로딩 중...");
            cnt++;
            Btn1.setEnabled(false);
            Btn2.setEnabled(false);
            Btn1.setSelected(true);
            if(view == Btn1)
                correct++;

            Random random = new Random();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (cnt <= 10) {

                        Btn1.setEnabled(true);
                        Btn2.setEnabled(true);

                        textView.setText("문제 " + cnt);
                    } else {
                        Btn1.setVisibility(View.GONE);
                        Btn2.setVisibility(View.GONE);
                        textView.setText("퀴즈 종료");
                        BtnR.setVisibility(View.VISIBLE);
                    }
                    Btn1.setSelected(false);
                }
            }, 750);
        }
    }
}