package com.example.voca.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.voca.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListTab extends Fragment implements View.OnClickListener {

    FloatingActionButton listFab;
    FragmentManager manager; //androidx.fragment.app.FragmentManager

    Fragment listTab;
    Fragment cardTab;
    Fragment quizTab;
    DialogFragment listDialog;

    ListView learningWord;
    ListView learnedWord;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        listDialog = new ListDialog();
        manager = getFragmentManager();

        TabHost tabHost = (TabHost) inflater.inflate(R.layout.list_tab,container,false);
        tabHost.setup();

        //TODO TabWidget 글자 색 바꾸기 / 현재 검은 색

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

