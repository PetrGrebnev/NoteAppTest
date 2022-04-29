package com.example.noteapptest

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapptest.databinding.HomeScreenBinding

class HomeFragment: Fragment(R.layout.home_screen){

    private var bindingHomeScreen: HomeScreenBinding? = null
    private val binding
        get() = bindingHomeScreen!!

    private lateinit var viewModel: HomeViewModel
    private lateinit var viewModelFactory: HomeViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingHomeScreen = HomeScreenBinding.bind(view)
        val repository = Repository(
            NoteDatabase.getInstance(requireContext()).noteDatabaseDAO,
            AppExecutor.ioExecutor
        )
        viewModelFactory = HomeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory)[HomeViewModel::class.java]

        viewModel.getNotes().observe(viewLifecycleOwner){
            binding.listNote.adapter = ListNoteAdapter(layoutInflater, it)
        }

        binding.apply {
            addFab.setOnClickListener {
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, CreateNoteFragment(), null)
                    .addToBackStack(null)
                    .commit()
            }
           listNote.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun setHasOptionsMenu(hasMenu: Boolean) {
        super.setHasOptionsMenu(hasMenu)
    }
}

