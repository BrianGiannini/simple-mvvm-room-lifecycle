package com.pedroroig.architectureexamplecondinginflow

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class Note(@PrimaryKey(autoGenerate = false) val id: Int?,
                val title: String,
                val description: String,
                val priority: Int)