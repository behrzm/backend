package com.example.pro

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class LessonPathActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LessonPathScreen { lessonId ->
                val intent = Intent(this, LessonDetailActivity::class.java)
                intent.putExtra("lesson_id", lessonId)
                startActivity(intent)
            }
        }
    }
}
