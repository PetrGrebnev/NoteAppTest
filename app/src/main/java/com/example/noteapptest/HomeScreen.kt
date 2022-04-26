package com.example.noteapptest

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapptest.databinding.HomeScreenBinding

class HomeScreen: Fragment(R.layout.home_screen), ListView {

    override val supportsDisplayNewItem: Boolean = false

    private var bindingHomeScreen: HomeScreenBinding? = null
    private val binding
        get() = bindingHomeScreen!!
    private val viewModel = ViewModelHomeScreen()
    private lateinit var adapter: ListNoteAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
       adapter = ListNoteAdapter(layoutInflater, ViewModelHomeScreen().notes)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingHomeScreen = HomeScreenBinding.bind(view)

        binding.apply {
            addFab.setOnClickListener {
 //               viewModel.addNewNote("Заголовок", "Текст заметки")
                parentFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container, CreateNoteText(), null)
                    .commit()
            }

            listNote.adapter = adapter
            listNote.layoutManager = LinearLayoutManager(requireContext())
            adapter.addListNote(ViewModelHomeScreen().notes)
        }

        viewModel.onViewReady(this)
    }

    override fun displayList(notes: List<NewNote>) {
        binding.listNote.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun setHasOptionsMenu(hasMenu: Boolean) {
        super.setHasOptionsMenu(hasMenu)
    }




    override fun displayNewItemInList(user: NewNote) {
        TODO("Not yet implemented")
    }

    override fun askForWriteExternalStoragePermission() {
        TODO("Not yet implemented")
    }

}

