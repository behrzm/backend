package com.example.pro.ai

class AiHintService {

    fun getHint(
        taskDescription: String,
        userAttempt: String,
        level: Int
    ): String {
        return when (level) {
            1 -> "Think about which HTML tag is responsible for grouping user input."
            2 -> "Recall how input fields and buttons are usually placed in HTML forms."
            else -> "Make sure your elements are wrapped in the correct container tag."
        }
    }
}
