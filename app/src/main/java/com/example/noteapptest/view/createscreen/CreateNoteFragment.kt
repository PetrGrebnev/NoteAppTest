package com.example.noteapptest.view.createscreen

import android.app.ActionBar
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.noteapptest.*
import com.example.noteapptest.databinding.CreateNoteTextBinding
import com.example.noteapptest.view.homescreen.HomeFragmentDirections
import java.lang.IllegalArgumentException
import kotlin.properties.Delegates

class CreateNoteFragment: Fragment(R.layout.create_note_text) {

    private lateinit var controller: NavController
    private var noteId by Delegates.notNull<Long>()

    private var bindingCreateNoteText: CreateNoteTextBinding? = null
    private val binding
        get() = bindingCreateNoteText!!

    private lateinit var viewModel: CreateNoteViewModel

    private fun initCreateVieModel(noteId: Long){
        val factory = object: ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return CreateNoteViewModel(
                    noteId,
                    Repository(
                        NoteDatabase.getInstance(requireContext()).noteDatabaseDAO,
                        AppExecutor.ioExecutor
                    )
                ) as T
            }
        }
        viewModel = ViewModelProvider(this, factory)[CreateNoteViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller = Navigation.findNavController(view)
        bindingCreateNoteText = CreateNoteTextBinding.bind(view)

        noteId = arguments?.getLong(ARGUMENT_NOTE_ID) ?: INVALID_ID
        initCreateVieModel(noteId)

        viewModel.currentNote.observe(viewLifecycleOwner){
            binding.createTitleNote.setText(it?.title ?: "")
            binding.createTextNote.setText(it?.text ?: "")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_create, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.save -> {
                val title = binding.createTitleNote.text.toString()
                val text = binding.createTextNote.text.toString()
                viewModel.updateOrAddNote(title, text, noteId)
                controller.popBackStack()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    
    companion object{
        private const val INVALID_ID = 1L
        private const val ARGUMENT_NOTE_ID = "ARGUMENT_NOTE_ID"
        fun newInstance(noteId: Long): CreateNoteFragment{
            val args = Bundle().apply {
                putLong(ARGUMENT_NOTE_ID, noteId)
            }
            val fragment = CreateNoteFragment()
            fragment.arguments = args
            return fragment
        }
    }

}