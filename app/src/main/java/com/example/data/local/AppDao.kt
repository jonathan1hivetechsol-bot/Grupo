package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {
    // Bookings
    @Query("SELECT * FROM course_bookings ORDER BY createdAt DESC")
    fun getAllBookings(): Flow<List<CourseBookingEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBooking(booking: CourseBookingEntity): Long

    @Query("DELETE FROM course_bookings WHERE id = :id")
    suspend fun deleteBookingById(id: Long)

    // Saved / Bookmarked Courses
    @Query("SELECT * FROM saved_courses ORDER BY savedAt DESC")
    fun getAllSavedCourses(): Flow<List<SavedCourseEntity>>

    @Query("SELECT EXISTS(SELECT 1 FROM saved_courses WHERE courseId = :courseId)")
    fun isCourseSaved(courseId: String): Flow<Boolean>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSavedCourse(savedCourse: SavedCourseEntity)

    @Query("DELETE FROM saved_courses WHERE courseId = :courseId")
    suspend fun removeSavedCourse(courseId: String)

    // Mock Exam Scores
    @Query("SELECT * FROM mock_scores ORDER BY timestamp DESC")
    fun getAllMockScores(): Flow<List<MockScoreEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMockScore(score: MockScoreEntity): Long

    @Query("DELETE FROM mock_scores")
    suspend fun clearMockScores()
}
