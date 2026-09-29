package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.local.AppDatabase
import com.example.data.local.CourseBookingEntity
import com.example.data.local.MockScoreEntity
import com.example.data.model.Course
import com.example.data.model.CourseCategory
import com.example.data.model.MockQuiz
import com.example.data.repository.TrainingRepository
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

enum class AppTab(val title: String) {
    HOME("Home"),
    COURSES("Courses"),
    MOCK_TESTS("Mock Exams"),
    MY_PORTAL("My Bookings"),
    CAMPUS("Campus")
}

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: TrainingRepository

    init {
        val db = AppDatabase.getInstance(application)
        repository = TrainingRepository(db.appDao())
    }

    // Navigation Tab
    private val _currentTab = MutableStateFlow(AppTab.HOME)
    val currentTab: StateFlow<AppTab> = _currentTab.asStateFlow()

    // Course Search & Filtering
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedCategory = MutableStateFlow(CourseCategory.ALL)
    val selectedCategory: StateFlow<CourseCategory> = _selectedCategory.asStateFlow()

    // Active Selection Dialogs
    private val _selectedCourseDetail = MutableStateFlow<Course?>(null)
    val selectedCourseDetail: StateFlow<Course?> = _selectedCourseDetail.asStateFlow()

    private val _courseToBook = MutableStateFlow<Course?>(null)
    val courseToBook: StateFlow<Course?> = _courseToBook.asStateFlow()

    private val _bookingConfirmation = MutableStateFlow<CourseBookingEntity?>(null)
    val bookingConfirmation: StateFlow<CourseBookingEntity?> = _bookingConfirmation.asStateFlow()

    // Mock Testing State
    private val _activeQuiz = MutableStateFlow<MockQuiz?>(null)
    val activeQuiz: StateFlow<MockQuiz?> = _activeQuiz.asStateFlow()

    private val _userQuizAnswers = MutableStateFlow<Map<Int, Int>>(emptyMap())
    val userQuizAnswers: StateFlow<Map<Int, Int>> = _userQuizAnswers.asStateFlow()

    private val _quizSubmitted = MutableStateFlow(false)
    val quizSubmitted: StateFlow<Boolean> = _quizSubmitted.asStateFlow()

    private val _lastQuizScore = MutableStateFlow<MockScoreEntity?>(null)
    val lastQuizScore: StateFlow<MockScoreEntity?> = _lastQuizScore.asStateFlow()

    // Room DB Streams
    val bookings: StateFlow<List<CourseBookingEntity>> = repository.allBookings
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val savedCourseIds: StateFlow<Set<String>> = repository.allSavedCourses
        .map { list -> list.map { it.courseId }.toSet() }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptySet()
        )

    val mockScores: StateFlow<List<MockScoreEntity>> = repository.allMockScores
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    val filteredCourses: StateFlow<List<Course>> = combine(
        _selectedCategory,
        _searchQuery
    ) { category, query ->
        repository.filterCourses(category, query)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = repository.getAllCourses()
    )

    // Actions
    fun setTab(tab: AppTab) {
        _currentTab.value = tab
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setCategory(category: CourseCategory) {
        _selectedCategory.value = category
    }

    fun getFilteredCourses(): List<Course> {
        return repository.filterCourses(_selectedCategory.value, _searchQuery.value)
    }

    fun openCourseDetail(course: Course) {
        _selectedCourseDetail.value = course
    }

    fun closeCourseDetail() {
        _selectedCourseDetail.value = null
    }

    fun startBooking(course: Course) {
        _courseToBook.value = course
    }

    fun dismissBookingDialog() {
        _courseToBook.value = null
    }

    fun confirmBooking(
        course: Course,
        name: String,
        phone: String,
        email: String,
        date: String,
        deliveryMode: String,
        notes: String
    ) {
        viewModelScope.launch {
            val booking = repository.createBooking(
                course = course,
                name = name,
                phone = phone,
                email = email,
                date = date,
                deliveryMode = deliveryMode,
                specialNotes = notes
            )
            _courseToBook.value = null
            _bookingConfirmation.value = booking
        }
    }

    fun dismissBookingConfirmation() {
        _bookingConfirmation.value = null
    }

    fun cancelBooking(bookingId: Long) {
        viewModelScope.launch {
            repository.cancelBooking(bookingId)
        }
    }

    fun toggleSaveCourse(courseId: String) {
        viewModelScope.launch {
            val isSaved = savedCourseIds.value.contains(courseId)
            repository.toggleCourseSaved(courseId, isSaved)
        }
    }

    // Mock Testing Controls
    fun startQuiz(quiz: MockQuiz) {
        _activeQuiz.value = quiz
        _userQuizAnswers.value = emptyMap()
        _quizSubmitted.value = false
        _lastQuizScore.value = null
    }

    fun selectQuizAnswer(questionId: Int, optionIndex: Int) {
        if (_quizSubmitted.value) return
        val current = _userQuizAnswers.value.toMutableMap()
        current[questionId] = optionIndex
        _userQuizAnswers.value = current
    }

    fun submitQuiz() {
        val quiz = _activeQuiz.value ?: return
        val answers = _userQuizAnswers.value

        var correctCount = 0
        quiz.questions.forEach { q ->
            if (answers[q.id] == q.correctIndex) {
                correctCount++
            }
        }

        val total = quiz.questions.size
        val percent = if (total > 0) (correctCount * 100) / total else 0
        val passed = percent >= quiz.passingScorePercent

        _quizSubmitted.value = true

        viewModelScope.launch {
            val scoreEntity = MockScoreEntity(
                quizId = quiz.id,
                quizTitle = quiz.title,
                categoryName = quiz.category.displayName,
                score = correctCount,
                totalQuestions = total,
                percent = percent,
                passed = passed
            )
            repository.recordMockScore(
                quizId = quiz.id,
                quizTitle = quiz.title,
                categoryName = quiz.category.displayName,
                score = correctCount,
                totalQuestions = total,
                percent = percent,
                passed = passed
            )
            _lastQuizScore.value = scoreEntity
        }
    }

    fun closeQuiz() {
        _activeQuiz.value = null
        _userQuizAnswers.value = emptyMap()
        _quizSubmitted.value = false
        _lastQuizScore.value = null
    }

    fun clearMockHistory() {
        viewModelScope.launch {
            repository.clearMockHistory()
        }
    }
}
