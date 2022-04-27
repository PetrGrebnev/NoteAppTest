package com.example.noteapptest

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.noteapptest.databinding.CreateNoteTextBinding

class CreateNoteFragment: Fragment(R.layout.create_note_text) {

    private var bindingCreateNoteText: CreateNoteTextBinding? = null
    private val binding
        get() = bindingCreateNoteText!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingCreateNoteText = CreateNoteTextBinding.bind(view)
        val viewModelCreate = ViewModelProvider(this)
            .get(CreateNoteViewModel::class.java)

        binding.addNote.setOnClickListener {
            val title = binding.createTitleNote.text.toString()
            val text = binding.createTextNote.text.toString()
            viewModelCreate.addNewNote(title, text)
            parentFragmentManager.popBackStack()
        }
    }

}