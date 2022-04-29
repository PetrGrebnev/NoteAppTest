package com.example.noteapptest

import java.util.concurrent.ExecutorService

class Repository(private val db: NoteDatabaseDAO, private val ioExecutor: ExecutorService) {
//    private val _notes = MutableLiveData<List<Note>>()
//    private var listNote: MutableList<Note> = mutableListOf()
//
//    val notes: LiveData<List<Note>> = _notes

    fun addNote(title: String, text: String){
        ioExecutor.execute{
            db.insert(Note(title,text))
        }
    }

//        listNote.add(Note(title, text))
//        _notes.value = listNote
    fun getAllNote() = db.getAllNotes()


}