package com.ozbilek.notepad.view

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.ozbilek.notepad.viewmodel.MainPageViewModel
import com.ozbilek.notepad.viewmodelfactory.MainPageViewModelFactory

@ExperimentalMaterial3Api
@Composable
fun MainPage(navController: NavController){
    val context = LocalContext.current
    var viewModel: MainPageViewModel = viewModel(
        factory = MainPageViewModelFactory(context.applicationContext as Application)
    )
    var noteList = viewModel.notes.observeAsState(listOf())

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            SearchBar()
        },
        floatingActionButton = {
            NoteAddButton(navController)
        }
    )
    {
        Column(modifier = Modifier.padding(it)) {
            LazyColumn{
                items(
                    count = noteList.value.size,
                    itemContent = {
                        val Id = noteList.value[it].NoteID
                        val noteTitle = noteList.value[it].NoteTitle
                        val noteText = noteList.value[it].NoteText
                        NoteItem(navController,viewModel,ID = Id,noteTitle = noteTitle, noteText = noteText)
                    }
                )
            }
        }
    }
}


@ExperimentalMaterial3Api
@Composable
fun NoteItem(navController: NavController,viewModel: MainPageViewModel,ID:Int,noteTitle:String,noteText:String){
    Card (
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 10.dp)
            .clickable {
                navController.navigate("NotePage/$ID")
            }
    )
    {
        Column(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
        ) {
            Text(text = noteTitle, fontSize = 15.sp)
            Text(text = noteText)
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { viewModel.deleteNote(ID) }, content = {Icon(Icons.Outlined.Delete, contentDescription = "")})
            }
        }
        
        
    }
}

@ExperimentalMaterial3Api
@Composable
fun SearchBar(){
    TopAppBar(
        title = {
            Text(text = "NotePad")
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}

@ExperimentalMaterial3Api
@Composable
fun NoteAddButton(navController: NavController){
    FloatingActionButton(
        onClick = { navController.navigate("NewNotePage") }
    ) {
        Icon(Icons.Filled.Add,"New note button")
    }
}


