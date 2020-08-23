
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

    val allWords: LiveData<List<individualWord>>

    init {
        val wordsDao = individual_WordRoomDatabase.getDatabase(application, viewModelScope).wordDao()
        repositoryIndividual = individual_WordRepository(wordsDao)
        allWords = repositoryIndividual.allWords
    }


    fun insert(individualWord: individualWord) = viewModelScope.launch(Dispatchers.IO) {
        repositoryIndividual.insert(individualWord)
    }

    fun delete(individualWord: individualWord) = viewModelScope.launch(Dispatchers.IO) {
        repositoryIndividual.delete(individualWord)
    }
}
