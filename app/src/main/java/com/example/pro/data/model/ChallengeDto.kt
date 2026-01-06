package com.example.pro.data.model

data class ChallengeDto(
    val id: String,
    val title: String,
    val description: String,
    val difficulty: String,
    val category: String,
    val basePoints: Int,
    val starterCode: String?,
    val isActive: Boolean
)
