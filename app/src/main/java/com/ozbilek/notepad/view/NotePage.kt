package com.ozbilek.notepad.view

import android.app.Application
import android.provider.ContactsContract.CommonDataKinds.Note
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ozbilek.notepad.R
import com.ozbilek.notepad.viewmodel.NewNotePageViewModel
import com.ozbilek.notepad.viewmodel.NotePageViewModel
import com.ozbilek.notepad.viewmodelfactory.NotePageViewModelFactory
import kotlinx.coroutines.*

@ExperimentalMaterial3Api
@Composable
fun NotePage(navController: NavController,ID:Int){
    val context = LocalContext.current
    var viewModel: NotePageViewModel = viewModel(
        factory = NotePageViewModelFactory(context.applicationContext as Application)
    )
    var noteTitle = viewModel.title.observeAsState()
    var noteText = viewModel.text.observeAsState()
    viewModel.getNote(ID)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    TextField(
                        value = noteTitle.value!!,
                        onValueChange = { viewModel.changeTitle(it) },
                        placeholder = { Text(text = "TitlePlaceHolder", fontSize = 20.sp) },
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = Color.Transparent,
                            textColor = MaterialTheme.colorScheme.onPrimary,
                            placeholderColor = MaterialTheme.colorScheme.onPrimary,
                            cursorColor = MaterialTheme.colorScheme.onPrimary,
                            unfocusedLabelColor = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("MainPage") })
                    {
                        Icon(Icons.Filled.ArrowBack,"Go to main menu")
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.uploadNote(ID) }) {
                        Icon(painterResource(R.drawable.ic_outline_save_24),"Save Button")
                    }
                    IconButton(onClick = { viewModel.deleteNote(ID);navController.navigate("MainPage") }) {
                        Icon(Icons.Outlined.Delete, contentDescription = "DeleteNote")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                )

            )
        }

    ){
        Column(
            modifier = Modifier
                .padding(it)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxSize(),
                value = noteText.value!!,
                onValueChange = { it1 ->  viewModel.changeText(it1)},
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    textColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    cursorColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                )

            )
        }
    }
}

