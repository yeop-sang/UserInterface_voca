package com.example.voca.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.voca.R;
import com.example.voca.Voca.Voca;
import com.example.voca.Voca.VocaViewModel;

import java.util.Locale;

import static android.speech.tts.TextToSpeech.ERROR;

public class ListViewDialog extends AppCompatActivity implements View.OnClickListener {

    TextView wordText;
    TextView meanText;

    Button learning;
    ImageButton ttsBtn;

    TextToSpeech tts;
    Voca current;

    VocaViewModel vocaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_dialog);

        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        vocaViewModel = new ViewModelProvider(this).get(VocaViewModel.class);

        int width = (int) (display.getWidth() * 0.9); //Display 사이즈의 90% 각자 원하는 사이즈로 설정하여 사용
        int height = (int) (display.getHeight() * 0.7);  //Display 사이즈의 90% 각자 원하는 사이즈로 설정하여 사용

        getWindow().getAttributes().width = width;
        getWindow().getAttributes().height = height;

        wordText = findViewById(R.id.list_dialog_word);
        meanText = findViewById(R.id.list_dialog_mean);

        ttsBtn = findViewById(R.id.list_dialog_tts);
        ttsBtn.setOnClickListener(this);
        learning = findViewById(R.id.list_dialog_learning);
        learning.setOnClickListener(this);


        Intent intent = getIntent();
        current = (Voca) intent.getParcelableExtra("Voca_Data");

        wordText.setText(current.word);
        meanText.setText(current.mean);

        if (current.learned) {
            learning.setText("학습 미완료");
        }

        getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status != ERROR) {
                    // 언어를 선택한다.
                    tts.setLanguage(Locale.ENGLISH);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(v==ttsBtn) {
            tts.speak(current.word,TextToSpeech.QUEUE_FLUSH,null);
        }
        else if (v==learning) {
            if (current.learned) {
                current.learned = false;
            }
            else {
                current.learned = true;
            }

            finish();

            vocaViewModel.update(current);
        }
    }
}