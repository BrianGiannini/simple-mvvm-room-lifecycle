package com.training.mvvmroomlifecycle

import android.app.Application
import androidx.lifecycle.LiveData
import com.training.mvvmroomlifecycle.utils.subscribeOnBackground

class NoteRepository(application: Application) {

    private var noteDao: NoteDao
    private var allNotes: LiveData<List<Note>>
    private val database = NoteDatabase.getInstance(application)

    init {
        noteDao = database.noteDao()
        allNotes = noteDao.getAllNotes()
    }

    fun insert(note: Note) {
        subscribeOnBackground {
            noteDao.insert(note)
        }
    }

    fun update(note: Note) {
        subscribeOnBackground {
            noteDao.update(note)
        }
    }

    fun delete(note: Note) {
        subscribeOnBackground {
            noteDao.delete(note)
        }
    }

    fun deleteAllNotes() {
        subscribeOnBackground {
            noteDao.deleteAllNotes()
        }
    }

    fun getAllNotes(): LiveData<List<Note>> {
        return allNotes
    }



}