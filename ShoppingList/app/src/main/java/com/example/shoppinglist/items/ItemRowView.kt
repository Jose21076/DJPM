package com.example.shoppinglist.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shoppinglist.R
import com.example.shoppinglist.models.Item
import com.example.shoppinglist.screen
import com.example.shoppinglist.ui.theme.ShoppingListTheme
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

@Composable
fun ItemRomView(
    modifier: Modifier = Modifier,
    listid : String = "",
    item : Item,
    onCheckedChange : ()->Unit = {}
){
    Row {
        Text(
            modifier = Modifier
                .padding(16.dp).weight(1f),
            text = item.name?:"")

        Text(
            modifier = Modifier
                .padding(16.dp),
            text = item.qtd.toString()?:"")
        Checkbox(
            checked =item.checked,
            onCheckedChange = {onCheckedChange()}
        )
        Button(modifier = Modifier
            .padding(10.dp)
            .size(32.dp),
            colors = ButtonDefaults.buttonColors(Color.Red),
            onClick = {
                val db = Firebase.firestore

                db.collection("lists")
                    .document(listid)
                    .collection("items")
                    .document(item.docId!!)
                    .delete()
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

    }

}

@Preview(showBackground = true)
@Composable
fun ItemRomViewPreview(){
    ShoppingListTheme {
        ItemRomView(item = Item(
            docId = "",
            name = "Lápis",
            qtd = 2.0,
            checked = false
        )
        )
    }
}