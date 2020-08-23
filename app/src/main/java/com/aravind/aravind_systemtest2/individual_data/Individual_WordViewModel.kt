
package com.aravind.aravind_systemtest2.individual_data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.aravind.aravind_systemtest2.individual_data.individualWord
import com.aravind.aravind_systemtest2.individual_data.individual_WordRepository
import com.aravind.aravind_systemtest2.individual_data.individual_WordRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class individual_WordViewModel(application: Application) : AndroidViewModel(application) {

    private val repositoryIndividual: individual_WordRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allWords: LiveData<List<individualWord>>

    init {
        val wordsDao = individual_WordRoomDatabase.getDatabase(application, viewModelScope).wordDao()
        repositoryIndividual = individual_WordRepository(wordsDao)
        allWords = repositoryIndividual.allWords
    }

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(individualWord: individualWord) = viewModelScope.launch(Dispatchers.IO) {
        repositoryIndividual.insert(individualWord)
    }
//    fun delete(individualWord: individualWord) = viewModelScope.launch(Dispatchers.IO) {
//        repositoryIndividual.delete(individualWord)
//    }
}
