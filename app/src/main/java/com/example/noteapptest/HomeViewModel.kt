package com.example.noteapptest

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class HomeViewModel(private val repHome: Repository): ViewModel() {

    fun getNotes() = repHome.getAllNote()
}