package com.ozbilek.notepad.room

import androidx.room.*
import com.ozbilek.notepad.entity.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM Note")
    suspend fun getNotes():List<Note>
    @Query("SELECT * FROM Note WHERE Note_ID=:NoteId")
    suspend fun getNote(NoteId:Int):Note
    @Insert
    suspend fun addNote(note:Note)
    @Update
    suspend fun updateNote(note:Note)
    @Delete
    suspend fun deleteUser(note:Note)
}