package com.example.voca.Quiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.voca.R;
import com.example.voca.Voca.Voca;
import com.example.voca.Voca.VocaViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainVocaQuiz extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;
    private TextView quizIndex;
    private TableLayout tableLayout;
    private Button Btn1;
    private Button Btn2;
    private Button Btn3;
    private Button Btn4;
    private Button BtnR;
    private int cnt = 1;
    private int correct = 0;
    private int quiz_cnt;
    private boolean learned;
    private boolean mode;
    private int timer;
    private int answer;
    private int answerID;
    private String answerStr = "";
    int[] arrIncorrect;
    int arrIncorrectSize = 0;
    Button[] arrButton = new Button[4];
    Voca[] arr;
    VocaViewModel vocaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

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
        quizIndex = findViewById(R.id.quiz_index);

        Intent intent = getIntent();
        quiz_cnt = intent.getIntExtra("문제 수",10);
        arrIncorrect = new int[quiz_cnt];
        quizIndex.setText(cnt+" / "+quiz_cnt);
        learned = !intent.getBooleanExtra("option2", false);
        mode = intent.getBooleanExtra("option3", false);

        vocaViewModel = new ViewModelProvider(this).get(VocaViewModel.class);

        vocaViewModel.getVocas().observe(this, vocas -> {
            arr = new Voca[vocas.size()];
            vocas.toArray(arr);
            setQuiz();
        });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        if (view == BtnR) {
            Intent intent = new Intent(this, QuizResult.class);
            intent.putExtra("correct", correct);
            intent.putExtra("total", cnt - 1);
            intent.putExtra("incorrect", arrIncorrect);
            startActivity(intent);
        } else {
            cnt++;
            Btn1.setEnabled(false);
            Btn2.setEnabled(false);
            Btn3.setEnabled(false);
            Btn4.setEnabled(false);
            if(view == arrButton[answer]) {
                correct++;
                timer = 750;
                textView.setText("정답!");
            }else {
                timer = 1500;
                arrIncorrect[arrIncorrectSize++] = answerID;
                textView.setText("오답!\n\n" + answerStr);
            }

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
                        quizIndex.setText(cnt+" / "+quiz_cnt);
                    } else {
                        Btn1.setVisibility(View.GONE);
                        Btn2.setVisibility(View.GONE);
                        Btn3.setVisibility(View.GONE);
                        Btn4.setVisibility(View.GONE);
                        quizIndex.setVisibility(View.GONE);
                        textView.setText("퀴즈 종료");
                        BtnR.setVisibility(View.VISIBLE);
                    }
                }
            }, timer);
        }
    }
    List<Voca> tmpList;
    private void setQuiz() {
        if(this.arr.length < 4)
            return;
        Random random = new Random();
        int rem = 4 - (arr.length % 4);
        tmpList = new ArrayList<>();
        for (int i = 0; i < rem; i++) {
            tmpList.add(arr[arr.length / 4 * i + random.nextInt(arr.length / 4)]);
        }
        for (int i = rem; i < 4; i++) {
            tmpList.add(arr[((arr.length / 4) * rem + ((4 + arr.length) / 4) * (i - rem) + random.nextInt((arr.length + 4) / 4))]);
        }
        Collections.shuffle(tmpList);
        answer = random.nextInt(4);
        if (mode) {      //단어가 보이고 뜻을 맞추는 경우
            textView.setText(tmpList.get(answer).word);
            answerID = tmpList.get(answer).id;
            answerStr = tmpList.get(answer).mean;
            for (int i = 0; i < 4; i++)
                arrButton[i].setText(tmpList.get(i).mean);
        } else {              //뜻이 보이고 단어를 맞추는 경우
            textView.setText(tmpList.get(answer).mean);
            answerID = tmpList.get(answer).id;
            answerStr = tmpList.get(answer).word;
            for (int i = 0; i < 4; i++)
                arrButton[i].setText(tmpList.get(i).word);
        }
    }
}
