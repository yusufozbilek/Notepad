package com.ozbilek.notepad.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ozbilek.notepad.viewmodel.NotePageViewModel

class NotePageViewModelFactory(var application: Application): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NotePageViewModel(application) as T
    }
}