package com.example.noteapptest

import androidx.lifecycle.ViewModel

class ViewModelNoteCreate: ViewModel() {
    private var view: com.example.noteapptest.ListView? = null
    private var noteList = mutableListOf<NewNote>()
    var notesCreate: List<NewNote> = noteList
        private set

    fun addNewNote(title: String, text: String){
        noteList.add(newNote(title, text))
    }

    private fun newNote(title: String, text: String) =
        NewNote (
        title,
        text
    )
}