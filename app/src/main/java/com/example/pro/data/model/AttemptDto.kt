package com.example.pro.data.model

data class AttemptDto(
    val id: String,
    val userId: String,
    val challengeId: String,
    val submittedCode: String,
    val score: Int,
    val passed: Boolean,
    val submittedAt: String
)
