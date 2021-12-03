package com.example.voca.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TabHost;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.voca.R;
import com.example.voca.Voca.Voca;
import com.example.voca.Voca.VocaViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ListTab extends Fragment implements View.OnClickListener {

    FloatingActionButton listFab;
    FragmentManager manager; //androidx.fragment.app.FragmentManager

    Fragment listTab;
    Fragment cardTab;
    Fragment quizTab;
    DialogFragment listDialog;

    ListView learningWord;
    ListView learnedWord;

    VocaViewModel vocaViewModel;
    List<Voca> vocaLearnedList = new ArrayList<Voca>();
    List<Voca> vocaNotLearnedList = new ArrayList<Voca>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        listDialog = new ListDialog();
        manager = getFragmentManager();

        VocaViewModel vocaViewModel = new ViewModelProvider(this).get(VocaViewModel.class);
        vocaViewModel.getLearnedVocas().observe(getViewLifecycleOwner(), vocas -> {
            vocaLearnedList.clear();
            vocaLearnedList.addAll(vocas);
        });

        vocaViewModel.getNotLearnedVocas().observe(getViewLifecycleOwner(), vocas -> {
            vocaNotLearnedList.clear();
            vocaNotLearnedList.addAll(vocas);
        });

        TabHost tabHost = (TabHost) inflater.inflate(R.layout.list_tab,container,false);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("tab1");
        spec.setIndicator("학습 중");
        spec.setContent(R.id.list_tab_learned);
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("tab2");
        spec.setIndicator("학습 완료");
        spec.setContent(R.id.list_tab_not_learned);
        tabHost.addTab(spec);

        listFab = (FloatingActionButton) tabHost.findViewById(R.id.list_fab);
        listFab.setOnClickListener(this);

        return tabHost;
    }

    @Override
    public void onClick (View view) {
        if (view == listFab)  {
            listDialog.show(manager,null);
        }
    }
}

