package com.example.notesappreturns.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.notesappreturns.database.Notes
import com.example.notesappreturns.database.NotesDatabase

class Repository(private val database: NotesDatabase) {

    private val notesLiveData = MutableLiveData<List<Notes>>()

    val notes:LiveData<List<Notes>>
    get() = notesLiveData

    suspend fun getNotes(){
        val result = database.notesDao().getAll()
        notesLiveData.postValue(result)
    }

    suspend fun insertNote(notes: Notes){
        database.notesDao().insertNote(notes)
    }
}