package com.example.voca.Voca;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface VocaDao {

    @Query("SELECT * FROM voca_table ORDER BY word ASC")
    LiveData<List<Voca>> getAlphabetizedVocas();

    @Query("SELECT * FROM voca_table ORDER BY word DESC")
    LiveData<List<Voca>> getDeAlphabetizedVocas();

    @Query("SELECT * FROM voca_table ORDER BY mean ASC")
    LiveData<List<Voca>> getHangulizedVocas();

    @Query("SELECT * FROM voca_table ORDER BY mean DESC")
    LiveData<List<Voca>> getDeHangulizedVocas();

    @Query("SELECT * FROM voca_table ORDER BY id ASC")
    LiveData<List<Voca>> getASCVocas();

    @Query("SELECT * FROM voca_table ORDER BY id DESC")
    LiveData<List<Voca>> getDESCVocas();

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Voca voca);

    @Update
    void update(Voca voca);

    @Delete
    void deleteVoca(Voca voca);

    @Delete
    void deleteVocas(Voca... voca);
}
