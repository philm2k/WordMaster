package com.philm2k.wordmaster.model.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.philm2k.wordmaster.model.WordMasterDatabase;
import com.philm2k.wordmaster.model.dao.UsageDao;
import com.philm2k.wordmaster.model.dao.WordDao;
import com.philm2k.wordmaster.model.entity.Usage;
import com.philm2k.wordmaster.model.entity.Word;
import com.philm2k.wordmaster.model.entity.WordWithUsages;

import java.util.List;

public class WordMasterRepository {
    private final WordDao wordDao;
    private final LiveData<List<Word>> allWords;
    private final UsageDao usageDao;
    private final LiveData<List<WordWithUsages>> allWordsAndUsages;

    public WordMasterRepository(Application application){
        WordMasterDatabase db = WordMasterDatabase.getInstance(application);
        wordDao = db.wordDao ();
        allWords = wordDao.getAll ();
        usageDao = db.usageDao ();
        allWordsAndUsages = wordDao.getAllWithUsages ();
    }

    public LiveData<List<Word>> getAllWords(){
        return allWords;
    }

    public LiveData<List<WordWithUsages>> getAllWordsAndUsages(){
        return allWordsAndUsages;
    }

    /*--------------- word manipulation ------------------- */
    public void insertWord(Word word) {
        new WordMasterRepository.InsertWordAsyncTask (wordDao).execute(word);
    }

    public void updateWord(Word word){
        new WordMasterRepository.UpdateWordAsyncTask (wordDao).execute(word);
    }

    public void deleteWord(Word word) {
        new WordMasterRepository.DeleteWordAsyncTask (wordDao).execute(word);
    }

    public void deleteAllWords(){
        new WordMasterRepository.DeleteAllWordsAsyncTask (wordDao).execute();
    }

    private static class InsertWordAsyncTask extends AsyncTask<Word, Void, Void>{

        private WordDao wordDao;

        public InsertWordAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.insert ( words[0] );
            return null;
        }
    }

    private static class UpdateWordAsyncTask extends AsyncTask<Word, Void, Void>{

        private WordDao wordDao;

        public UpdateWordAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.update ( words[0] );
            return null;
        }
    }

    private static class DeleteWordAsyncTask extends AsyncTask<Word, Void, Void>{

        private WordDao wordDao;

        public DeleteWordAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            wordDao.delete ( words[0] );
            return null;
        }
    }

    private static class DeleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void>{

        private WordDao wordDao;

        public DeleteAllWordsAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            wordDao.deleteAll ( );
            return null;
        }
    }

    /*--------------- usage manipulation ------------------- */
    public void insertUsage(Usage usage) {
        new WordMasterRepository.InsertUsageAsyncTask (usageDao).execute ( usage );
    }
    public void updateUsage(Usage usage) {
        new WordMasterRepository.UpdateUsageAsyncTask (usageDao).execute ( usage );
    }
    public void deleteUsage(Usage usage) {
        new WordMasterRepository.DeleteUsageAsyncTask (usageDao).execute ( usage );
    }

    private static class InsertUsageAsyncTask extends AsyncTask<Usage, Void, Void>{

        private UsageDao usageDao;

        public InsertUsageAsyncTask(UsageDao usageDao) {
            this.usageDao = usageDao;
        }

        @Override
        protected Void doInBackground(Usage... usages) {
            usageDao.insert ( usages[0] );
            return null;
        }
    }

    private static class UpdateUsageAsyncTask extends AsyncTask<Usage, Void, Void>{

        private UsageDao usageDao;

        public UpdateUsageAsyncTask(UsageDao usageDao) {
            this.usageDao = usageDao;
        }

        @Override
        protected Void doInBackground(Usage... usages) {
            usageDao.update ( usages[0] );
            return null;
        }
    }

    private static class DeleteUsageAsyncTask extends AsyncTask<Usage, Void, Void>{

        private UsageDao usageDao;

        public DeleteUsageAsyncTask(UsageDao usageDao) {
            this.usageDao = usageDao;
        }

        @Override
        protected Void doInBackground(Usage... usages) {
            usageDao.delete ( usages[0] );
            return null;
        }
    }
}
