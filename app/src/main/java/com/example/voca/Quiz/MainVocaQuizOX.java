package com.example.voca.Quiz;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.voca.R;
import com.example.voca.Voca.Voca;
import com.example.voca.Voca.VocaViewModel;

import java.util.Random;

public class MainVocaQuizOX extends AppCompatActivity implements View.OnClickListener{

    private TextView textView;
    private TextView quizIndex;
    private Button Btn1;
    private Button Btn2;
    private Button BtnR;
    private int cnt = 1;
    private int correct = 0;
    private int quiz_cnt;
    private boolean learned;
    private int timer;
    private int answer;
    private int answerID;
    int[] arrIncorrect;
    int arrIncorrectSize = 0;
    Button[] arrButton = new Button[2];
    Voca[] arr;
    VocaViewModel vocaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_voca_quiz_ox);

        Btn1 = findViewById(R.id.B_1);
        Btn2 = findViewById(R.id.B_2);
        BtnR = findViewById(R.id.B_R);
        arrButton[0] = Btn1;
        arrButton[1] = Btn2;
        Btn1.setOnClickListener(this);
        Btn2.setOnClickListener(this);
        BtnR.setOnClickListener(this);

        textView = findViewById(R.id.quiz_text);
        quizIndex = findViewById(R.id.quiz_index);

        Intent intent = getIntent();
        quiz_cnt = intent.getIntExtra("문제 수",10);
        arrIncorrect = new int[quiz_cnt];
        quizIndex.setText(cnt+" / "+quiz_cnt);
        learned = !intent.getBooleanExtra("option2", false);

        vocaViewModel = new ViewModelProvider(this).get(VocaViewModel.class);

        vocaViewModel.getVocas("word", learned, "DESC").observe(this, vocas -> {
            arr = new Voca[vocas.size()];
            vocas.toArray(arr);
            setQuiz();
        });
    }

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
            Btn1.setSelected(true);
            if(view == arrButton[answer]) {
                correct++;
                timer = 750;
                textView.setText("정답!");
            }else {
                timer = 1000;
                arrIncorrect[arrIncorrectSize++] = answerID;
                textView.setText("오답!");
            }

            Random random = new Random();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (cnt <= quiz_cnt) {

                        Btn1.setEnabled(true);
                        Btn2.setEnabled(true);

                        setQuiz();
                        quizIndex.setText(cnt+" / "+quiz_cnt);
                    } else {
                        Btn1.setVisibility(View.GONE);
                        Btn2.setVisibility(View.GONE);
                        quizIndex.setVisibility(View.GONE);
                        textView.setText("퀴즈 종료");
                        BtnR.setVisibility(View.VISIBLE);
                    }
                }
            }, timer);
        }
    }
    @SuppressLint("SetTextI18n")
    private void setQuiz() {
        if(this.arr.length < 4)
            return;
        Random random = new Random();
        int mode = random.nextInt() % 2;
        int answerIndex = Math.abs(random.nextInt() % arr.length);
        int fakeAnswerIndex = Math.abs(random.nextInt() % arr.length);
        while(answerIndex == fakeAnswerIndex)
            fakeAnswerIndex = Math.abs(random.nextInt() % arr.length);
        answer = random.nextInt(2);     //answer이 0이면 X가 정답, 1이면 O가 정답
        answerID = arr[answerIndex].id;
        if (mode == 0) {      //단어가 보이고 뜻을 맞추는 경우
            if(answer == 1)
                textView.setText(arr[answerIndex].word+"\n의 뜻은\n"+arr[answerIndex].mean);
            else
                textView.setText(arr[answerIndex].word+"\n의 뜻은\n"+arr[fakeAnswerIndex].mean);
        } else {              //뜻이 보이고 단어를 맞추는 경우
            if(answer == 1)
                textView.setText(arr[answerIndex].mean+"\n은(는) 영어로\n"+arr[answerIndex].word);
            else
                textView.setText(arr[answerIndex].mean+"\n은(는) 영어로\n"+arr[fakeAnswerIndex].word);
        }
    }
}
