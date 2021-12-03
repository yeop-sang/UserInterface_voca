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

    public LiveData<List<Voca>> getVocas(String order, boolean learned, boolean descending) {
        return vocaDao.getVocas(order, learned, descending);
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
