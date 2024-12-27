package com.example.shoppinglist.lists.addOwner

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore

data class ListItemState(
    val newOwner: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

class AddOwnerViewModel : ViewModel(){
    var state = mutableStateOf(ListItemState())
        private set

    fun onNewOwnerChange(newOwner: String){
        state.value = state.value.copy(newOwner = newOwner)
    }

    fun addOwner(docID: String){
        val db = Firebase.firestore

        db.collection("lists")
            .document(docID)
            .update("owners", FieldValue.arrayUnion(state.value.newOwner))
    }
}