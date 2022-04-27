package com.example.noteapptest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object Repository {
    private val _notes = MutableLiveData<List<Note>>()
    val notes: LiveData<List<Note>> = _notes
    private var listNote: MutableList<Note> = mutableListOf()

    fun addNote(title: String, text: String){
        listNote.add(Note(title, text))
        _notes.value = listNote
    }
}