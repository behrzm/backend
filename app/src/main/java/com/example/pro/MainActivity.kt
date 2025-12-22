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
}
