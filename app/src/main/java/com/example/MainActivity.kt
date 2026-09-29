package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.GrupoBottomBar
import com.example.ui.components.GrupoTopBar
import com.example.ui.screens.BookingConfirmationDialog
import com.example.ui.screens.BookingDialog
import com.example.ui.screens.CampusScreen
import com.example.ui.screens.CourseDetailDialog
import com.example.ui.screens.CoursesScreen
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.MockExamScreen
import com.example.ui.screens.MyPortalScreen
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.AppTab
import com.example.ui.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                GrupoApp()
            }
        }
    }
}

@Composable
fun GrupoApp(viewModel: MainViewModel = viewModel()) {
    val currentTab by viewModel.currentTab.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val selectedCategory by viewModel.selectedCategory.collectAsStateWithLifecycle()
    val selectedCourseDetail by viewModel.selectedCourseDetail.collectAsStateWithLifecycle()
    val courseToBook by viewModel.courseToBook.collectAsStateWithLifecycle()
    val bookingConfirmation by viewModel.bookingConfirmation.collectAsStateWithLifecycle()

    val activeQuiz by viewModel.activeQuiz.collectAsStateWithLifecycle()
    val userQuizAnswers by viewModel.userQuizAnswers.collectAsStateWithLifecycle()
    val quizSubmitted by viewModel.quizSubmitted.collectAsStateWithLifecycle()
    val lastQuizScore by viewModel.lastQuizScore.collectAsStateWithLifecycle()

    val bookings by viewModel.bookings.collectAsStateWithLifecycle()
    val savedCourseIds by viewModel.savedCourseIds.collectAsStateWithLifecycle()
    val mockScores by viewModel.mockScores.collectAsStateWithLifecycle()
    val filteredCourses by viewModel.filteredCourses.collectAsStateWithLifecycle()

    // Handle back button gracefully
    BackHandler(
        enabled = activeQuiz != null || selectedCourseDetail != null || courseToBook != null || currentTab != AppTab.HOME
    ) {
        when {
            courseToBook != null -> viewModel.dismissBookingDialog()
            selectedCourseDetail != null -> viewModel.closeCourseDetail()
            activeQuiz != null -> viewModel.closeQuiz()
            currentTab != AppTab.HOME -> viewModel.setTab(AppTab.HOME)
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            GrupoTopBar(
                savedCount = savedCourseIds.size,
                onSavedClicked = { viewModel.setTab(AppTab.MY_PORTAL) }
            )
        },
        bottomBar = {
            GrupoBottomBar(
                currentTab = currentTab,
                onTabSelected = { viewModel.setTab(it) },
                bookingsCount = bookings.size
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (currentTab) {
                AppTab.HOME -> HomeScreen(
                    searchQuery = searchQuery,
                    onSearchQueryChange = { viewModel.setSearchQuery(it) },
                    savedCourseIds = savedCourseIds,
                    onCourseSelected = { viewModel.openCourseDetail(it) },
                    onBookCourse = { viewModel.startBooking(it) },
                    onToggleSave = { viewModel.toggleSaveCourse(it) },
                    onNavigateTab = { viewModel.setTab(it) },
                    onSelectCategory = { viewModel.setCategory(it) }
                )
                AppTab.COURSES -> CoursesScreen(
                    courses = filteredCourses,
                    selectedCategory = selectedCategory,
                    searchQuery = searchQuery,
                    savedCourseIds = savedCourseIds,
                    onSelectCategory = { viewModel.setCategory(it) },
                    onSearchQueryChange = { viewModel.setSearchQuery(it) },
                    onCourseSelected = { viewModel.openCourseDetail(it) },
                    onBookCourse = { viewModel.startBooking(it) },
                    onToggleSave = { viewModel.toggleSaveCourse(it) }
                )
                AppTab.MOCK_TESTS -> MockExamScreen(
                    activeQuiz = activeQuiz,
                    userAnswers = userQuizAnswers,
                    isSubmitted = quizSubmitted,
                    lastScore = lastQuizScore,
                    pastScores = mockScores,
                    onStartQuiz = { viewModel.startQuiz(it) },
                    onSelectAnswer = { qId, optIdx -> viewModel.selectQuizAnswer(qId, optIdx) },
                    onSubmitQuiz = { viewModel.submitQuiz() },
                    onCloseQuiz = { viewModel.closeQuiz() }
                )
                AppTab.MY_PORTAL -> MyPortalScreen(
                    bookings = bookings,
                    savedCourseIds = savedCourseIds,
                    mockScores = mockScores,
                    onCourseSelected = { viewModel.openCourseDetail(it) },
                    onBookCourse = { viewModel.startBooking(it) },
                    onCancelBooking = { viewModel.cancelBooking(it) },
                    onToggleSave = { viewModel.toggleSaveCourse(it) },
                    onClearMockHistory = { viewModel.clearMockHistory() },
                    onNavigateTab = { viewModel.setTab(it) }
                )
                AppTab.CAMPUS -> CampusScreen()
            }
        }
    }

    // Modal Overlays
    selectedCourseDetail?.let { course ->
        CourseDetailDialog(
            course = course,
            isSaved = savedCourseIds.contains(course.id),
            onDismiss = { viewModel.closeCourseDetail() },
            onBookCourse = { viewModel.startBooking(it) },
            onToggleSave = { viewModel.toggleSaveCourse(it) }
        )
    }

    courseToBook?.let { course ->
        BookingDialog(
            course = course,
            onDismiss = { viewModel.dismissBookingDialog() },
            onConfirm = { c, name, phone, email, date, mode, notes ->
                viewModel.confirmBooking(c, name, phone, email, date, mode, notes)
            }
        )
    }

    bookingConfirmation?.let { booking ->
        BookingConfirmationDialog(
            booking = booking,
            onDismiss = { viewModel.dismissBookingConfirmation() }
        )
    }
}
