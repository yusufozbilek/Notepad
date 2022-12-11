package com.ozbilek.notepad.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.ozbilek.notepad.entity.Note
import com.ozbilek.notepad.repo.NoteDaoRepo

class NewNotePageViewModel(application: Application): AndroidViewModel(application) {
    private var nrepo = NoteDaoRepo(application)
    var title = MutableLiveData<String>()
    var text = MutableLiveData<String>()

    init {
        title.value = ""
        text.value = ""
    }
    fun saveNote(){
        nrepo.insertNote(Note(0,title.value!!,text.value!!,0,"Null"))
    }

    fun changeTitle(newTitle:String){
        title.value = newTitle
    }

    fun changeText(newText:String){
        text.value = newText
    }
}