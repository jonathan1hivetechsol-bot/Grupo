package com.example.data.model

import com.example.R

enum class CourseCategory(val displayName: String, val badgeColorHex: Long) {
    ALL("All Courses", 0xFF3452FF),
    SECURITY("SIA Security", 0xFF3452FF),
    CONSTRUCTION("CSCS Construction", 0xFFD6B135),
    TAXI_TFL("TFL SERU & Taxi", 0xFF54C037),
    TRAINER("Teacher & Assessor", 0xFF8B5CF6),
    ESOL("ESOL & Life in UK", 0xFF059669),
    HEALTH_CARE("Health & Social Care", 0xFFE11D48),
    IT_TECH("IT & Cyber Skills", 0xFF0284C7)
}

data class Course(
    val id: String,
    val title: String,
    val subtitle: String,
    val category: CourseCategory,
    val price: String,
    val duration: String,
    val accreditation: String,
    val passRate: String,
    val nextDates: List<String>,
    val deliveryMode: String,
    val campus: String = "252-256 Romford Road, London E7 9HZ",
    val description: String,
    val syllabus: List<String>,
    val requirements: List<String>,
    val careerRoles: List<String>,
    val imageResId: Int = R.drawable.img_sia_security,
    val includesExam: Boolean = true,
    val freeRetakeIncluded: Boolean = true
)
