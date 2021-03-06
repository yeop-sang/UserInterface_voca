package com.example.voca.Voca;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class VocaViewModel extends AndroidViewModel {
    private final VocaRepository repository;
    private LiveData<List<Voca>> vocas;
    private LiveData<List<Voca>> learnedVocas;
    private LiveData<List<Voca>> notLearnedVocas;

    public VocaViewModel(Application application) {
        super(application);
        repository = new VocaRepository(application);
        learnedVocas = repository.getVocas("word", true, false);
        notLearnedVocas = repository.getVocas("word", false, false);
        vocas = repository.getVocas();
    }

    public LiveData<List<Voca>> getLearnedVocas() {
        return learnedVocas;
    }

    public LiveData<List<Voca>> getNotLearnedVocas() {
        return notLearnedVocas;
    }

    public LiveData<List<Voca>> getVocas(String column, boolean learned, String desc) {
        boolean descValue = !desc.equals("DESC");
        if (learned) {
            learnedVocas = repository.getVocas(column, true, descValue);
            return learnedVocas;
        } else {
            notLearnedVocas = repository.getVocas(column, false, descValue);
            return notLearnedVocas;
        }
    }

    public LiveData<List<Voca>> getVocas() {
        return repository.getVocas();
    }

    public LiveData<List<Voca>> searchVocas(String search) {
        return repository.searchVocas(search);
    }

    public void update(Voca voca) { repository.update(voca); }

    public void insert(Voca voca) {
        repository.insert(voca);
    }

    public void vocaLearn(Voca voca) { }

    public void delete(Voca voca) {
        repository.delete(voca);
    }
}
