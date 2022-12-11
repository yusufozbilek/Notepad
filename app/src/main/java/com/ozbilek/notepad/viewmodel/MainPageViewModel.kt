package com.ozbilek.notepad.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ozbilek.notepad.entity.Note
import com.ozbilek.notepad.repo.NoteDaoRepo

class MainPageViewModel(application: Application):AndroidViewModel(application) {
    val nrepo = NoteDaoRepo(application)
    var notes = MutableLiveData<List<Note>>()
    init {
        loadNotes()
        notes = nrepo.getList()
    }
    fun loadNotes(){
        nrepo.getAll()
    }
    fun deleteNote(Id:Int){
        nrepo.deleteNote(Id)
        loadNotes()
        notes = nrepo.getList()
    }
}