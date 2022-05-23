package com.example.noteapptest.view.homescreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.noteapptest.Note
import com.example.noteapptest.NoteSortOrder
import com.example.noteapptest.Repository

class HomeViewModel(private val repository: Repository) : ViewModel() {

    private var _columns = MutableLiveData<Boolean>()
    val columns: LiveData<Boolean> = _columns

    fun getNotes(sortOrder: NoteSortOrder): LiveData<List<Note>> {
        return when (sortOrder) {
            NoteSortOrder.NONE -> repository.getAllNotesById()
            NoteSortOrder.TITLE_ASC -> repository.getNoteSortByTitle()
            NoteSortOrder.TITLE_DESC -> repository.getNoteSortByTitleDesc()
            NoteSortOrder.ID_ASC -> repository.getAllNote()
        }
    }

    fun deleteAll() = repository.deleteAll()
    fun deleteNoteById(noteId: Long) = repository.deleteNoteByID(noteId)

    fun linesOrColumns(columns: Boolean) {
        _columns.value = columns
    }
}