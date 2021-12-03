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
            "CASE WHEN :desc = 0 THEN :column END ASC," +
            "CASE WHEN :desc = 1 THEN :column END DESC")
    LiveData<List<Voca>> getVocas(String column, boolean learned, boolean desc);

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
