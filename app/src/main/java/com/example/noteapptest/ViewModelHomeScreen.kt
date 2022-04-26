package com.example.noteapptest

import androidx.lifecycle.ViewModel

class ViewModelHomeScreen: ViewModel() {
    private var view: com.example.noteapptest.ListView? = null
    private var noteList = mutableListOf<NewNote>()
    var notes: List<NewNote> = ViewModelNoteCreate().notesCreate
        private set

    fun onViewReady(view: com.example.noteapptest.ListView) {
        addNoteHome()
        this.view = view
        view.displayList(notes)
    }

    fun addNoteHome(){
        noteList = ViewModelNoteCreate().notesCreate.toMutableList()
    }

    fun addNewNote(title: String, text: String){
        noteList.add(newNote(title, text))
    }

    private fun newNote(title: String, text: String) =
        NewNote (
            title,
            text
        )
}