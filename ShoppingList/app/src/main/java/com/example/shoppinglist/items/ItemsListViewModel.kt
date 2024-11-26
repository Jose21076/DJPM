package com.example.shoppinglist.items

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.TAG
import com.example.shoppinglist.models.Item
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

data class ItemsListState(
    val listItemsList : List<Item> = arrayListOf(),
    val isLoading: Boolean = false,
    val error: String? = null
)

class ItemsListViewModel : ViewModel(){

    var state = mutableStateOf(ItemsListState())
        private set


    fun getItems(docID: String) {

        val db = Firebase.firestore

        db.collection("lists")
            .document(docID)
            .collection("items")
            .addSnapshotListener { value, error ->
                if (error != null) {
                    state.value = state.value.copy(
                        error = error.message
                    )
                    return@addSnapshotListener
                }

                val items = arrayListOf<Item>()
                for (document in value?.documents!!) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    val item = document.toObject(Item::class.java)
                    item?.docId = document.id
                    items.add(item!!)
                }
                state.value = state.value.copy(
                    listItemsList = items
                )
            }
    }

    fun toggleItemChecked(listId: String, item: Item){
        val db = Firebase.firestore

        item.checked = !item.checked

        db.collection("lists")
            .document(listId)
            .collection("items")
            .document(item.docId!!)
            .set(item)
    }
}