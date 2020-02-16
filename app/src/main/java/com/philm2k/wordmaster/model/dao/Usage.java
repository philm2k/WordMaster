package com.philm2k.wordmaster.model.dao;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "usage_table",foreignKeys = @ForeignKey ( entity = Word.class,
        parentColumns = "id", childColumns = "word_id", onDelete = CASCADE))

public class Usage {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String english_statement;
    private String korean_translation;
    private Long timestamp;
    private int word_id;

    public Usage(String english_statement, String korean_translation, int word_id) {
        this.english_statement = english_statement;
        this.korean_translation = korean_translation;
        this.timestamp = System.currentTimeMillis ();
        this.word_id = word_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnglish_statement() {
        return english_statement;
    }

    public void setEnglish_statement(String english_statement) {
        this.english_statement = english_statement;
    }

    public String getKorean_translation() {
        return korean_translation;
    }

    public void setKorean_translation(String korean_translation) {
        this.korean_translation = korean_translation;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public int getWord_id() {
        return word_id;
    }

    public void setWord_id(int word_id) {
        this.word_id = word_id;
    }

    @Override
    public String toString() {
        return "Usage{" +
                "id=" + id +
                ", english_statement='" + english_statement + '\'' +
                ", korean_translation='" + korean_translation + '\'' +
                ", timestamp=" + new Date (timestamp).toString () +
                ", word_id=" + word_id +
                '}';
    }
}