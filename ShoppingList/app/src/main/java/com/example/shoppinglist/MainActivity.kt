package com.example.shoppinglist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglist.items.ListItemsView
import com.example.shoppinglist.items.additems.AddItemView
import com.example.shoppinglist.lists.addlists.AddListView
import com.example.shoppinglist.lists.ListListsView
import com.example.shoppinglist.login.LoginView
import com.example.shoppinglist.login.RegisterView
import com.example.shoppinglist.ui.theme.ShoppingListTheme
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

const val TAG = "ShoppingList"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ShoppingListTheme {
                var navcontroller = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(modifier = Modifier.padding(innerPadding),
                        navController = navcontroller,
                        startDestination = screen.Login.route
                    ){
                        composable(screen.Login.route){
                            LoginView(modifier = Modifier.padding(innerPadding),
                                onLoginSuccess = {
                                    navcontroller.navigate("home")
                                },
                                navController = navcontroller
                            )
                        }
                        composable(screen.Register.route){
                            RegisterView(modifier = Modifier.padding(innerPadding),
                                onRegisterSuccess = {
                                    navcontroller.navigate("home")
                                }
                            )
                        }
                        composable(screen.Home.route) {
                            ListListsView(navController = navcontroller)
                        }
                        composable(screen.AddList.route) {
                            AddListView(navController = navcontroller)
                        }
                        composable(screen.ListItems.route) {
                            val listId = it.arguments?.getString("listId")
                            ListItemsView(
                                modifier = Modifier.padding(innerPadding),
                                listId = listId ?: ""
                            )
                        }
                        composable(screen.AddItem.route) {
                            val listId = it.arguments?.getString("listId")
                            AddItemView(
                                modifier = Modifier.padding(innerPadding),
                                listId = listId ?: ""
                            )
                        }
                    }
                }
                LaunchedEffect(Unit) {
                    val auth = Firebase.auth
                    val currentUser = auth.currentUser
                    if (currentUser != null) {
                        navcontroller.navigate(screen.Home.route)
                    }
                }
            }
        }
    }
}

sealed class screen(val route: String){
    object Login: screen("login")
    object Register: screen("register")
    object Home: screen("home")
    object ListItems : screen("list_items/{listId}")
    object AddList: screen("add_list")
    object AddItem: screen("add_item/{listId}")
}