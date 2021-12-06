package com.example.voca.Quiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.voca.R;

import java.util.Random;

public class MainVocaQuiz extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;
    private TableLayout tableLayout;
    private Button Btn1;
    private Button Btn2;
    private Button Btn3;
    private Button Btn4;
    private Button BtnR;
    private int cnt = 1;
    private int correct = 0;
    private int quiz_cnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Btn1 = findViewById(R.id.B_1);
        Btn2 = findViewById(R.id.B_2);
        Btn3 = findViewById(R.id.B_3);
        Btn4 = findViewById(R.id.B_4);
        BtnR = findViewById(R.id.B_R);

        Btn1.setOnClickListener(this);
        Btn2.setOnClickListener(this);
        Btn3.setOnClickListener(this);
        Btn4.setOnClickListener(this);
        BtnR.setOnClickListener(this);

        textView = findViewById(R.id.quiz_text);
        textView.setText("문제 "+cnt);

        Intent intent = getIntent();
        quiz_cnt = intent.getIntExtra("문제 수",1);
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
            Btn3.setEnabled(false);
            Btn4.setEnabled(false);
            if(view == Btn1)
                correct++;

            Random random = new Random();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (cnt <= quiz_cnt) {

                        Btn1.setEnabled(true);
                        Btn2.setEnabled(true);
                        Btn3.setEnabled(true);
                        Btn4.setEnabled(true);

                        Btn1.setText("" + random.nextInt(100));
                        Btn2.setText("" + random.nextInt(100));
                        Btn3.setText("" + random.nextInt(100));
                        Btn4.setText("" + random.nextInt(100));
                        textView.setText("문제 " + cnt);
                    } else {
                        Btn1.setVisibility(View.GONE);
                        Btn2.setVisibility(View.GONE);
                        Btn3.setVisibility(View.GONE);
                        Btn4.setVisibility(View.GONE);
                        textView.setText("퀴즈 종료");
                        BtnR.setVisibility(View.VISIBLE);
                    }
                }
            }, 750);
        }
    }
}
