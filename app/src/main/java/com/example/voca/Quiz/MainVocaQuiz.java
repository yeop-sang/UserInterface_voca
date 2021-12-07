package com.example.voca.Quiz;

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
    Voca[] arr;
    Button[] arrButton = new Button[4];
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

        Intent intent = getIntent();
        quiz_cnt = intent.getIntExtra("문제 수",10);
        learned = intent.getBooleanExtra("learned", false);

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
        if(this.arr.length < 1)
            return;
        Random random = new Random();
        int mode = random.nextInt() % 2;
        int rem = 4 - (arr.length % 4);
        tmpList = new ArrayList<>();
        for (int i = 0; i < rem; i++) {
            tmpList.add(arr[arr.length / 4 * i + random.nextInt(arr.length / 4)]);
        }
        for (int i = rem; i < 4; i++) {
            tmpList.add(arr[((arr.length / 4) + ((4 + arr.length) / 4) * (i - 1) + random.nextInt((arr.length + 4) / 4)) - 1]);
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
