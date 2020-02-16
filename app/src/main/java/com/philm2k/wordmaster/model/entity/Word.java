package com.philm2k.wordmaster.model.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String word;
    private String meaning;
    private boolean first;
    private boolean second;
    private boolean third;
    private boolean fourth;

    public Word(String word, String meaning) {
        this.word = word;
        this.meaning = meaning;
        this.first = false;
        this.second = false;
        this.third = false;
        this.fourth = false;
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

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isSecond() {
        return second;
    }

    public void setSecond(boolean second) {
        this.second = second;
    }

    public boolean isThird() {
        return third;
    }

    public void setThird(boolean third) {
        this.third = third;
    }

    public boolean isFourth() {
        return fourth;
    }

    public void setFourth(boolean fourth) {
        this.fourth = fourth;
    }
}
