package com.philm2k.wordmaster.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.philm2k.wordmaster.model.entity.Usage;

import java.util.List;

@Dao
public interface UsageDao {

    @Query("SELECT * FROM usage_table WHERE id = :id LIMIT 1")
    Usage getUsageById(int id);

    @Query("SELECT * FROM usage_table WHERE english_statement LIKE :usage LIMIT 1")
    Usage getUsageByEStmt(String usage);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(Usage usage);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int update(Usage usage);

    @Delete
    int delete(Usage usage);

    @Query("DELETE FROM usage_table")
    int deleteAll();

    @Query("SELECT * FROM usage_table")
    LiveData<List<Usage>> getAll();

    @Query("SELECT * FROM usage_table WHERE word_id = :wordId ORDER BY id")
    LiveData<List<Usage>> findUsagesForWord(final int wordId);

}
