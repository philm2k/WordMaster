package com.philm2k.wordmaster.model;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.philm2k.wordmaster.model.dao.UsageDao;
import com.philm2k.wordmaster.model.dao.WordDao;
import com.philm2k.wordmaster.model.entity.Usage;
import com.philm2k.wordmaster.model.entity.Word;
import com.philm2k.wordmaster.model.entity.WordWithUsages;

@Database(entities = {Word.class,Usage.class, WordWithUsages.class}, version = 1, exportSchema = false)
public abstract class WordMasterDatabase extends RoomDatabase {
    private static WordMasterDatabase instance;

    public abstract WordDao wordDao();
    public abstract UsageDao usageDao();

    public static synchronized WordMasterDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder (context.getApplicationContext (),
                    WordMasterDatabase.class, "wordmaster-db" )
                    .fallbackToDestructiveMigration ()
                    .addCallback ( roomCallback )
                    .build ();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback (){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate ( db );
            new PopulateAsyncTask ( instance ).execute (  );
        }
    };

    private static class PopulateAsyncTask extends AsyncTask<Void, Void, Void> {

        private WordDao wordDao;
        private UsageDao usageDao;

        public PopulateAsyncTask(WordMasterDatabase db) {
            this.wordDao = db.wordDao ();
            this.usageDao = db.usageDao ();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
