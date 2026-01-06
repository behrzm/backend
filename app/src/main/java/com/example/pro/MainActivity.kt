package com.example.pro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.pro.main.MainScreen
import com.example.pro.ui.theme.ProTheme
import com.example.pro.ui.theme.task.TaskScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProTheme {
                TaskScreen()
            }
        }
    }
}
