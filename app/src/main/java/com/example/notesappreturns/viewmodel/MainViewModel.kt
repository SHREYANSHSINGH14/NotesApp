package com.example.notesappreturns.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesappreturns.database.Notes
import com.example.notesappreturns.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository):ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO){
            repository.getNotes()
        }
    }
    val notes:LiveData<List<Notes>>
    get() = repository.notes
}