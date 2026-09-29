package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.model.CourseCategory
import com.example.data.repository.TrainingData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

    @Test
    fun `read string from context matches Grupo Training`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Grupo Training", appName)
    }

    @Test
    fun `training courses exist for all key vocational categories`() {
        val courses = TrainingData.courses
        assertTrue("Courses list should not be empty", courses.isNotEmpty())

        val securityCourses = courses.filter { it.category == CourseCategory.SECURITY }
        val cscsCourses = courses.filter { it.category == CourseCategory.CONSTRUCTION }
        val taxiCourses = courses.filter { it.category == CourseCategory.TAXI_TFL }
        val esolCourses = courses.filter { it.category == CourseCategory.ESOL }

        assertTrue("Should have SIA security courses", securityCourses.isNotEmpty())
        assertTrue("Should have CSCS construction courses", cscsCourses.isNotEmpty())
        assertTrue("Should have TFL SERU taxi courses", taxiCourses.isNotEmpty())
        assertTrue("Should have ESOL / Life skills courses", esolCourses.isNotEmpty())
    }

    @Test
    fun `mock quizzes are properly structured with valid answer indexes`() {
        val quizzes = TrainingData.mockQuizzes
        assertTrue("Quizzes should not be empty", quizzes.isNotEmpty())

        quizzes.forEach { quiz ->
            assertEquals(10, quiz.questions.size)
            quiz.questions.forEach { question ->
                assertTrue(
                    "Correct index should be within bounds",
                    question.correctIndex in 0 until question.options.size
                )
                assertFalse("Question text should not be blank", question.question.isBlank())
                assertFalse("Explanation should not be blank", question.explanation.isBlank())
            }
        }
    }
}
