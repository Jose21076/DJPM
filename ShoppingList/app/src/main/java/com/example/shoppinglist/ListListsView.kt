package com.example.shoppinglist

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
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

@Composable
fun ListListsView(modifier: Modifier = Modifier,
                  navController: NavController = rememberNavController()
){

    val viewModel : ListListsViewModel = viewModel()
    val state = viewModel.state.value

    LazyColumn(modifier = modifier.fillMaxSize()) {
        itemsIndexed(
            items = state.listItemsList
        ){  index, item ->
            Text(
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp),
                text = item.name?:""
            )
        }
    }

    Box(modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomEnd){
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