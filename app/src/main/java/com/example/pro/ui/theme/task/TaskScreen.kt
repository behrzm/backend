package com.example.pro.ui.theme.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pro.ai.AiHintService
import com.example.pro.model.Task

@Composable
fun TaskScreen() {

    val task = Task(
        id = 1,
        title = "HTML Form Task",
        description = "Create an HTML form with input and submit button.",
        difficulty = 1
    )

    val aiService = remember { AiHintService() }

    var userAnswer by remember { mutableStateOf("") }
    var hintLevel by remember { mutableStateOf(1) }
    var hintText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {

        Text(
            text = task.title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = task.description,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = userAnswer,
            onValueChange = { userAnswer = it },
            label = { Text("Your solution") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                hintText = aiService.getHint(
                    taskDescription = task.description,
                    userAttempt = userAnswer,
                    level = hintLevel
                )
                if (hintLevel < 3) hintLevel++
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(14.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6A5ACD)
            )
        ) {
            Text("🧠 Get hint (−XP)")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Using a hint will reduce XP",
            fontSize = 12.sp,
            color = Color.Red
        )

        if (hintText.isNotEmpty()) {
            Spacer(modifier = Modifier.height(16.dp))

            Card(
                shape = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF3F0FF)
                )
            ) {
                Text(
                    text = hintText,
                    modifier = Modifier.padding(16.dp),
                    color = Color(0xFF4A3AFF)
                )
            }
        }
    }
}
