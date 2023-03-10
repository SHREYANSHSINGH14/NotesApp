package com.example.notesappreturns.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Notes::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao

    companion object {
        @Volatile
        private var INSTANCE: NotesDatabase? = null

        fun getDatabase(context: Context): NotesDatabase {
            if (INSTANCE == null) {
                synchronized(lock = this) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        NotesDatabase::class.java,
                        "notesDB"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }
}