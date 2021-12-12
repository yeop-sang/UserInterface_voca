package com.example.voca.Quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

    @SuppressLint("SetTextI18n")
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
            finish();
        }
        else {
            //incorrect list Activity
            Intent intent = getIntent();
            int[] arr = intent.getIntArrayExtra("incorrect");
            Log.d("test", ""+arr[1]);
            //Intent intent = new Intent(this, QuizResultList);
            //intent.putExtra("incorrect", intent.getIntArrayExtra("incorrect"));
            //startActivity(intent);
        }
    }
}