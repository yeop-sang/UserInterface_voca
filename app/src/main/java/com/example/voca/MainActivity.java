package com.example.voca;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1;
    private Button btn2;
    private Button btn3;

    private FragmentManager manager; //androidx.fragment.app.FragmentManager
    private ListTab oneFragment;
    private CardTab twoFragment;
    private QuizTab threeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.main_btn1);
        btn2 = findViewById(R.id.main_btn2);
        btn3 = findViewById(R.id.main_btn3);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

        manager = getSupportFragmentManager();
        oneFragment = new ListTab();
        twoFragment = new CardTab();
        threeFragment = new QuizTab();

//      FragmentTransaction tf=manager.beginTransaction();
//      tf.add(R.id.main_container, oneFragment);
//      tf.commit();

         FragmentTransaction tf=manager.beginTransaction();
         tf.add(R.id.main_container, oneFragment);
         tf.commit();

    }

    @Override
    public void onClick(View v) {
        if(v==btn1) {
            if(!oneFragment.isVisible()) {
                FragmentTransaction tf = manager.beginTransaction();
                tf.replace(R.id.main_container, oneFragment);
                //tf.addToBackStack(null);                 // 실행 스택 쌓음. 뒤로가기 누르면 뒤로 감

                tf.commit();
            }
        } else if (v==btn2) {
            if(!twoFragment.isVisible()) {
                FragmentTransaction tf = manager.beginTransaction();
                tf.replace(R.id.main_container, twoFragment);
                //tf.addToBackStack(null);                 // 실행 스택 쌓음. 뒤로가기 누르면 뒤로 감

                tf.commit();
            }
        } else if (v == btn3) {
            if(!threeFragment.isVisible()) {
                Log.d("kkang","111111111111");
                FragmentTransaction tf = manager.beginTransaction();
                tf.replace(R.id.main_container,threeFragment);
                //tf.addToBackStack(null);

                tf.commit();
            }
        }
    }
}