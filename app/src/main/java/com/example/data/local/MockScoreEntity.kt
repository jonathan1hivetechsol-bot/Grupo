package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mock_scores")
data class MockScoreEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val quizId: String,
    val quizTitle: String,
    val categoryName: String,
    val score: Int,
    val totalQuestions: Int,
    val percent: Int,
    val passed: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)
