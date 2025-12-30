package com.example.pro

import android.app.Application
import com.google.firebase.FirebaseApp

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        val app = FirebaseApp.initializeApp(this)
        check(app != null) { "FirebaseApp failed to initialize" }
    }
}
