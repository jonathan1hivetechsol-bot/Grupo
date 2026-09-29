package com.example.data.model

data class MockQuestion(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

data class MockQuiz(
    val id: String,
    val title: String,
    val category: CourseCategory,
    val description: String,
    val passingScorePercent: Int = 80,
    val timeLimitMinutes: Int = 15,
    val questions: List<MockQuestion>
)
