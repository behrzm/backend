package com.example.pro

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pro.data.htmlLessons

@Composable
fun LessonDetailScreen(lessonNumber: Int, navBack: () -> Unit) {
    val lesson = htmlLessons[lessonNumber]

    Column(modifier = Modifier.fillMaxSize().padding(20.dp)) {
        Text(lesson?.title ?: "", fontSize = 32.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(20.dp))
        Text(lesson?.description ?: "")
        Spacer(modifier = Modifier.height(20.dp))
        CodeEditor()
        Spacer(modifier = Modifier.height(30.dp))
        Box(
            modifier = Modifier
                .background(Color(0xFF3A79FF), RoundedCornerShape(20.dp))
                .clickable { navBack() }
                .padding(16.dp)
        ) {
            Text("Назад", color = Color.White)
        }
    }
}
