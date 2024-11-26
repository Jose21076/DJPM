package com.example.shoppinglist.login

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.TAG
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

class LoginViewModel : ViewModel() {

    var state = mutableStateOf(LoginState())
        private set

    fun onEmailChange(email: String) {
        state.value = state.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        state.value = state.value.copy(password = password)
    }

    fun onLoginClick(onLoginSuccess: () -> Unit) {
        state.value = state.value.copy(isLoading = true)
        // Perform login logic here
        state.value = state.value.copy(isLoading = false)

        var auth: FirebaseAuth = Firebase.auth

        auth.signInWithEmailAndPassword(state.value.email, state.value.password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInWithEmail:success")
                val user = auth.currentUser
                onLoginSuccess()
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "signInWithEmail:failure", task.exception)
                state.value = state.value.copy(error = task.exception?.message?: "Unknown error")
            }
        }
    }
}

