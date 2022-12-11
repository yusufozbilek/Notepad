package com.ozbilek.notepad.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ozbilek.notepad.entity.Note
import com.ozbilek.notepad.repo.NoteDaoRepo

class NotePageViewModel(application: Application): AndroidViewModel(application) {
    private val nrepo =  NoteDaoRepo(application)
    var title = MutableLiveData<String>()
    var text = MutableLiveData<String>()
    init {
        title = MutableLiveData()
        text = MutableLiveData()
    }

    fun getNote(Id:Int){
        nrepo.getNoteById(Id)
        title.value = nrepo.findednote.value!!.NoteTitle
        text.value = nrepo.findednote.value!!.NoteText
    }
    fun uploadNote(Id: Int){
        nrepo.updateNote(Note(Id,title.value!!,text.value!!,0,""))
    }
    fun deleteNote(Id: Int){
        nrepo.deleteNote(Id)
    }
    fun changeTitle(newTitle:String){
        title.value = newTitle
    }
    fun changeText(newText:String){
        text.value = newText
    }
}