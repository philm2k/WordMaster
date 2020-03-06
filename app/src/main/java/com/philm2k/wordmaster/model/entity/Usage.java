package com.philm2k.wordmaster.model.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.philm2k.wordmaster.BR;

@Entity(tableName = "usage_table")
public class Usage extends BaseObservable {

    @PrimaryKey(autoGenerate = true)
    public int id;
    private String english_statement;
    private String korean_translation;
    public int word_id;

    public Usage(String english_statement, String korean_translation, int word_id) {
        this.english_statement = english_statement;
        this.korean_translation = korean_translation;
        this.word_id = word_id;
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
    public String getEnglish_statement() {
        return english_statement;
    }
    public void setEnglish_statement(String english_statement) {
        this.english_statement = english_statement;
        notifyPropertyChanged(BR.english_statement);
    }

    @Bindable
    public String getKorean_translation() {
        return korean_translation;
    }
    public void setKorean_translation(String korean_translation) {
        this.korean_translation = korean_translation;
        notifyPropertyChanged(BR.korean_translation);
    }

    @Bindable
    public int getWord_id() {
        return word_id;
    }
    public void setWord_id(int word_id) {
        this.word_id = word_id;
        notifyPropertyChanged(BR.word_id);
    }

    @Override
    public String toString() {
        return "Usage{" +
                "id=" + id +
                ", english_statement='" + english_statement + '\'' +
                ", korean_translation='" + korean_translation + '\'' +
                ", word_id=" + word_id +
                '}';
    }
}