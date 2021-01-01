package com.training.mvvmroomlifecycle

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.training.mvvmroomlifecycle.utils.subscribeOnBackground
import org.jetbrains.annotations.NotNull

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        private var instance: NoteDatabase? = null

        @Synchronized
        @NotNull
        fun getInstance(ctx: Context): NoteDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    ctx.applicationContext, NoteDatabase::class.java,
                    "note_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()
            }

            return instance!!

        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                populateDatabase(instance)
            }
        }

        private fun populateDatabase(db: NoteDatabase?) {
            if (db != null) {
                val noteDao = db.noteDao()
                subscribeOnBackground {
                    noteDao.insert(Note("title 1", "desc 1", 1))
                    noteDao.insert(Note("title 2", "desc 2", 2))
                    noteDao.insert(Note("title 3", "desc 3", 3))
                }
            }
        }
    }

}