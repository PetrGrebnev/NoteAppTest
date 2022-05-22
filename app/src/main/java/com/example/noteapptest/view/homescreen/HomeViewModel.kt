package com.example.noteapptest.view.homescreen

import androidx.lifecycle.ViewModel
import com.example.noteapptest.Note
import com.example.noteapptest.Repository

class HomeViewModel(private val repHome: Repository): ViewModel() {

    fun getNotes() = repHome.getAllNote()
    fun deleteAll() = repHome.deleteAll()
    fun deleteNoteById(noteId: Long) = repHome.deleteNoteByID(noteId)
}