package com.example.voca.Voca;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class VocaViewModel extends AndroidViewModel {
    private final VocaRepository repository;
    private final LiveData<List<Voca>> allVocas;

    public VocaViewModel(Application application) {
        super(application);
        repository = new VocaRepository(application);
        allVocas = repository.getAllVocas();
    }

    public LiveData<List<Voca>> getAllVocas() {
        return allVocas;
    }

    public void insert(Voca voca) {
        repository.insert(voca);
    }
}
