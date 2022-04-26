package com.example.noteapptest

interface ListView {
    val supportsDisplayNewItem: Boolean

    fun displayList(notes: List<NewNote>)
    fun displayNewItemInList(note: NewNote)
    fun askForWriteExternalStoragePermission()
}