package com.philm2k.wordmaster.model.entity;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "word_table")
public class Word {

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
