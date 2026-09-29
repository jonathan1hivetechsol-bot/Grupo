package com.example.data.repository

import com.example.data.local.AppDao
import com.example.data.local.CourseBookingEntity
import com.example.data.local.MockScoreEntity
import com.example.data.local.SavedCourseEntity
import com.example.data.model.Course
import com.example.data.model.CourseCategory
import com.example.data.model.MockQuiz
import kotlinx.coroutines.flow.Flow
import kotlin.random.Random

class TrainingRepository(private val appDao: AppDao) {

    val allBookings: Flow<List<CourseBookingEntity>> = appDao.getAllBookings()
    val allSavedCourses: Flow<List<SavedCourseEntity>> = appDao.getAllSavedCourses()
    val allMockScores: Flow<List<MockScoreEntity>> = appDao.getAllMockScores()

    fun getAllCourses(): List<Course> = TrainingData.courses

    fun getCourseById(courseId: String): Course? =
        TrainingData.courses.find { it.id == courseId }

    fun filterCourses(category: CourseCategory, query: String = ""): List<Course> {
        val trimmed = query.trim()
        return TrainingData.courses.filter { course ->
            val matchesCategory = (category == CourseCategory.ALL) || (course.category == category)
            val matchesQuery = trimmed.isBlank() ||
                course.title.contains(trimmed, ignoreCase = true) ||
                course.subtitle.contains(trimmed, ignoreCase = true) ||
                course.description.contains(trimmed, ignoreCase = true) ||
                course.accreditation.contains(trimmed, ignoreCase = true) ||
                course.category.displayName.contains(trimmed, ignoreCase = true)
            matchesCategory && matchesQuery
        }
    }

    fun getAllMockQuizzes(): List<MockQuiz> = TrainingData.mockQuizzes

    fun getMockQuizById(quizId: String): MockQuiz? =
        TrainingData.mockQuizzes.find { it.id == quizId }

    fun isCourseSaved(courseId: String): Flow<Boolean> = appDao.isCourseSaved(courseId)

    suspend fun toggleCourseSaved(courseId: String, currentlySaved: Boolean) {
        if (currentlySaved) {
            appDao.removeSavedCourse(courseId)
        } else {
            appDao.insertSavedCourse(SavedCourseEntity(courseId = courseId))
        }
    }

    suspend fun createBooking(
        course: Course,
        name: String,
        phone: String,
        email: String,
        date: String,
        deliveryMode: String,
        specialNotes: String
    ): CourseBookingEntity {
        val randomRefSuffix = Random.nextInt(100000, 999999)
        val reference = "GT-$randomRefSuffix"

        val entity = CourseBookingEntity(
            bookingReference = reference,
            courseId = course.id,
            courseTitle = course.title,
            categoryName = course.category.displayName,
            price = course.price,
            candidateName = name.trim(),
            candidatePhone = phone.trim(),
            candidateEmail = email.trim(),
            preferredDate = date,
            deliveryMode = deliveryMode,
            specialRequirements = specialNotes.trim(),
            bookingStatus = "Confirmed - Check-in on Arrival"
        )
        val generatedId = appDao.insertBooking(entity)
        return entity.copy(id = generatedId)
    }

    suspend fun cancelBooking(bookingId: Long) {
        appDao.deleteBookingById(bookingId)
    }

    suspend fun recordMockScore(
        quizId: String,
        quizTitle: String,
        categoryName: String,
        score: Int,
        totalQuestions: Int,
        percent: Int,
        passed: Boolean
    ): Long {
        val scoreEntity = MockScoreEntity(
            quizId = quizId,
            quizTitle = quizTitle,
            categoryName = categoryName,
            score = score,
            totalQuestions = totalQuestions,
            percent = percent,
            passed = passed
        )
        return appDao.insertMockScore(scoreEntity)
    }

    suspend fun clearMockHistory() {
        appDao.clearMockScores()
    }
}
