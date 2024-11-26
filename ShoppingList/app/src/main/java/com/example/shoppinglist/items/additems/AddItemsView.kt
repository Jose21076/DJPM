package com.example.shoppinglist.items.additems

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
import com.example.shoppinglist.lists.addlists.AddListViewModel
import com.example.shoppinglist.ui.theme.ShoppingListTheme

@Composable
fun AddItemView(
    modifier: Modifier = Modifier,
    listId : String,
    navController: NavController = rememberNavController()
){
    val viewModel : AddItemViewModel = viewModel()
    val state = viewModel.state.value

    Column(modifier = modifier.fillMaxSize()) {
        TextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = {
                Text("Enter item name")
            },
            value = state.name,
            onValueChange = viewModel::onNameChange
        )
        TextField(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = {
                Text("Enter quantity")
            },
            value = state.qtd.toString(),
            onValueChange = viewModel::onQtdChange
        )
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            onClick = {
                viewModel.addItem(listId)
                navController.popBackStack()
            }) {
            Text("add")
        }
    }
}

@Preview
@Composable
fun AddListViewPreview(){
    ShoppingListTheme {
        AddItemView(modifier = Modifier, listId = "")
    }
}