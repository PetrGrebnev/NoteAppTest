package com.example.noteapptest

class Repository {

    private var noteList = mutableListOf<NewNote>()
    var notes: List<NewNote> = ViewModelNoteCreate().notesCreate
        private set

    fun addNote(title: String, text: String){
        noteList.add(newNote(title, text))
    }

    private fun newNote(title: String, text: String) =
        NewNote (
            title,
            text
        )
}