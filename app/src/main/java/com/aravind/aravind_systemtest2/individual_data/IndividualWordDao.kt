package com.aravind.aravind_systemtest2.individual_data


import androidx.lifecycle.LiveData;
import androidx.room.*
import com.aravind.aravind_systemtest2.individual_data.individualWord


@Dao
interface individualWordDao {

    @Query("SELECT * from word_table ORDER BY word ASC")
    fun getAlphabetizedWords(): LiveData<List<individualWord>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(individualWord: individualWord)

    @Query("DELETE FROM word_table WHERE word = :id")
    fun deleteById(id: String)

    @Query("DELETE FROM  word_table")
    fun deleteAll()
}
