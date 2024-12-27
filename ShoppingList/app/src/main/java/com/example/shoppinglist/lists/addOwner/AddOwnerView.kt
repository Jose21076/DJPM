package com.example.shoppinglist.lists.addOwner

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglist.items.additems.AddItemViewModel
import com.example.shoppinglist.lists.addlists.AddListViewModel
import com.example.shoppinglist.ui.theme.ShoppingListTheme

@Composable
fun AddOwnerView(
    modifier: Modifier = Modifier,
    listId : String,
    navController: NavController = rememberNavController()
){
    val viewModel : AddOwnerViewModel = viewModel()
    val state = viewModel.state.value

    Column(modifier = modifier.fillMaxSize()) {
        TextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = {
                Text("Enter userID")
            },
            value = state.newOwner,
            onValueChange = viewModel::onNewOwnerChange
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {
                viewModel.addOwner(listId)
                navController.popBackStack()
            }) {
            Text("add")
        }
    }
}

@Preview
@Composable
fun AddOwnerViewPreview() {
    ShoppingListTheme {
        AddOwnerView(modifier = Modifier, listId = "")
    }
}