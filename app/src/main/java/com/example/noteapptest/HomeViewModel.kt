package com.example.noteapptest

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class HomeViewModel: ViewModel() {
    val notes: LiveData<List<Note>> = Repository.notes
}