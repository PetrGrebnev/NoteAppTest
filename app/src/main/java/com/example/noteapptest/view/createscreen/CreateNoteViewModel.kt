package com.example.noteapptest.view.createscreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.noteapptest.Note
import com.example.noteapptest.Repository
import com.example.noteapptest.ResultState

class CreateNoteViewModel(
    private val noteId: Long,
    private val rep: Repository
) : ViewModel() {

    val currentNote: LiveData<ResultState<Note?>> = rep.getNoteById(noteId)

    fun updateOrAddNote(title: String, text: String, noteId: Long) {
        if (noteId == 0L) {
            rep.addNote(title, text)
        } else {
            rep.updateNote(title, text, noteId)
        }
    }

    init {

    }
}
