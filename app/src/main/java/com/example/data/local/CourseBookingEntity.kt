package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course_bookings")
data class CourseBookingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val bookingReference: String,
    val courseId: String,
    val courseTitle: String,
    val categoryName: String,
    val price: String,
    val candidateName: String,
    val candidatePhone: String,
    val candidateEmail: String,
    val preferredDate: String,
    val deliveryMode: String,
    val specialRequirements: String,
    val bookingStatus: String = "Confirmed - Check-in on Arrival",
    val createdAt: Long = System.currentTimeMillis()
)
