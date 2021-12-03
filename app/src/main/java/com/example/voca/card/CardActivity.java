package com.example.voca.card;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.voca.R;

public class CardActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView Q;
    private TextView A;
    private TextView I;
    ImageButton ttsButton;
    ImageButton visButton;
    int cnt = 1;
    int max = 100;
    float initX;
    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        Q = findViewById(R.id.question);
        A = findViewById(R.id.answer);
        I = findViewById(R.id.card_index);
        ttsButton = findViewById(R.id.tts_button);
        visButton = findViewById(R.id.vis_button);
        ttsButton.setOnClickListener(this);
        visButton.setOnClickListener(this);
        Q.setText("부제");
        A.setText("subtitle");
        I.setText(cnt +" / "+max);
    }

    @Override
    public void onClick(View view) {
        if(view == visButton)
            if(A.getVisibility() == View.VISIBLE)
                A.setVisibility(View.INVISIBLE);
            else
                A.setVisibility(View.VISIBLE);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            initX = event.getRawX();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            float diffX = initX - event.getRawX();
            if (diffX > 30) {
                if(cnt == max) {
                    cnt = 1;
                    I.setText(1 + " / " + max);
                }else
                    I.setText(++cnt +" / "+max);
            } else if (diffX < -30) {
                if(cnt == 1) {
                    cnt = max;
                    I.setText(max + " / " + max);
                }else
                    I.setText(--cnt +" / "+max);
            }
        }
        return true;
    }
}