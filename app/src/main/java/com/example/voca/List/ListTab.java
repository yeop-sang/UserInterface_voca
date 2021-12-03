package com.example.voca.List;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voca.R;
import com.example.voca.Voca.Voca;
import com.example.voca.Voca.VocaListAdapter;
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
    List<Voca> vocaList = new ArrayList<Voca>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        listDialog = new ListDialog();
        manager = getFragmentManager();

        VocaViewModel vocaViewModel = new ViewModelProvider(this).get(VocaViewModel.class);
        vocaViewModel.getVocas().observe(this, vocas -> {
            vocaList.clear();
            vocaList.addAll(vocas);
        });

        TabHost tabHost = (TabHost) inflater.inflate(R.layout.list_tab,container,false);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("tab1");
        spec.setIndicator("학습 중");
        spec.setContent(R.id.list_tab1);
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("tab2");
        spec.setIndicator("학습 완료");
        spec.setContent(R.id.list_tab2);
        tabHost.addTab(spec);

        String[] tmpWords1 = {"A","B","C"};
        String[] tmpWords2 = {"a","b","c"};

        learningWord = (ListView) tabHost.findViewById(R.id.list_tab1);
        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,tmpWords1);
        learningWord.setAdapter(listViewAdapter);

        learnedWord = (ListView) tabHost.findViewById(R.id.list_tab2);
        listViewAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,tmpWords2);
        learnedWord.setAdapter(listViewAdapter);

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

