package com.philm2k.wordmaster.view.word;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.philm2k.wordmaster.model.entity.Word;
import com.philm2k.wordmaster.model.repository.WordRepository;

import java.util.List;

public class WordViewModel extends AndroidViewModel {

    private WordRepository repository;
    private LiveData<List<Word>> allWords;
    private MutableLiveData<String> txtWord = new MutableLiveData<>();
    private MutableLiveData<String> txtMeaning = new MutableLiveData<>();

    public WordViewModel(@NonNull Application application) {
        super (application);
        repository = new WordRepository(application);
        allWords = repository.getAllWords();
    }


    public MutableLiveData<String> getTxtWord() {
        return txtWord;
    }

    public void setTxtWord(MutableLiveData<String> txtWord) {
        this.txtWord = txtWord;
    }

    public MutableLiveData<String> getTxtMeaning() {
        return txtMeaning;
    }

    public void setTxtMeaning(MutableLiveData<String> txtMeaning) {
        this.txtMeaning = txtMeaning;
    }

    public void insert(String word, String meaning){
        repository.insertWord ( new Word(word, meaning) );
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