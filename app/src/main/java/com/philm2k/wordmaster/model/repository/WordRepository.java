package com.philm2k.wordmaster.model.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.philm2k.wordmaster.model.WordMasterDatabase;
import com.philm2k.wordmaster.model.dao.WordDao;
import com.philm2k.wordmaster.model.entity.Word;

import java.util.List;

public class WordRepository {
    private final WordDao wordDao;
    private final LiveData<List<Word>> allWords;

    public WordRepository(Application application){
        WordMasterDatabase db = WordMasterDatabase.getInstance(application);
        wordDao = db.wordDao ();
        //allWords = wordDao.getAll ();   // 내림차순 정렬 by word
        allWords = wordDao.getRandomAll ();  // Random 정렬
    }

    public LiveData<List<Word>> getAllWords(){
        return allWords;
    }

    public Word getWordById(int id){
        try {
            return new WordRepository.GetWordByIdAsyncTask(wordDao).execute(id).get();
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public Word getWordByWord(String word){
        try {
            return new WordRepository.GetWordByWordAsyncTask(wordDao).execute(word).get();
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void insertWord(Word word) {
        new WordRepository.InsertWordAsyncTask (wordDao).execute(word);
    }

    public void updateWord(Word word){
        new WordRepository.UpdateWordAsyncTask (wordDao).execute(word);
    }

    public void deleteWord(Word word) {
        new WordRepository.DeleteWordAsyncTask (wordDao).execute(word);
    }

    public void deleteAllWords(){
        new WordRepository.DeleteAllWordsAsyncTask (wordDao).execute();
    }

    private static class GetWordByIdAsyncTask extends AsyncTask<Integer, Void, Word>{

        private WordDao wordDao;

        public GetWordByIdAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Word doInBackground(Integer... integers) {
            return wordDao.getWordByWord(integers[0]);
        }
    }

    private static class GetWordByWordAsyncTask extends AsyncTask<String, Void, Word>{

        private WordDao wordDao;

        public GetWordByWordAsyncTask(WordDao wordDao) {
            this.wordDao = wordDao;
        }

        @Override
        protected Word doInBackground(String... strings) {
            return wordDao.getWordByWord(strings[0]);
        }
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
}
