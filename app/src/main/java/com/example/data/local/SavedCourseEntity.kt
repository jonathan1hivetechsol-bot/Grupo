package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_courses")
data class SavedCourseEntity(
    @PrimaryKey
    val courseId: String,
    val savedAt: Long = System.currentTimeMillis()
)
