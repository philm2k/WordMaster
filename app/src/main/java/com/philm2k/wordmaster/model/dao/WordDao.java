package com.philm2k.wordmaster.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;

import com.philm2k.wordmaster.model.entity.Word;
import com.philm2k.wordmaster.model.entity.WordWithUsages;

import java.util.List;

@Dao
public interface WordDao {
    @Insert
    long insert(Word word);

    @Update
    int update(Word word);

    @Query("DELETE FROM word_table")
    int deleteAll();

    @Delete
    int delete(Word word);

    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAll();

    @Transaction
    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<WordWithUsages>> getAllWithUsages();
}
