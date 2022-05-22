package com.example.noteapptest

import java.util.concurrent.ExecutorService

class Repository(
    private val db: NoteDatabaseDAO,
    private val ioExecutor: ExecutorService)
{

    fun addNote(title: String, text: String){
        ioExecutor.execute{
            db.insert(Note(title,text))
        }
    }

    fun updateNote(title: String, text: String, noteId: Long){
        ioExecutor.execute{
            db.updateNote(Note(title,text, noteId))
        }
    }

    fun getAllNote() = db.getAllNotes()

    fun deleteAll() {
        ioExecutor.execute{
            db.deleteAllNotes()
        }
    }

    fun getNoteById(noteId: Long) = db.getNoteById(noteId)

    fun deleteNoteByID(noteId: Long){
        ioExecutor.execute {
            db.deleteNoteById(noteId)
        }
    }
}