package com.example.notesappreturns.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesappreturns.database.Notes
import com.example.notesappreturns.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InsertViewModel(private val repository: Repository):ViewModel() {

    fun insert(notes: Notes){
        viewModelScope.launch(Dispatchers.IO){
            repository.insertNote(notes)
        }
    }

}