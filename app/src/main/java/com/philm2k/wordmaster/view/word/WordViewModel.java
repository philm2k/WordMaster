package com.philm2k.wordmaster.view.word;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.philm2k.wordmaster.model.entity.Usage;
import com.philm2k.wordmaster.model.entity.Word;
import com.philm2k.wordmaster.model.entity.WordWithUsages;
import com.philm2k.wordmaster.model.repository.WordMasterRepository;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordMasterRepository repository;

    public WordViewModel(@NonNull Application application) {
        super (application);
        repository = new WordMasterRepository (application);
    }

    public void insertWord(Word word){
        repository.insertWord ( word );
    }

    public void updateWord(Word word){
        repository.updateWord ( word );
    }

    public void deleteWord(Word word){
        repository.deleteWord ( word );
    }

    public LiveData<List<Word>> getAllWords(){
        return repository.getAllWords ();
    }

    public LiveData<List<WordWithUsages>> getAllWordsAndUsages(){
        return repository.getAllWordsAndUsages ();
    }

    public void insertUsage(Usage usage) {
        repository.insertUsage ( usage );
    }

    public void updateUsage(Usage usage) {
        repository.updateUsage ( usage );
    }

    public void deleteUsage(Usage usage) {
        repository.deleteUsage ( usage );
    }
}