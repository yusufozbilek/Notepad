@file:OptIn(ExperimentalMaterial3Api::class)

package com.rectifier.notepad


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun NoteItem(noteName:String="None",noteContent:String="Every Moment I live... is agony."){
    val is_favorite = remember { mutableStateOf(false)}
    val fav_icon = remember{ mutableStateOf(Icons.Filled.FavoriteBorder)}
    val is_MoreVertClicked = remember { mutableStateOf(false)}
    ElevatedCard(modifier = Modifier.padding(10.dp,15.dp), onClick = {}) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(modifier = Modifier.padding(20.dp,10.dp),text = noteName, fontSize = 20.sp)
                Row {
                    IconButton(onClick = {
                        if (!is_favorite.value) {fav_icon.value=Icons.Filled.Favorite;is_favorite.value= true}
                        else {fav_icon.value=Icons.Filled.FavoriteBorder;is_favorite.value=false}}) {
                        Icon(fav_icon.value,"")
                    }
                    IconButton(onClick = { is_MoreVertClicked.value=true }) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "Options")
                        DropdownMenu(expanded = is_MoreVertClicked.value, onDismissRequest = { is_MoreVertClicked.value = false}) {
                            DropdownMenuItem(text = { Text(text = "Edit")}, onClick = { /*TODO*/ }, leadingIcon = {Icon(Icons.Filled.Delete,"")})
                            DropdownMenuItem(text = { Text(text = "Delete")}, onClick = { /*TODO*/ }, leadingIcon = {Icon(Icons.Filled.Edit,"")})
                            DropdownMenuItem(text = { Text(text = "Info")}, onClick = { /*TODO*/ }, leadingIcon = {Icon(Icons.Filled.Info,"")})
                        }
                    }
                }

            }

            Text(modifier= Modifier.padding(20.dp,10.dp),text = noteContent, fontSize = 15.sp)
        }
    }
}
@Composable
fun MainScreen(navController: NavController){
    val searchWord = remember { mutableStateOf("")}
    val isenable = remember{ mutableStateOf(false)}
    val notelist = remember{ mutableStateListOf<String>() }
    LaunchedEffect(key1 = true){

    }
    Scaffold(
        modifier = Modifier,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    notelist.add("")
                    navController.navigate("NoteScreen")
                }
            ) {
            Icon(Icons.Filled.Add, contentDescription = "Add new element") } },
        floatingActionButtonPosition = FabPosition.End,
        topBar = {
            SmallTopAppBar(
                title = {
                        TextField(
                            value = searchWord.value,
                            onValueChange = {searchWord.value=it},
                            colors =TextFieldDefaults.textFieldColors(containerColor = Color.Transparent,disabledIndicatorColor = Color.Transparent, disabledPlaceholderColor = Color.Transparent, disabledTextColor = Color.Transparent),
                            enabled = isenable.value,
                            placeholder = { Text(text = "Search", fontSize = 22.sp)}

                            )
                        },
                navigationIcon = { IconButton(onClick = { /*TODO*/ }) { Icon(Icons.Filled.Menu, contentDescription ="")}},
                actions = {
                    IconButton(onClick = { isenable.value = !isenable.value }) {
                        if (!isenable.value){
                            Icon(Icons.Filled.Search,"Search")
                        }else{
                            Icon(Icons.Filled.Close,"Iptal")
                        }
                    }
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Settings,"Settings")
                    }
                }
            )
        }
    ){
        paddingValues ->
        LazyColumn(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)) {
            items(count = notelist.count()){
                NoteItem("Title")
            }
        }

    }
}

@Composable
fun NoteScreen(navController: NavController){
    val locked = remember { mutableStateOf(false) }
    val favorited = remember { mutableStateOf(false) }
    val delete = remember { mutableStateOf(false)}
    val noteTitle = remember{ mutableStateOf("")}
    val noteText = remember { mutableStateOf("")}
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    TextField(
                        value = noteTitle.value,
                        onValueChange = {noteTitle.value = it},
                        colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent, unfocusedIndicatorColor = Color.Transparent, focusedIndicatorColor = Color.Transparent),
                        singleLine = true,
                        placeholder = { Text(text = "Title", fontSize = 20.sp)},
                        textStyle = TextStyle(fontSize = 20.sp)

                    ) },
                navigationIcon = { IconButton(onClick = { navController.navigate("MainScreen") }) {Icon(Icons.Filled.ArrowBack,"Go back")}},
                actions = {
                    IconButton(onClick = { favorited.value = !favorited.value }) {
                        if (!favorited.value){
                            Icon(Icons.Filled.FavoriteBorder, "Favourite")
                        }else{
                            Icon(Icons.Filled.Favorite, "Favourite")
                        }

                    }
                    IconButton(onClick = { locked.value = !locked.value }) {
                        if (!locked.value){
                            Icon(Icons.Outlined.Lock, "Lock")
                        }else{
                            Icon(Icons.Filled.Lock, "Lock")
                        }

                    }

                    IconButton(onClick = {delete.value = !delete.value}) {
                        if (!delete.value){
                            Icon(Icons.Outlined.Delete, "Delete")
                        }else{
                            Icon(Icons.Filled.Delete, "Delete")
                        }


                    }
                }
            )
        },




    ) {
        paddingValues ->
        Column(modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()) {
            TextField(modifier = Modifier.fillMaxSize(),value = noteText.value, onValueChange = {noteText.value=it},colors = TextFieldDefaults.textFieldColors(containerColor = Color.Transparent), textStyle = TextStyle(fontSize = 16.sp))
        }
    }
}


@Preview(showBackground = true, showSystemUi = true, name = "MainMenu", group = "Screens")
@Composable
fun PrevMainScreen(){
    val testnavController = rememberNavController()
    MainScreen(testnavController)
}
@Preview(name = "Card", group = "Items")
@Composable
fun Cardview(){
    NoteItem(noteName = "Title Test")
}

@Preview(showSystemUi = true, showBackground = true, name = "NoteScreen", group = "Screens")
@Composable
fun PrevNoteScreen(){
    val testnavController = rememberNavController()
    NoteScreen(testnavController)
}


