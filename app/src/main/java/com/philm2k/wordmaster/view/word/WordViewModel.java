package com.philm2k.wordmaster.view.word;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.philm2k.wordmaster.model.entity.Word;
import com.philm2k.wordmaster.model.repository.WordRepository;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository repository;
    private LiveData<List<Word>> allWords;

    public WordViewModel(@NonNull Application application) {
        super (application);
        repository = new WordRepository(application);
        allWords = repository.getAllWords();
    }

    public void insert(Word word){
        repository.insertWord ( word );
    }

    public void update(Word word){
        repository.updateWord ( word );
    }

    public void delete(Word word){
        repository.deleteWord ( word );
    }

    public Word getWordById(int id){
        return repository.getWordById(id);
    }

    public Word getWordByWord(String query){
        return repository.getWordByWord(query);
    }

    public LiveData<List<Word>> getAllWords(){
        return allWords;
    }
}