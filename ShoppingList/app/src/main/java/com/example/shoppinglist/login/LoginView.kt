package com.example.shoppinglist.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglist.screen
import com.example.shoppinglist.ui.theme.ShoppingListTheme

@Composable
fun LoginView(modifier: Modifier = Modifier,
              onLoginSuccess: () -> Unit = {},
              navController: NavController = rememberNavController()){

    var viewModel: LoginViewModel = viewModel()
    val state = viewModel.state.value

    Box(modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center){
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            TextField(value =state.email,
                onValueChange = {
                    viewModel.onEmailChange(it)
                },
                placeholder = {
                    Text("Email:")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            TextField(value =state.password,
                onValueChange = {
                    viewModel.onPasswordChange(it)
                },
                placeholder = {
                    Text("Password:")
                }
            )
            Button(onClick = {
                viewModel.onLoginClick{
                    onLoginSuccess()
                }
            },
                content = {
                    Text("Login")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                navController.navigate(screen.Register.route)
            },
                content = {
                    Text("Register")
                }
            )
            if (state.error != null){
                Text(state.error)
            }
            if (state.isLoading) {
                CircularProgressIndicator()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginviewPreview(){
    ShoppingListTheme {
        LoginView()
    }
}