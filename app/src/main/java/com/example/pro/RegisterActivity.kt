package com.example.pro

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.*
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class RegisterActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var googleClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseApp.initializeApp(this)
        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id)) // 🔥 WEB CLIENT ID
            .requestEmail()
            .build()

        googleClient = GoogleSignIn.getClient(this, gso)

        val googleLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode != RESULT_OK) return@registerForActivityResult

                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

                try {
                    val account = task.getResult(Exception::class.java)

                    val credential = GoogleAuthProvider.getCredential(
                        account.idToken,
                        null
                    )

                    auth.signInWithCredential(credential)
                        .addOnSuccessListener {
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                        .addOnFailureListener {
                            it.printStackTrace() // ← ТУТ у тебя была ошибка
                        }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

        setContent {
            RegisterScreen(
                onRegisterClick = { _, _ -> },
                onLoginClick = {
                    startActivity(Intent(this, LoginActivity::class.java))
                },
                onGoogleClick = {
                    googleClient.revokeAccess().addOnCompleteListener {
                        googleClient.signOut().addOnCompleteListener {
                            googleLauncher.launch(googleClient.signInIntent)
                        }
                    }
                },
                onPhoneClick = {
                    startActivity(Intent(this, PhoneAuthActivity::class.java))
                }
            )
        }
    }
}
