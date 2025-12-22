package com.example.pro

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class RegisterActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth

    private val googleLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            val account = task.result

            val credential = GoogleAuthProvider.getCredential(account.idToken, null)

            auth.signInWithCredential(credential)
                .addOnSuccessListener {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                .addOnFailureListener {
                    it.printStackTrace()
                }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val googleClient = GoogleSignIn.getClient(this, gso)

        setContent {
            RegisterScreen(
                onRegisterClick = { email, password ->
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnSuccessListener {
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        }
                        .addOnFailureListener {
                            it.printStackTrace()
                        }
                },
                onGoogleClick = {
                    googleClient.signOut()
                    googleLauncher.launch(googleClient.signInIntent)
                },
                onPhoneClick = {
                    startActivity(Intent(this, PhoneAuthActivity::class.java))
                },
                onLoginClick = {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
            )
        }
    }
}
