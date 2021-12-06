package com.example.voca.Voca;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class VocaRepository {
    private VocaDao vocaDao;
    private LiveData<List<Voca>> vocas;

    VocaRepository(Application application) {
        VocaDatabase db = VocaDatabase.getDatabase(application);
        vocaDao = db.vocaDao();
        vocas = vocaDao.getAllVocas();
    }

    public LiveData<List<Voca>> getVocas() {
        return vocas;
    }

    public LiveData<List<Voca>> getVocas(String column, boolean learned, boolean descending) {
        vocas = convertedVocaOrdering(column, learned, descending);
        return vocas;
    }

    private LiveData<List<Voca>> convertedVocaOrdering(String column, boolean learned, boolean descending) {
        if(column.equals("word")) {
            return vocaDao.getVocasAlphabetized(learned, descending);
        } else if(column.equals("mean")) {
            return vocaDao.getVocasHangulized(learned, descending);
        } else if(column.equals("id")) {
            return vocaDao.getVocasIdized(learned, descending);
        }
        return vocas;
    }

    public void insert(Voca voca) {
        VocaDatabase.databaseWriteExecutor.execute(() -> {
            vocaDao.insert(voca);
        });
    }

    public void delete(Voca voca) {
        VocaDatabase.databaseWriteExecutor.execute(() -> {
            vocaDao.deleteVoca(voca);
        });
    }
}
