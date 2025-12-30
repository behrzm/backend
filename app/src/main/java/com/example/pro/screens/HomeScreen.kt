package com.example.pro.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ---- DATA MODEL ----
data class Course(
    val title: String,
    val description: String,
    val progress: Float,
    val level: String,
    val color: List<Color>
)

// ---- MOCK DATA ----
private val courses = listOf(
    Course(
        title = "Python Basics",
        description = "Start coding with Python from scratch",
        progress = 0.35f,
        level = "Beginner",
        color = listOf(Color(0xFF667EEA), Color(0xFF764BA2))
    ),
    Course(
        title = "Java for Android",
        description = "Build real Android apps with Java",
        progress = 0.7f,
        level = "Intermediate",
        color = listOf(Color(0xFF11998E), Color(0xFF38EF7D))
    ),
    Course(
        title = "Kotlin & Jetpack Compose",
        description = "Modern Android UI development",
        progress = 0.15f,
        level = "Advanced",
        color = listOf(Color(0xFFFF512F), Color(0xFFDD2476))
    )
)

// ---- UI ----
@Composable
fun HomeScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
    ) {

        // Header
        Text(
            text = "Your Courses",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Continue learning where you left off",
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(courses) { course ->
                CourseCard(course)
            }
        }
    }
}

@Composable
private fun CourseCard(course: Course) {

    Card(
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {

        Box(
            modifier = Modifier
                .background(
                    Brush.horizontalGradient(course.color)
                )
                .padding(20.dp)
        ) {

            Column {

                // Title
                Text(
                    text = course.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = course.description,
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.9f)
                )

                Spacer(modifier = Modifier.height(14.dp))

                // Level chip
                AssistChip(
                    onClick = {},
                    label = {
                        Text(course.level, color = Color.White)
                    },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = Color.White.copy(alpha = 0.2f)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Progress
                LinearProgressIndicator(
                    progress = course.progress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp),
                    color = Color.White,
                    trackColor = Color.White.copy(alpha = 0.3f)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "${(course.progress * 100).toInt()}% completed",
                        color = Color.White,
                        fontSize = 12.sp
                    )

                    Button(
                        onClick = { /* TODO open course */ },
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White
                        )
                    ) {
                        Text(
                            text = "Continue",
                            color = course.color.first(),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }

    Text("Level: 3")
    Text("XP: 1200")
    Text("🔥 Streak: 5")

}
