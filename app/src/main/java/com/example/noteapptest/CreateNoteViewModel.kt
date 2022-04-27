package com.example.noteapptest

import androidx.lifecycle.ViewModel

class CreateNoteViewModel: ViewModel() {

    fun addNewNote(title: String, text: String){
        Repository.addNote(title, text)
    }

}