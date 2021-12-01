package com.example.voca.Voca;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class VocaRepository {

    private VocaDao vocaDao;
    private LiveData<List<Voca>> allVocas;

    VocaRepository(Application application) {
        VocaDatabase db = VocaDatabase.getDatabase(application);
        vocaDao = db.vocaDao();
        allVocas = vocaDao.getASCVocas();
    }

    LiveData<List<Voca>> getAllVocas() {
        return allVocas;
    }

    public void insert(Voca voca) {
        VocaDatabase.databaseWriteExecutor.execute(() -> {
            vocaDao.insert(voca);
        });
    }
}
