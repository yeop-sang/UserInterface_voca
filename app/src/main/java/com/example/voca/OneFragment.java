package com.example.voca;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;

import androidx.fragment.app.Fragment;

public class OneFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        TabHost tabHost = (TabHost) inflater.inflate(R.layout.fragment_one,container,false);
        tabHost.setup();

        TabHost.TabSpec spec = tabHost.newTabSpec("tab1");
        spec.setIndicator("tab1");
        spec.setContent(R.id.list_tab1);
        tabHost.addTab(spec);

        spec = tabHost.newTabSpec("tab2");
        spec.setIndicator("tab2");
        spec.setContent(R.id.list_tab2);
        tabHost.addTab(spec);

        return tabHost;
    }
}

