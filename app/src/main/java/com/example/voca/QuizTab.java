package com.example.voca;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class QuizTab extends Fragment implements View.OnClickListener{

    private FloatingActionButton quizFab;
    private LinearLayout linearLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        linearLayout = (LinearLayout) inflater.inflate(R.layout.quiz_tab,container,false);

        quizFab = (FloatingActionButton) linearLayout.findViewById(R.id.quiz_fab);
        quizFab.setOnClickListener(this);

        context = container.getContext();   // 임시 Toast 띄워주기 용
        return linearLayout;
    }

    @Override
    public void onClick(View v) {
        if (v == quizFab)  {
            // quizFab 클릭 시

            Toast toast = Toast.makeText(context,"quizFab이 클릭됨!",Toast.LENGTH_SHORT);
            toast.show();

        }
    }

    private Context context; // 임시 Toast 띄워주기 용

}
