package com.philm2k.wordmaster.view.word;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.philm2k.wordmaster.model.entity.Word;
import com.philm2k.wordmaster.model.repository.WordRepository;

public class AddWordViewModel extends AndroidViewModel {
    private WordRepository repository;
    private MutableLiveData<String> txtWord = new MutableLiveData<>();
    private MutableLiveData<String> txtMeaning = new MutableLiveData<>();

    public AddWordViewModel(@NonNull Application application) {
        super(application);
        repository = new WordRepository(application);
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

    public void insert(Word word){
        repository.insertWord ( word );
    }

    public void insert(String word, String meaning){
        repository.insertWord ( new Word(word, meaning) );
    }
}
