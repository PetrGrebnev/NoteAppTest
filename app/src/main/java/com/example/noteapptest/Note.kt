package com.example.noteapptest

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "note_table")
data class Note(
    val title: String,
    val text: String,
    @PrimaryKey(autoGenerate = true)
    var noteId: Long = 0L
)
