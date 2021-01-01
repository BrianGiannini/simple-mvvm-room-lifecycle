package com.training.mvvmroomlifecycle

import androidx.room.Entity
import androidx.room.PrimaryKey

// add tableName if we don't want to use default 'Note" name given by class
@Entity(tableName = "note_table")
data class Note(
    val title: String,
    val description: String,
    val priority: Int,
    @PrimaryKey(autoGenerate = false) val id: Int? = null
)