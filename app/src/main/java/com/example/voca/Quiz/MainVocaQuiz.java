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
import com.example.voca.Voca.Voca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    Voca[] arr = new Voca[7];
    Button[] arrButton = new Button[4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        arr[0] = new Voca("Game", "게임");
        arr[1] = new Voca("Dog", "강아지");
        arr[2] = new Voca("Cat", "고양이");
        arr[3] = new Voca("Food", "음식");
        arr[4] = new Voca("World", "세계");
        arr[5] = new Voca("People", "사람");
        arr[6] = new Voca("dish", "접시");
        //arr[7] = new Voca("wind", "바람");
        Btn1 = findViewById(R.id.B_1);
        Btn2 = findViewById(R.id.B_2);
        Btn3 = findViewById(R.id.B_3);
        Btn4 = findViewById(R.id.B_4);
        BtnR = findViewById(R.id.B_R);
        arrButton[0] = Btn1;
        arrButton[1] = Btn2;
        arrButton[2] = Btn3;
        arrButton[3] = Btn4;
        Btn1.setOnClickListener(this);
        Btn2.setOnClickListener(this);
        Btn3.setOnClickListener(this);
        Btn4.setOnClickListener(this);
        BtnR.setOnClickListener(this);

        textView = findViewById(R.id.quiz_text);
        setQuiz();

        Intent intent = getIntent();
        quiz_cnt = intent.getIntExtra("문제 수",10);
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

                        setQuiz();
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
    List<Voca> tmpList;
    private void setQuiz() {
        Random random = new Random();
        int mode = random.nextInt() % 2;
        int rem = 4 - (arr.length % 4);
        tmpList = new ArrayList<>();
        for (int i = 0; i < rem; i++) {
            tmpList.add(arr[arr.length / 4 * i + random.nextInt(arr.length / 4)]);
        }
        for (int i = rem; i < 4; i++) {
            tmpList.add(arr[(arr.length / 4) + ((4 + arr.length) / 4) * (i - 1) + random.nextInt((arr.length + 4) / 4)]);
        }
        Collections.shuffle(tmpList);
        if (mode == 0) {      //단어가 보이고 뜻을 맞추는 경우
            textView.setText(tmpList.get(random.nextInt(4)).word);
            for (int i = 0; i < 4; i++)
                arrButton[i].setText(tmpList.get(i).mean);
        } else {              //뜻이 보이고 단어를 맞추는 경우
            textView.setText(tmpList.get(random.nextInt(4)).mean);
            for (int i = 0; i < 4; i++)
                arrButton[i].setText(tmpList.get(i).word);
        }
    }
}
