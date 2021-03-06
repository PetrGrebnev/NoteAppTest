package com.example.noteapptest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import java.lang.RuntimeException
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService

class Repository(
    private val db: NoteDatabaseDAO,
    private val ioExecutor: Executor
)
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

    fun getNoteSortByTitleDesc() = db.getNoteSortByTitleDesc()

    fun getNoteSortByTitle() = db.getNoteSortByTitle()

    fun getAllNotesById() = db.getAllNotesById()

    fun deleteAll() {
        ioExecutor.execute{
            db.deleteAllNotes()
        }
    }

    fun getNoteById(noteId: Long): LiveData<ResultState<Note?>>{
        val result = MutableLiveData<ResultState<Note?>>(ResultState.Loading())

        ioExecutor.execute(){
            Thread.sleep(1000)

            val note = db.getNoteById(noteId)
            if(note != null){
                result.postValue(ResultState.Success(note))
            } else {
                result.postValue(ResultState.Error(RuntimeException("New create note")))
            }
        }
        return result
    }

    fun deleteNoteByID(noteId: Long){
        ioExecutor.execute {
            db.deleteNoteById(noteId)
        }
    }
}