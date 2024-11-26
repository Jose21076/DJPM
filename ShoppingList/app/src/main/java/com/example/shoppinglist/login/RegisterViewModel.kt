package com.example.shoppinglist.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.TAG
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


data class RegisterState(
    val email: String = "",
    val password: String = "",
    val rePassword: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

class RegisterViewModel : ViewModel() {

    var state = mutableStateOf(RegisterState())
        private set

    fun onEmailChange(email: String) {
        state.value = state.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        state.value = state.value.copy(password = password)
    }

    fun onPasswordConfirm(rePassword: String) {
        state.value = state.value.copy(rePassword = rePassword)
    }

    fun onRegisterClick(onRegisterSuccess: () -> Unit) {
        state.value = state.value.copy(isLoading = true)
        // Perform login logic here
        state.value = state.value.copy(isLoading = false)

        var auth: FirebaseAuth = Firebase.auth

        auth.createUserWithEmailAndPassword(state.value.email, state.value.password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "createUserWithEmail:success")
                val user = auth.currentUser
                onRegisterSuccess()
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", task.exception)
                state.value = state.value.copy(error = task.exception?.message?: "Unknown error")
            }
        }
    }
}