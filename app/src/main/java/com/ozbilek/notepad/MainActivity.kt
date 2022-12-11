package com.ozbilek.notepad

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ozbilek.notepad.ui.theme.NotepadTheme
import com.ozbilek.notepad.view.MainPage
import com.ozbilek.notepad.view.NewNotePage
import com.ozbilek.notepad.view.NotePage

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotepadTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavBall()
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun NavBall(){
    val navController = rememberNavController()
    NavHost(navController = navController , startDestination = "MainPage"){
        composable("MainPage"){
            MainPage(navController)
        }
        composable("NotePage/{ID}",
            arguments = listOf(navArgument("ID"){type = NavType.IntType})){
            val ID = it.arguments?.getInt("ID")!!
            NotePage(navController= navController,ID)
        }
        composable("NewNotePage"){
            NewNotePage(navController)
        }
    }
}