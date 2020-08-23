package com.aravind.aravind_systemtest2.individual_data


import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.aravind.aravind_systemtest2.individual_data.individualWord
import com.aravind.aravind_systemtest2.individual_data.individualWordDao

class individual_WordRepository(private val individualWordDao: individualWordDao) {

    val allWords: LiveData<List<individualWord>> = individualWordDao.getAlphabetizedWords()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(individualWord: individualWord) {
        individualWordDao.insert(individualWord)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(individualWord: individualWord) {
        individualWordDao.deleteById(individualWord.word)
    }
}
