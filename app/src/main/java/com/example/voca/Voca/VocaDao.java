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

    @Query("SELECT * FROM voca_table WHERE learned = :learned ORDER BY " +
            "CASE WHEN :desc = 0 THEN word END ASC," +
            "CASE WHEN :desc = 1 THEN word END DESC")
    LiveData<List<Voca>> getVocasAlphabetized(boolean learned, boolean desc);

    @Query("SELECT * FROM voca_table WHERE learned = :learned ORDER BY " +
            "CASE WHEN :desc = 0 THEN mean END ASC," +
            "CASE WHEN :desc = 1 THEN mean END DESC")
    LiveData<List<Voca>> getVocasHangulized(boolean learned, boolean desc);

    @Query("SELECT * FROM voca_table WHERE learned = :learned ORDER BY " +
            "CASE WHEN :desc = 0 THEN id END ASC," +
            "CASE WHEN :desc = 1 THEN id END DESC")
    LiveData<List<Voca>> getVocasIdized(boolean learned, boolean desc);

    @Query("SELECT * FROM voca_table ORDER BY id")
    LiveData<List<Voca>> getAllVocas();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Voca voca);

    @Update
    void update(Voca voca);

    @Delete
    void deleteVoca(Voca voca);

    @Delete
    void deleteVocas(Voca... voca);

    @Query("DELETE FROM voca_table")
    void clearVocas();
}
