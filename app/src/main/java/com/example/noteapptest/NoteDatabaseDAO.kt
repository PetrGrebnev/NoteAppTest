package com.example.noteapptest

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDatabaseDAO {

    @Insert
    fun insert(note: Note)

    @Update
    fun updateNote(note: Note)

    @Query("SELECT * FROM note_table ORDER BY noteId DESC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM note_table ORDER BY noteId ASC")
    fun getAllNotesById(): LiveData<List<Note>>

    @Query("SELECT * FROM note_table ORDER BY title ASC")
    fun getNoteSortByTitle(): LiveData<List<Note>>

    @Query("SELECT * FROM note_table ORDER BY title DESC")
    fun getNoteSortByTitleDesc(): LiveData<List<Note>>

    @Query("DELETE FROM note_table")
    fun deleteAllNotes()

    @Query("SELECT * FROM note_table WHERE noteId=:noteId")
    fun getNoteById(noteId: Long): Note?

    @Query("DELETE FROM note_table WHERE noteId=:noteId")
    fun deleteNoteById(noteId: Long)
}