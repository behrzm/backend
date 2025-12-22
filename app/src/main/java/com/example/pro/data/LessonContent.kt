package com.example.pro.data

data class LessonContent(
    val title: String,
    val description: String,
    val topic: String,
    val content: String
)

val htmlLessons = mapOf(
    1 to LessonContent(
        "Введение в HTML",
        "Первый шаг в изучении веб-разработки.",
        "Что такое HTML",
        "<h1>Hello HTML</h1>"
    ),
    2 to LessonContent(
        "Основные теги",
        "Базовые HTML теги",
        "Теги",
        "<p>Paragraph</p>"
    )
)
