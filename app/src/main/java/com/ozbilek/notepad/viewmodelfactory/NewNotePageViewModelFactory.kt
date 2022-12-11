package com.ozbilek.notepad.viewmodelfactory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ozbilek.notepad.viewmodel.NewNotePageViewModel

class NewNotePageViewModelFactory(var application: Application): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NewNotePageViewModel(application) as T
    }
}