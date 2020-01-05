package com.pedroroig.architectureexamplecondinginflow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteAdapter : RecyclerView.Adapter<NoteAdapter.NoteHolder>() {

    private var notes: List<Note> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.note_item, parent,
            false)
        return NoteHolder(itemView)
    }

    override fun getItemCount() =
        notes.size

    override fun onBindViewHolder(holder: NoteHolder, position: Int) {
        with(notes[position]) {
            holder.tvTitle.text = title
            holder.tvDescription.text = description
            holder.tvPriority.text = priority.toString()
        }
    }

    fun setNotes(notes: List<Note>) {
        this.notes = notes
        notifyDataSetChanged()
    }

    fun getNoteAt(position: Int) = notes[position]


    class NoteHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvTitle: TextView = itemView.findViewById(R.id.text_view_title)
        val tvDescription: TextView = itemView.findViewById(R.id.text_view_description)
        val tvPriority: TextView = itemView.findViewById(R.id.text_view_priority)

    }
}