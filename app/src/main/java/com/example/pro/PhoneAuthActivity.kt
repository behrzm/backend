package com.example.pro

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
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
                onSendCode = { phone ->
                    startPhoneAuth(phone, false)
                },
                onVerifyCode = { code ->
                    verifyCode(code)
                },
                onResendCode = { phone ->
                    startPhoneAuth(phone, true)
                }
            )
        }
    }

    private fun startPhoneAuth(phone: String, isResend: Boolean) {
        val builder = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phone)
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(callbacks)

        if (isResend && resendToken != null) {
            builder.setForceResendingToken(resendToken!!)
        }

        PhoneAuthProvider.verifyPhoneNumber(builder.build())
    }

    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            auth.signInWithCredential(credential)
                .addOnSuccessListener { goMain() }
        }

        override fun onVerificationFailed(e: FirebaseException) {
            e.printStackTrace()
        }

        override fun onCodeSent(
            id: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            verificationId = id
            resendToken = token
        }
    }

    private fun verifyCode(code: String) {
        val credential = PhoneAuthProvider.getCredential(
            verificationId ?: return,
            code
        )

        auth.signInWithCredential(credential)
            .addOnSuccessListener { goMain() }
            .addOnFailureListener { it.printStackTrace() }
    }

    private fun goMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
