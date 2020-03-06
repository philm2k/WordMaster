package com.philm2k.wordmaster.model.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.philm2k.wordmaster.BR;

import java.util.List;

@Entity(tableName = "word_table")
public class Word extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String word;
    private String meaning;

    @Ignore
    public List<Usage> usages;

    public Word(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
    }

    @Bindable
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
        notifyPropertyChanged(BR.id);
    }

    @Bindable
    public String getWord() {
        return word;
    }
    public void setWord(String word) {
        this.word = word;
        notifyPropertyChanged(BR.word);
    }

    @Bindable
    public String getMeaning() {
        return meaning;
    }
    public void setMeaning(String meaning) {
        this.meaning = meaning;
        notifyPropertyChanged(BR.meaning);
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", meaning='" + meaning + '\'' +
                ", usages=" + usages +
                '}';
    }
}
