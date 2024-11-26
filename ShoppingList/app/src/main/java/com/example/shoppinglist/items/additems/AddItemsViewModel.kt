package com.example.shoppinglist.items.additems

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.TAG
import com.example.shoppinglist.models.Item
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

data class AddItemState(
    val name: String = "",
    val qtd: String? = "",
    val checked: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null
)

class AddItemViewModel: ViewModel() {
    var state = mutableStateOf(AddItemState())
        private set

    fun onNameChange(name: String) {
        state.value = state.value.copy(name = name)
    }

    fun onQtdChange(qtd: String) {
        state.value = state.value.copy(qtd = qtd)
    }

    fun addItem(listId: String){
        val db = Firebase.firestore

        val item = Item("", state.value.name, state.value.qtd?.toDouble(), state.value.checked)

        db.collection("lists")
            .document(listId)
            .collection("items")
            .add(item)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot written with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }

    }
}