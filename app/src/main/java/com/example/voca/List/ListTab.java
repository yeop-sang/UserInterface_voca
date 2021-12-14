package com.example.voca.List;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
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

    TabHost tabHost;

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

    ImageButton wordOrdering;
    ImageButton meanOrdering;
    ImageButton idOrdering;
    final VocaListAdapter learnedVocaAdapter = new VocaListAdapter(new VocaListAdapter.VocaDiff(),getContext());
    final VocaListAdapter notLearnedVocaAdapter = new VocaListAdapter(new VocaListAdapter.VocaDiff(),getContext());

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView textView;

    public ListTab() {
        // Required empty public constructor
    }

    public static ListTab newInstance(String param1, String param2) {
        // 객체를 만들어주었던 newInstance 함수
        ListTab fragment = new ListTab();   // 객체 생성
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);    //Bundle을 넣어주는 setArguments 함수
        //아직 생명주기에 안들어갔음.
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        listDialog = new ListAddDialog();
        manager = getFragmentManager();

        tabHost = (TabHost) inflater.inflate(R.layout.list_tab, container, false);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("tab1");
        spec.setIndicator("학습 중");
        spec.setContent(R.id.list_tab_not_learned);
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("tab2");
        spec.setIndicator("학습 완료");
        spec.setContent(R.id.list_tab_learned);
        tabHost.addTab(spec);

        RecyclerView recyclerLearnedView = tabHost.findViewById(R.id.list_tab_learned);
        RecyclerView recyclerNotLearnedView = tabHost.findViewById(R.id.list_tab_not_learned);


        recyclerLearnedView.setAdapter(learnedVocaAdapter);
        recyclerNotLearnedView.setAdapter(notLearnedVocaAdapter);

        recyclerLearnedView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerNotLearnedView.setLayoutManager(new LinearLayoutManager(getContext()));

        vocaViewModel = new ViewModelProvider(this).get(VocaViewModel.class);
        vocaViewModel.getLearnedVocas().observe(getViewLifecycleOwner(), vocas -> {
            Log.d("LearnedVoca", "변경감지");
            vocaLearnedList.clear();
            vocaLearnedList.addAll(vocas);
            learnedVocaAdapter.submitList(vocas);
        });

        vocaViewModel.getNotLearnedVocas().observe(getViewLifecycleOwner(), vocas -> {
            Log.d("NotLearnedVoca", "변경감지");
            vocaNotLearnedList.clear();
            vocaNotLearnedList.addAll(vocas);
            notLearnedVocaAdapter.submitList(vocas);
        });

        listFab = (FloatingActionButton) tabHost.findViewById(R.id.list_fab);
        listFab.setOnClickListener(this);

        wordOrdering = tabHost.findViewById(R.id.word_ordering);
        meanOrdering = tabHost.findViewById(R.id.mean_ordering);
        idOrdering = tabHost.findViewById(R.id.id_ordering);

        wordOrdering.setOnClickListener(this);
        meanOrdering.setOnClickListener(this);
        idOrdering.setOnClickListener(this);

        return tabHost;
    }
    boolean wordIsSorted = false;
    boolean meanIsSorted = false;
    boolean idIsSorted = false;
    String varDesc = "";
    @Override
    public void onClick(View view) {
        boolean learnedTab = this.tabHost.getCurrentTab() == 1;
        LiveData<List<Voca>> temp;
        if (view == listFab) {
            Log.d("tabhost", "ListFab Click");
            listDialog.show(manager, null);
        } else if (view == wordOrdering) {
            Log.d("tabhost", "WordOrdering");
            if(wordIsSorted)
                varDesc = "DESC";
            else
                varDesc = "ASEC";
            vocaViewModel.getVocas("word", learnedTab, varDesc).observe(getViewLifecycleOwner(), vocas -> {
                if (learnedTab){
                    vocaLearnedList.clear();
                    vocaLearnedList.addAll(vocas);
                    learnedVocaAdapter.submitList(vocas);
                }else {
                    vocaNotLearnedList.clear();
                    vocaNotLearnedList.addAll(vocas);
                    notLearnedVocaAdapter.submitList(vocas);
                }
            });
            wordIsSorted = !wordIsSorted;
        } else if (view == meanOrdering) {
            Log.d("tabhost", "meanOrdering");
            if(meanIsSorted)
                varDesc = "DESC";
            else
                varDesc = "ASEC";
            vocaViewModel.getVocas("mean", learnedTab, varDesc).observe(getViewLifecycleOwner(), vocas -> {
                if (learnedTab){
                    vocaLearnedList.clear();
                    vocaLearnedList.addAll(vocas);
                    learnedVocaAdapter.submitList(vocas);
                }else {
                    vocaNotLearnedList.clear();
                    vocaNotLearnedList.addAll(vocas);
                    notLearnedVocaAdapter.submitList(vocas);
                }
            });
            meanIsSorted = !meanIsSorted;
        } else if (view == idOrdering) {
            Log.d("tabhost", "idOrdering");
            if(idIsSorted)
                varDesc = "DESC";
            else
                varDesc = "ASEC";
            vocaViewModel.getVocas("id", learnedTab, varDesc).observe(getViewLifecycleOwner(), vocas -> {
                if (learnedTab){
                    vocaLearnedList.clear();
                    vocaLearnedList.addAll(vocas);
                    learnedVocaAdapter.submitList(vocas);
                }else {
                    vocaNotLearnedList.clear();
                    vocaNotLearnedList.addAll(vocas);
                    notLearnedVocaAdapter.submitList(vocas);
                }
            });
            idIsSorted = !idIsSorted;
        }
        Log.d("tabhost", "" + learnedTab);
    }
}

