package kulkarni.aditya.architecture;

import android.arch.lifecycle.LiveData;
import android.arch.paging.DataSource;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import kulkarni.aditya.architecture.model.Word;

/**
 * Created by maverick on 3/21/18.
 */

@Dao
public interface WordDao {

    @Query("SELECT * FROM word_list ORDER BY word")
    DataSource.Factory<Integer, Word> getWords();

    @Query("SELECT * FROM word_list WHERE word = :word")
    LiveData<Word> getWordDetail(String word);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Word word);

    @Update
    void update(Word word);

    @Query("DELETE FROM word_list WHERE word = :word")
    void deleteWord(String word);
}
