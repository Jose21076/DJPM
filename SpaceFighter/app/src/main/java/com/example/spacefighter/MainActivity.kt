package com.example.spacefighter

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.spacefighter.ui.theme.SpaceFighterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        WindowCompat.setDecorFitsSystemWindows(window,false)

        enableEdgeToEdge()
        setContent {
            val navcontroller = rememberNavController()
            SpaceFighterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(navController = navcontroller,
                        startDestination = "game start"){
                        composable("game start"){
                            GameHomeView(onPlayClick = {
                                navcontroller.navigate("game_screen")
                            })
                        }
                        composable("game_screen") {
                            GameScreenView()
                        }
                    }
                }
            }
        }
    }
}