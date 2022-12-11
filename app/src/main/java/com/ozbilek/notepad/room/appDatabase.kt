package com.ozbilek.notepad.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ozbilek.notepad.entity.Note

@Database(entities = [Note::class], version = 1)
abstract class appDatabase:RoomDatabase() {
    abstract fun notesDao() : NoteDao
    companion object{
        var INSTANCE: appDatabase? = null

        fun databaseAccess(context: Context): appDatabase?{
            if (INSTANCE == null){
                synchronized(appDatabase::class){
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        appDatabase::class.java,
                        "notedatabase.db"
                        ).createFromAsset("notedatabase.db").build()
                }
            }
            return INSTANCE
        }
    }
}