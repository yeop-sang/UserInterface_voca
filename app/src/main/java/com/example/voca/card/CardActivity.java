package com.example.voca.card;

import static android.net.wifi.p2p.WifiP2pManager.ERROR;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.voca.R;
import com.example.voca.Voca.Voca;
import com.example.voca.Voca.VocaViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CardActivity extends AppCompatActivity implements View.OnClickListener {

    private TextToSpeech tts;

    private TextView question;
    private TextView answer;
    private TextView index;
    private ImageButton ttsButton;
    private Button visButton;
    private ImageButton arrowLeft;
    private ImageButton arrowRight;
    private int cnt = 0;
    private int max = 0;
    private float initX;
    private boolean loadFlag = false;

    private boolean wordOrMean;
    private boolean notLearned;

    private FloatingActionButton learnEndBtn;
    private FloatingActionButton learnNotEndBtn;

    VocaViewModel vocaViewModel;
    List<Voca> vocas = new ArrayList<Voca>();

    @SuppressLint({"SetTextI18n", "WrongViewCast"})
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);

        vocaViewModel = new ViewModelProvider(this).get(VocaViewModel.class);

        learnEndBtn = findViewById(R.id.card_learn_end);
        learnNotEndBtn = findViewById(R.id.card_not_learn_end);
        learnEndBtn.setOnClickListener(this);
        learnNotEndBtn.setOnClickListener(this);

        question = findViewById(R.id.question);
        answer = findViewById(R.id.answer);
        index = findViewById(R.id.card_index);

        ttsButton = findViewById(R.id.tts_button);
        visButton = findViewById(R.id.vis_button);
        visButton.setOnClickListener(this);
        arrowLeft = findViewById(R.id.arrow_left);
        arrowRight = findViewById(R.id.arrow_right);
        arrowLeft.setOnClickListener(this);
        arrowRight.setOnClickListener(this);

        Intent intent = getIntent();

        wordOrMean = intent.getBooleanExtra("option1", true);
        notLearned = intent.getBooleanExtra("option2", true);

        vocaViewModel.getVocas("word", !notLearned, "DESC").observe(this, vocas -> {
            Log.d("CardActivity", "변경감지");
            this.vocas.clear();
            this.vocas.addAll(vocas);
            max = vocas.size() - 1;
            setText(cnt);
            if(loadFlag)
                if (cnt == 0) {
                    if(notLearned) {
                        Toast toast = Toast.makeText(getApplicationContext(),"list에 단어가 없어 학습완료 Card를 실행합니다.",Toast.LENGTH_SHORT);
                        toast.show();
                    } else {
                        Toast toast = Toast.makeText(getApplicationContext(),"list에 단어가 없어 학습중 Card를 실행합니다.",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    notLearned = !notLearned;
                }
            else loadFlag = true;
        });


        // TTS를 생성하고 OnInitListener로 초기화 한다.
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != ERROR) {
                    // 언어를 선택한다.
                    tts.setLanguage(Locale.ENGLISH);
                }
            }
        });

        if (vocas.size() > 0) {
            setText(cnt);
        } else {
            question.setText("loading");
            answer.setText("loading");
            index.setText("loading");
        }


        ttsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // editText에 있는 문장을 읽는다.
                tts.speak(question.getText(), TextToSpeech.QUEUE_FLUSH, null, "CARD_ID");
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view == visButton) {
            if (visButton.getAlpha() == 0)
                visButton.setAlpha((float) 1.0);
            else
                visButton.setAlpha(0);
        } else if (view == arrowLeft)
            moveLeft();
        else if (view == arrowRight)
            moveRight();
        else if (view == learnEndBtn) {
            vocas.get(cnt).learned = true;
        } else if (view == learnNotEndBtn) {
            vocas.get(cnt).learned = false;
        }

        vocaViewModel.update(vocas.get(cnt));
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

    public void moveLeft() {
        if (cnt == 0) {
            cnt = max;
            setText(cnt);
        } else
            cnt -= 1;
        setText(cnt);
        visButton.setAlpha((float) 1.0);
    }

    public void moveRight() {
        if (cnt == max) {
            cnt = 0;
            setText(cnt);
        } else {
            cnt += 1;
            setText(cnt);
        }
        visButton.setAlpha((float) 1.0);
    }

    private void setText(int cnt) {
        if (wordOrMean) {
            question.setText(vocas.get(cnt).word);
            answer.setText(vocas.get(cnt).mean);
            index.setText((cnt + 1) + " / " + (max + 1));
        } else {
            question.setText(vocas.get(cnt).mean);
            answer.setText(vocas.get(cnt).word);
            index.setText((cnt + 1) + " / " + (max + 1));
        }
    }
}