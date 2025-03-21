package com.ramlekhak.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ramlekhak.data.AppDatabase
import com.ramlekhak.data.EntryRepository
import kotlinx.coroutines.launch

/**
 * ViewModel for the Writing screen.
 */
class WritingViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: EntryRepository
    
    // Events for the UI
    private val _submissionEvent = MutableLiveData<SubmissionEvent>()
    val submissionEvent: LiveData<SubmissionEvent> = _submissionEvent

    init {
        val entryDao = AppDatabase.getDatabase(application).entryDao()
        repository = EntryRepository(entryDao)
    }

    /**
     * Submit a writing count to the repository
     */
    fun submitCount(count: Int, note: String? = null) {
        viewModelScope.launch {
            try {
                repository.insert(count, note)
                _submissionEvent.postValue(SubmissionEvent.Success(count))
            } catch (e: Exception) {
                _submissionEvent.postValue(SubmissionEvent.Error(e.message ?: "Unknown error occurred"))
            }
        }
    }

    /**
     * Submission event sealed class
     */
    sealed class SubmissionEvent {
        data class Success(val count: Int) : SubmissionEvent()
        data class Error(val message: String) : SubmissionEvent()
    }
}
