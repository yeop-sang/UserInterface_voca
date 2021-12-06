package com.example.voca.Voca;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "voca_table", indices = @Index(value = {"word"}, unique = true))
public class Voca {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int id;

    @NonNull
    @ColumnInfo(name = "word")
    public String word;

    @NonNull
    @ColumnInfo(name = "mean")
    public String mean;
    
    @NonNull
    @ColumnInfo(name = "learned")
    public Boolean learned;

    public Voca(@NonNull String word, @NonNull String mean) {
        this.word = word;
        this.mean = mean;
        this.learned = false;
    }
}
