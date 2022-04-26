package com.example.noteapptest

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListNoteAdapter(
    private val layoutInflater: LayoutInflater,
    private val notes: List<NewNote>
): RecyclerView.Adapter<ListNoteAdapter.ViewHolder>(){

    private var notesList = mutableListOf<NewNote>()

    class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val itemTitleNote: TextView = itemView.findViewById(R.id.item_title_note)
        val itemTextNote: TextView = itemView.findViewById(R.id.item_text_note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            layoutInflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = notes[position]
        holder.apply {
            itemTitleNote.text = item.titleNote
            itemTextNote.text = item.textNote
        }
    }

    override fun getItemCount() = notesList.size

    @SuppressLint("NotifyDataSetChanged")
    fun addNote(notesList: List<NewNote>){
        this.notesList = notesList.toMutableList()
        notifyDataSetChanged()
    }

    fun addListNote(note: List<NewNote>) {
        notesList = note.toMutableList()
        notifyDataSetChanged()
    }


}
