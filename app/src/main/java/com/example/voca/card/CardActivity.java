package com.example.voca.card;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.voca.R;

public class CardActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView Q;
    private TextView A;
    private TextView I;
    ImageButton ttsButton;
    Button visButton;
    ImageButton arrowLeft;
    ImageButton arrowRight;
    int cnt = 1;
    int max = 100;
    float initX;
    @SuppressLint({"SetTextI18n", "WrongViewCast"})
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
        arrowLeft = findViewById(R.id.arrow_left);
        arrowRight = findViewById(R.id.arrow_right);
        arrowLeft.setOnClickListener(this);
        arrowRight.setOnClickListener(this);
        Q.setText("subtitle");
        A.setText("부제");
        I.setText(cnt +" / "+max);
    }

    @Override
    public void onClick(View view) {
        if(view == visButton) {
            if (visButton.getAlpha() == 0)
                visButton.setAlpha((float) 1.0);
            else
                visButton.setAlpha(0);
        }else if(view == arrowLeft)
            moveLeft();
        else if(view == arrowRight)
            moveRight();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            initX = event.getRawX();
        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            float diffX = initX - event.getRawX();
            if (diffX > 30) {
                moveRight();
            } else if (diffX < -30) {
                moveLeft();
            }
        }
        return true;
    }
    public void moveLeft(){
        if(cnt == 1) {
            cnt = max;
            I.setText(max + " / " + max);
        }else
            I.setText(--cnt +" / "+max);
        visButton.setAlpha((float) 1.0);
    }
    public void moveRight(){
        if(cnt == max) {
            cnt = 1;
            I.setText(1 + " / " + max);
        }else
            I.setText(++cnt +" / "+max);
        visButton.setAlpha((float) 1.0);
    }
}