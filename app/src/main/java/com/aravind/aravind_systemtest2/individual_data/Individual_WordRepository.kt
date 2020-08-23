package com.aravind.aravind_systemtest2.individual_data


import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.aravind.aravind_systemtest2.individual_data.individualWord
import com.aravind.aravind_systemtest2.individual_data.individualWordDao

class individual_WordRepository(private val individualWordDao: individualWordDao) {

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    val allWords: LiveData<List<individualWord>> = individualWordDao.getAlphabetizedWords()

    // You must call this on a non-UI thread or your app will crash. So we're making this a
    // suspend function so the caller methods know this.
    // Like this, Room ensures that you're not doing any long running operations on the main
    // thread, blocking the UI.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(individualWord: individualWord) {
        individualWordDao.insert(individualWord)
    }

//    @Suppress("RedundantSuspendModifier")
//    @WorkerThread
//    suspend fun delete(individualWord: individualWord) {
//        individualWordDao.deleteAll(individualWord)
//    }

}
