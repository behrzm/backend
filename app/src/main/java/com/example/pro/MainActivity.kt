package com.example.pro

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CodLeyScreen(
                onLanguageClick = {
                    startActivity(
                        Intent(this, LessonPathActivity::class.java)
                    )
                }
            )
        }
    }

    private fun logout() {
        // Firebase
        com.google.firebase.auth.FirebaseAuth.getInstance().signOut()

        // Google
        val gso = com.google.android.gms.auth.api.signin.GoogleSignInOptions.Builder(
            com.google.android.gms.auth.api.signin.GoogleSignInOptions.DEFAULT_SIGN_IN
        ).build()

        val googleClient = com.google.android.gms.auth.api.signin.GoogleSignIn.getClient(this, gso)
        googleClient.signOut()

        // Переход на Register
        val intent = android.content.Intent(this, RegisterActivity::class.java)
        intent.flags = android.content.Intent.FLAG_ACTIVITY_NEW_TASK or
                android.content.Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

}
