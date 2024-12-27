package com.example.shoppinglist.lists

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.shoppinglist.ui.theme.ShoppingListTheme
import androidx.compose.material3.Text
import com.example.shoppinglist.R
import com.example.shoppinglist.screen
import com.example.shoppinglist.ui.theme.Orange
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@Composable
fun ListListsView(
    modifier: Modifier = Modifier,
    navController: NavController = rememberNavController()
){

    val viewModel : ListListsViewModel = viewModel()
    val state = viewModel.state.value
    Box(modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd){
        LazyColumn(modifier = modifier.fillMaxSize()) {
            itemsIndexed(
                items = state.listItemsList
            ){  index, item ->
                Row(Modifier.fillMaxWidth()){
                    Text(
                        modifier = Modifier.weight(0.6f)
                            .padding(16.dp)
                            .clickable {
                                navController.navigate(
                                    screen.ListItems.route.replace("{listId}", item.docID!!)
                                )
                            },
                        text = item.name?:""
                    )
                    Button(modifier = Modifier.weight(0.2f)
                        .padding(10.dp)
                        .size(32.dp),
                        colors = ButtonDefaults.buttonColors(Color.Red),
                        onClick = {
                            val db = Firebase.firestore

                            db.collection("lists")
                                .document(item.docID!!)
                                .delete()

                            viewModel.getLists()
                        }){
                        Image(
                            modifier = Modifier
                                .scale(2.0f)
                                .size(32.dp),
                            colorFilter = ColorFilter.tint(Color.White),
                            painter = painterResource(id = R.drawable.baseline_remove_24),
                            contentDescription = "remove"
                        )
                    }
                    Button(modifier = Modifier.weight(0.2f)
                        .padding(10.dp)
                        .size(32.dp),
                        colors = ButtonDefaults.buttonColors(Orange),
                        onClick = {
                            navController.navigate(
                                screen.AddOwner.route.replace("{listId}", item.docID!!)
                            )
                        }){
                        Image(
                            modifier = Modifier
                                .scale(2.0f)
                                .size(32.dp),
                            colorFilter = ColorFilter.tint(Color.White),
                            painter = painterResource(id = R.drawable.baseline_add_24),
                            contentDescription = "add"
                        )
                    }
                }

            }
        }

        Button(modifier = Modifier
            .padding(10.dp)
            .size(64.dp),
            onClick = {
                navController.navigate(screen.AddList.route)
            }) {
            Image(
                modifier = Modifier
                    .scale(2.0f)
                    .size(64.dp),
                colorFilter = ColorFilter.tint(Color.White),
                painter = painterResource(id = R.drawable.baseline_add_24),
                contentDescription = "add"
            )
        }
    }
    LaunchedEffect(key1 = Unit){
        viewModel.getLists()
    }
}

@Preview
@Composable
fun ListListsViewPreview(){
    ShoppingListTheme {
        ListListsView(modifier = Modifier)
    }
}