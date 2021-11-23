package com.example.voca.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.voca.MainActivity;
import com.example.voca.R;

public class QuizResult extends AppCompatActivity implements View.OnClickListener {

    private int correct;
    private int total;

    private Button quizExitBtn;
    private Button incorrectListBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        quizExitBtn = findViewById(R.id.quiz_exit);
        quizExitBtn.setOnClickListener(this);
        incorrectListBtn = findViewById(R.id.incorrect_list);
        incorrectListBtn.setOnClickListener(this);

        Intent intent = getIntent();
        correct = intent.getIntExtra("correct", 0);
        total = intent.getIntExtra("total", 0);

        TextView result = (TextView)findViewById(R.id.quiz_info);
        result.setText(correct+" / "+total);
    }

    @Override
    public void onClick(View v) {
        if (v==quizExitBtn) {
            Intent mainIntent= new Intent(v.getContext(), MainActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK); // 기존의 액티비티 삭제
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // 새로운 액티비티 생성
            startActivity(mainIntent);
        }
        else {
            //incorrect list Activity
        }
    }
}