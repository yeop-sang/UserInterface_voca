package com.example.voca.Voca;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@Database(entities = {Voca.class}, version = 1, exportSchema = false)
abstract public class VocaDatabase extends RoomDatabase {
    abstract VocaDao vocaDao();

    static final ArrayList<String> words
            = new ArrayList<String>(Arrays.asList("resume", "applicant", "requirement", "meet", "candidate", "confidence", "highly", "professional"));

    static final ArrayList<String> means
            = new ArrayList<String>(Arrays.asList("이력서", "지원자,신청자", "필요조건,요건", "만족시키다", "후보자,지원자", "확신,자신,신임", "매우,대단히", "전문적인,직업의,전문가"));

    private static volatile VocaDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static VocaDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (VocaDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            VocaDatabase.class, "voca_database")
                            .addCallback(VocaDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback VocaDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriteExecutor.execute(() -> {
                VocaDao dao = INSTANCE.vocaDao();

                //TODO: Remove Danger code!
                dao.clearVocas();

                Voca voca = new Voca("hello", "안녕하세요");
                voca.learned = true;
                dao.insert(voca);
                voca = new Voca("world", "세계");
                voca.learned = true;
                dao.insert(voca);
                voca = new Voca("yes", "그래");
                voca.learned = true;
                dao.insert(voca);
                voca = new Voca("no", "아니");
                voca.learned = true;
                dao.insert(voca);

                for (int i = 0; i < words.size() - 1; i++) {
                    Log.d("add", words.get(i) + means.get(i));
                    voca = new Voca(words.get(i), means.get(i));
                    dao.insert(voca);
                }
            });
        }
    };
}
