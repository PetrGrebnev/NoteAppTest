package com.example.noteapptest

import androidx.lifecycle.ViewModel

class CreateNoteViewModel(private val rep: Repository): ViewModel() {

    fun addNewNote(title: String, text: String){
        rep.addNote(title, text)
    }

}