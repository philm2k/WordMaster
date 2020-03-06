package com.philm2k.wordmaster.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.philm2k.wordmaster.model.entity.Word;

import java.util.List;

@Dao
public interface WordDao {

    @Query("SELECT * FROM word_table WHERE id = :id LIMIT 1")
    Word getWordByWord(int id);

    @Query("SELECT * FROM word_table WHERE word = :word LIMIT 1")
    Word getWordByWord(String word);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Word word);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int update(Word word);

    @Query("DELETE FROM word_table")
    int deleteAll();

    @Delete
    int delete(Word word);

    @Query("SELECT * from word_table ORDER BY word ASC")
    LiveData<List<Word>> getAll();

    @Query("SELECT * FROM word_table ORDER BY RANDOM()")
    LiveData<List<Word>> getRandomAll();

    @Query("SELECT * FROM word_table ORDER BY RANDOM() Limit :qty")
    LiveData<List<Word>> getRandomFew(int qty);

}
