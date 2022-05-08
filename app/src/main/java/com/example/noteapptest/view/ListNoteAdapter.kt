package com.example.noteapptest.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapptest.Note
import com.example.noteapptest.R

class ListNoteAdapter(
    private val layoutInflater: LayoutInflater,
    private val onClickListener: OnNoteClickListener? = null
): RecyclerView.Adapter<ListNoteAdapter.ViewHolder>(){

    private var notes: MutableList<Note> = mutableListOf()

    fun setListNote(notes: List<Note>){
        this.notes = notes.toMutableList()
        notifyDataSetChanged()
    }

    fun newNote(note: Note){
        this.notes.add(note)
        notifyItemChanged(notes.size)
    }

    interface OnNoteClickListener{
        fun onNoteClick(note: Note)
    }

    inner class ViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val itemTitleNote: TextView = itemView.findViewById(R.id.item_title_note)
        val itemTextNote: TextView = itemView.findViewById(R.id.item_text_note)
        init {
            itemView.setOnClickListener{
                onClickListener?.onNoteClick(notes[adapterPosition])
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            layoutInflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = notes[position]
        holder.apply {
            itemTitleNote.text = item.title
            itemTextNote.text = item.text
        }
    }

    override fun getItemCount() = notes.size


}
