package com.example.noteapptest.view.homescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteapptest.Note
import com.example.noteapptest.Repository

class HomeViewModel(private val repHome: Repository): ViewModel() {

    private var _columns = MutableLiveData<Boolean>()
    val columns: LiveData<Boolean> = _columns

    fun getNotes() = repHome.getAllNote()
    fun deleteAll() = repHome.deleteAll()
    fun deleteNoteById(noteId: Long) = repHome.deleteNoteByID(noteId)

    fun linesOrColumns(columns: Boolean){
        _columns.value = columns
    }
}