package com.example.voca.Voca;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Voca.class}, version = 1, exportSchema = false)
abstract public class VocaDatabase extends RoomDatabase {
    abstract VocaDao vocaDao();

    private static volatile VocaDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static VocaDatabase getDatabase(final Context context) {
        if(INSTANCE == null) {
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

                Voca voca = new Voca("Hello", "안녕하세요");
                dao.insert(voca);
                voca = new Voca("World", "세계");
                dao.insert(voca);
            });
        }
    };
}
