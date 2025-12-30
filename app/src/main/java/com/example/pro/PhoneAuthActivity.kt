package com.example.pro

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class PhoneAuthActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private var verificationId: String? = null
    private var resendToken: PhoneAuthProvider.ForceResendingToken? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        setContent {
            PhoneAuthScreen(
                onSendCode = { phone -> startPhoneAuth(phone, false) },
                onVerifyCode = { code -> verifyCode(code) },
                onResendCode = { phone -> startPhoneAuth(phone, true) }
            )
        }
    }

    private fun startPhoneAuth(phone: String, isResend: Boolean) {

        if (!phone.startsWith("+")) {
            Toast.makeText(
                this,
                "Use format +77001234567",
                Toast.LENGTH_LONG
            ).show()
            return
        }

        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phone)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(callbacks)
            .apply {
                if (isResend && resendToken != null) {
                    setForceResendingToken(resendToken!!)
                }
            }
            .build()

        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private val callbacks =
        object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                auth.signInWithCredential(credential)
                    .addOnSuccessListener { goMain() }
                    .addOnFailureListener {
                        Toast.makeText(
                            this@PhoneAuthActivity,
                            it.localizedMessage,
                            Toast.LENGTH_LONG
                        ).show()
                    }
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Toast.makeText(
                    this@PhoneAuthActivity,
                    e.localizedMessage ?: "Verification failed",
                    Toast.LENGTH_LONG
                ).show()
            }

            override fun onCodeSent(
                id: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                verificationId = id
                resendToken = token
                PhoneAuthUiState.step.value = 2
            }
        }

    private fun verifyCode(code: String) {
        val id = verificationId ?: run {
            Toast.makeText(this, "Code not requested", Toast.LENGTH_SHORT).show()
            return
        }

        val credential = PhoneAuthProvider.getCredential(id, code)

        auth.signInWithCredential(credential)
            .addOnSuccessListener { goMain() }
            .addOnFailureListener {
                Toast.makeText(this, "Invalid code", Toast.LENGTH_LONG).show()
            }
    }

    private fun goMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
