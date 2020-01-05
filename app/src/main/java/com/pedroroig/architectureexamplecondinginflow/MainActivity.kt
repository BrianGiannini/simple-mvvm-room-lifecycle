package com.pedroroig.architectureexamplecondinginflow

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.LEFT
import androidx.recyclerview.widget.ItemTouchHelper.RIGHT
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*


const val ADD_NOTE_REQUEST = 1

class MainActivity : AppCompatActivity() {

    private lateinit var vm: NoteViewModel
    private lateinit var adapter: NoteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpRecyclerView()

        setUpListeners()

        vm = ViewModelProviders.of(this)[NoteViewModel::class.java]

        vm.getAllNotes().observe(this, Observer {
            Log.i("Notes observed", "$it")

            adapter.setNotes(it)
        })

    }

    private fun setUpListeners() {
        button_add_note.setOnClickListener {
            val intent = Intent(this, AddNoteActivity::class.java)
            startActivityForResult(intent, ADD_NOTE_REQUEST)
        }

        // swipe listener
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, LEFT or RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val note = adapter.getNoteAt(viewHolder.adapterPosition)
                vm.delete(note)
            }

        }).attachToRecyclerView(recycler_view)
    }

    private fun setUpRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)

        adapter = NoteAdapter()
        recycler_view.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(data != null && requestCode == ADD_NOTE_REQUEST && resultCode == Activity.RESULT_OK) {
            val title: String = data.getStringExtra(EXTRA_TITLE)
            val description: String =
                data.getStringExtra(EXTRA_DESCRIPTION)
            val priority: Int = data.getIntExtra(EXTRA_PRIORITY, -1)
            vm.insert(Note(title, description, priority))
            Toast.makeText(this, "Note inserted!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Note not saved!", Toast.LENGTH_SHORT).show()
        }

    }
}
