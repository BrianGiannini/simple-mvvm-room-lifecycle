package com.pedroroig.architectureexamplecondinginflow

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_note.*


const val EXTRA_TITLE = "com.codinginflow.architectureexample.EXTRA_TITLE"
const val EXTRA_DESCRIPTION = "com.codinginflow.architectureexample.EXTRA_DESCRIPTION"
const val EXTRA_PRIORITY = "com.codinginflow.architectureexample.EXTRA_PRIORITY"

class AddNoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        number_picker_priority.minValue = 1
        number_picker_priority.maxValue = 10

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_close)

        title = "Add Note"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_note -> {
                saveNote()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveNote() {
        val title = et_title.text.toString()
        val desc = et_desc.text.toString()
        val priority = number_picker_priority.value

        if(title.isEmpty() || desc.isEmpty()) {
            Toast.makeText(this, "please insert title and description", Toast.LENGTH_SHORT).show()
            return
        }

        val data = Intent()
        data.putExtra(EXTRA_TITLE, title)
        data.putExtra(EXTRA_DESCRIPTION, desc)
        data.putExtra(EXTRA_PRIORITY, priority)

        setResult(Activity.RESULT_OK, data)
        finish()
    }
}
