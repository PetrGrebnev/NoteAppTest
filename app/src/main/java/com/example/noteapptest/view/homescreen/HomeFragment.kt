package com.example.noteapptest.view.homescreen

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.AppBarConfiguration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapptest.*
import com.example.noteapptest.databinding.HomeScreenBinding
import com.example.noteapptest.view.ListNoteAdapter
import com.example.noteapptest.view.ListNoteAdapter.OnNoteClickListener


class HomeFragment: Fragment(R.layout.home_screen){

    private lateinit var bindingHomeScreen: HomeScreenBinding
    private val binding
        get() = bindingHomeScreen
    private lateinit var controller: NavController
    private var lineOrColumns = true

    private lateinit var adapter: ListNoteAdapter

    private lateinit var viewModel: HomeViewModel
    private lateinit var viewModelFactory: HomeViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        controller = Navigation.findNavController(view)
        bindingHomeScreen = HomeScreenBinding.bind(view)

        val repository = Repository(
            NoteDatabase.getInstance(requireContext()).noteDatabaseDAO,
            AppExecutor.ioExecutor
        )
        viewModelFactory = HomeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]
        adapter = ListNoteAdapter(layoutInflater,
            object : OnNoteClickListener {
                override fun onNoteClick(note: Note) {
                    val bundle = bundleOf(ARGUMENT_NOTE_ID to note.noteId)
                    controller.navigate(
                        R.id.action_homeFragment_to_createNoteFragment,
                        bundle
                    )
                        Log.d("Home", "$note", null)
                }

                override fun delNote(note: Note) {
                    viewModel.deleteNoteById(note.noteId)
                    Log.d("Home", "$note", null)
                }
            }
        )
        viewModel.getNotes().observe(viewLifecycleOwner){
            adapter.setListNote(it)
        }
        viewModel.columns.observe(viewLifecycleOwner){
            lineOrColumns = it
        }

        binding.apply {
            addFab.setOnClickListener {
               controller.navigate(HomeFragmentDirections.actionHomeFragmentToCreateNoteFragment())
            }

            listNote.adapter = adapter
            if (lineOrColumns){
                listNote.layoutManager = LinearLayoutManager(requireContext())
            } else {
                listNote.layoutManager = GridLayoutManager(requireContext(),2)
            }
        }
    }



    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.settings -> {
                controller.navigate(HomeFragmentDirections.actionHomeFragmentToCreateNoteFragment())
                return true
            }
            R.id.delete -> {
                viewModel.deleteAll()
            }
            R.id.line_or_columns ->{
                if(lineOrColumns){
                    viewModel.linesOrColumns(false)
                } else {
                    viewModel.linesOrColumns(true)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object{
        private const val ARGUMENT_NOTE_ID = "ARGUMENT_NOTE_ID"
    }
}

