package com.example.noteapptest

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import com.example.noteapptest.NoteRepositoryTest.Companion.DEMO_NOTE_0
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test


class NoteRepositoryTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun getNoteById_WhenNoteIsPresent(){
        //GIVEN
        val dao = NoteDaoStub()
        val sut = Repository(dao, TestExecutor())

        //WHEN
        val resultLiveData = sut.getNoteById(1)

        //THEN
        assertEquals(ResultState.Success(DEMO_NOTE_0), resultLiveData.value)
    }

    companion object{
        val DEMO_NOTE_0 = Note(
            "text",
            "title",
            0
        )
    }
}

class NoteDaoStub: NoteDatabaseDAO{
    override fun insert(note: Note) {
        TODO("Not yet implemented")
    }

    override fun updateNote(note: Note) {
        TODO("Not yet implemented")
    }

    override fun getAllNotes(): LiveData<List<Note>> {
        TODO("Not yet implemented")
    }

    override fun getAllNotesById(): LiveData<List<Note>> {
        TODO("Not yet implemented")
    }

    override fun getNoteSortByTitle(): LiveData<List<Note>> {
        TODO("Not yet implemented")
    }

    override fun getNoteSortByTitleDesc(): LiveData<List<Note>> {
        TODO("Not yet implemented")
    }

    override fun deleteAllNotes() {
        TODO("Not yet implemented")
    }

    override fun getNoteById(noteId: Long): Note? {
        if (noteId < 0) return null
        return DEMO_NOTE_0
    }

    override fun deleteNoteById(noteId: Long) {
        TODO("Not yet implemented")
    }

}