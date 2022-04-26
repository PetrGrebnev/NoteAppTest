package com.example.noteapptest

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.noteapptest.databinding.CreateNoteTextBinding

class CreateNoteText: Fragment(R.layout.create_note_text) {

    private var bindingCreateNoteText: CreateNoteTextBinding? = null
    private val binding
        get() = bindingCreateNoteText!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingCreateNoteText = CreateNoteTextBinding.bind(view)

        binding.addNote.setOnClickListener {
            val title = binding.createTitleNote.text.toString()
            val text = binding.createTextNote.text.toString()
            ViewModelNoteCreate().addNewNote(title, text)
            parentFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_container, HomeScreen(), null)
                .commit()
        }
    }

}