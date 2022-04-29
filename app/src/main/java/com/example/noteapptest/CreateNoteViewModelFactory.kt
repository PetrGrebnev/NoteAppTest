package com.example.noteapptest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class CreateNoteViewModelFactory(private val repository: Repository):
    ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass.isAssignableFrom(CreateNoteViewModel::class.java)){
                return CreateNoteViewModel(repository) as T
            }
            throw IllegalArgumentException("unknown ViewModel class")
        }
}