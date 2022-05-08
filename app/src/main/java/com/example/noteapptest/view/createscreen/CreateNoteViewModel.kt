package com.example.noteapptest.view.createscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteapptest.Note
import com.example.noteapptest.Repository

class CreateNoteViewModel(
    private val noteId: Long,
    private val rep: Repository
    ): ViewModel() {

    private val _currentNote = MutableLiveData<Note?>()
    val currentNote: LiveData<Note?> = rep.getNoteById(noteId)

    private var currentNoteCache: Note? = null


    fun addNewNote(title: String, text: String){
        rep.addNote(title, text)
    }


    init {

    }
}
