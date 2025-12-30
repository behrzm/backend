package com.example.pro.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// ---------- DATA ----------
data class Achievement(
    val title: String,
    val description: String,
    val progress: Float,
    val unlocked: Boolean,
    val gradient: List<Color>
)

// ---------- MOCK DATA ----------
private val achievements = listOf(
    Achievement(
        "First Course",
        "Start your first course",
        1f,
        true,
        listOf(Color(0xFF56CCF2), Color(0xFF2F80ED))
    ),
    Achievement(
        "50% Progress",
        "Reach 50% overall progress",
        0.52f,
        true,
        listOf(Color(0xFF6A11CB), Color(0xFF2575FC))
    ),
    Achievement(
        "Streak x7",
        "Study 7 days in a row",
        0.7f,
        false,
        listOf(Color(0xFFFF512F), Color(0xFFDD2476))
    ),
    Achievement(
        "Course Master",
        "Complete a full course",
        0.3f,
        false,
        listOf(Color(0xFF11998E), Color(0xFF38EF7D))
    )
)

// ---------- UI ----------
@Composable
fun AchievementsScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
    ) {

        Text(
            text = "Achievements",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Your learning milestones",
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )

        Spacer(modifier = Modifier.height(20.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(achievements) { index, achievement ->

                // новая строка каждые 2 элемента
                if (index % 2 == 0) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {

                        AchievementCard(
                            achievement = achievement,
                            modifier = Modifier.weight(1f)
                        )

                        if (index + 1 < achievements.size) {
                            AchievementCard(
                                achievement = achievements[index + 1],
                                modifier = Modifier.weight(1f)
                            )
                        } else {
                            Spacer(modifier = Modifier.weight(1f))
                        }
                    }
                }
            }
        }
    }
}

// ---------- CARD ----------
@Composable
private fun AchievementCard(
    achievement: Achievement,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(24.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Box(
            modifier = Modifier
                .background(
                    if (achievement.unlocked)
                        Brush.linearGradient(achievement.gradient)
                    else
                        Brush.linearGradient(
                            listOf(
                                Color.Gray.copy(alpha = 0.4f),
                                Color.Gray.copy(alpha = 0.6f)
                            )
                        )
                )
                .padding(16.dp)
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .background(
                            Color.White.copy(
                                alpha = if (achievement.unlocked) 0.9f else 0.4f
                            ),
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (achievement.unlocked) "🏆" else "🔒",
                        fontSize = 26.sp
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = achievement.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = achievement.description,
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.85f),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(10.dp))

                LinearProgressIndicator(
                    progress = { achievement.progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(6.dp),
                    color = Color.White,
                    trackColor = Color.White.copy(alpha = 0.3f)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "${(achievement.progress * 100).toInt()}%",
                    fontSize = 12.sp,
                    color = Color.White
                )
            }
        }
    }
}
