package com.example.voca.Voca;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class VocaViewModel extends AndroidViewModel {
    private final VocaRepository repository;
    private LiveData<List<Voca>> vocas;

    public VocaViewModel(Application application) {
        super(application);
        repository = new VocaRepository(application);
        vocas = repository.getVocas();
    }

    public LiveData<List<Voca>> getVocas() {
        return vocas;
    }

    public void requestVocas(String order, int learned, String desc) {
        boolean descValue = !desc.equals("DESC");
        repository.requestVocas(order, learned, descValue);
        vocas = repository.getVocas();
    }

    public void insert(Voca voca) {
        repository.insert(voca);
    }

    public void delete(Voca voca) {
        repository.delete(voca);
    }
}
