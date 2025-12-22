package com.example.pro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class LessonDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lessonId = intent.getIntExtra("lesson_id", 1)

        setContent {
            LessonDetailScreen(
                lessonNumber = lessonId,
                navBack = { finish() }
            )
        }
    }
}
