package com.ozbilek.notepad.repo

import android.app.Application
import android.icu.text.CaseMap.Title
import android.provider.ContactsContract.Data
import androidx.lifecycle.MutableLiveData
import com.ozbilek.notepad.entity.Note
import com.ozbilek.notepad.room.appDatabase
import kotlinx.coroutines.*

class NoteDaoRepo(application: Application) {
    var noteList = MutableLiveData<List<Note>>()
    var noteListSize = MutableLiveData<Int>()
    var findednote = MutableLiveData<Note>()
    val db: appDatabase
    init {
        db = appDatabase.databaseAccess(application.applicationContext)!!
        noteList = MutableLiveData()
        noteListSize = MutableLiveData()
        findednote = MutableLiveData(Note(0,"","",0,""))
    }

    fun getList():MutableLiveData<List<Note>>{
        return noteList
    }
    fun getAll(){
        val job:Job = CoroutineScope(Dispatchers.Main).launch {
            noteList.value = db.notesDao().getNotes()
        }
    }
    fun getNoteById(Id:Int){
        val job:Job = CoroutineScope(Dispatchers.Main).launch {
            findednote.value = db.notesDao().getNote(Id)
        }
    }
    fun insertNote(note:Note){
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            db.notesDao().addNote(note)
        }
    }
    fun updateNote(note:Note){
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            db.notesDao().updateNote(note)
        }
    }
    fun deleteNote(Id:Int){
        val job: Job = CoroutineScope(Dispatchers.Main).launch {
            db.notesDao().deleteUser(Note(Id,"","",0,""))
        }
    }
}