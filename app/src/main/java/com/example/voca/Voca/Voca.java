package com.example.voca.Voca;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "voca_table", indices = @Index(value = {"word"}, unique = true))
public class Voca implements Parcelable {

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

    protected Voca(Parcel in) {
        id = in.readInt();
        word = in.readString();
        mean = in.readString();
        byte tmpLearned = in.readByte();
        learned = tmpLearned == 0 ? null : tmpLearned == 1;
    }

    public static final Creator<Voca> CREATOR = new Creator<Voca>() {
        @Override
        public Voca createFromParcel(Parcel in) {
            return new Voca(in);
        }

        @Override
        public Voca[] newArray(int size) {
            return new Voca[size];
        }
    };

    public void learningEnd() {
        learned = true;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(word);
        dest.writeString(mean);
        dest.writeByte((byte) (learned == null ? 0 : learned ? 1 : 2));
    }
}
