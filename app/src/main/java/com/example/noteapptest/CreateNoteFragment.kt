package com.example.noteapptest

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.noteapptest.databinding.CreateNoteTextBinding
import java.util.concurrent.Executor

class CreateNoteFragment: Fragment(R.layout.create_note_text) {

    private var bindingCreateNoteText: CreateNoteTextBinding? = null
    private val binding
        get() = bindingCreateNoteText!!

    private lateinit var viewModel: CreateNoteViewModel
    private lateinit var viewModelFactory: CreateNoteViewModelFactory

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingCreateNoteText = CreateNoteTextBinding.bind(view)
        val repository = Repository(
            NoteDatabase.getInstance(requireContext()).noteDatabaseDAO,
            AppExecutor.ioExecutor
        )
        viewModelFactory = CreateNoteViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[CreateNoteViewModel::class.java]

        binding.addNote.setOnClickListener {
            val title = binding.createTitleNote.text.toString()
            val text = binding.createTextNote.text.toString()
            viewModel.addNewNote(title, text)
            parentFragmentManager.popBackStack()
        }
    }

}