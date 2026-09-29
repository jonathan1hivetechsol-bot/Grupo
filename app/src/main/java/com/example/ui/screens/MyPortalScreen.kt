package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DeleteOutline
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.CourseBookingEntity
import com.example.data.local.MockScoreEntity
import com.example.data.model.Course
import com.example.data.repository.TrainingData
import com.example.ui.components.GrupoDangerButton
import com.example.ui.components.GrupoOutlinedButton
import com.example.ui.components.GrupoPrimaryButton
import com.example.ui.theme.GrupoEmeraldDark
import com.example.ui.theme.GrupoGoldAccent
import com.example.ui.theme.GrupoGoldDark
import com.example.ui.theme.GrupoGreen
import com.example.ui.theme.GrupoNavyDark
import com.example.ui.theme.GrupoNavyPrimary
import com.example.ui.viewmodel.AppTab
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPortalScreen(
    bookings: List<CourseBookingEntity>,
    savedCourseIds: Set<String>,
    mockScores: List<MockScoreEntity>,
    onCourseSelected: (Course) -> Unit,
    onBookCourse: (Course) -> Unit,
    onCancelBooking: (Long) -> Unit,
    onToggleSave: (String) -> Unit,
    onClearMockHistory: () -> Unit,
    onNavigateTab: (AppTab) -> Unit
) {
    val context = LocalContext.current
    var selectedSubTab by remember { mutableIntStateOf(0) }
    val tabTitles = listOf(
        "Bookings (${bookings.size})",
        "Saved (${savedCourseIds.size})",
        "Scores (${mockScores.size})",
        "Checklist"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .testTag("my_portal_screen")
    ) {
        // Tab Row
        Surface(color = Color.White, shadowElevation = 2.dp) {
            PrimaryTabRow(
                selectedTabIndex = selectedSubTab,
                containerColor = Color.White,
                contentColor = GrupoNavyPrimary,
                indicator = {
                    TabRowDefaults.PrimaryIndicator(
                        modifier = Modifier.tabIndicatorOffset(selectedSubTab),
                        color = GrupoNavyPrimary
                    )
                }
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedSubTab == index,
                        onClick = { selectedSubTab = index },
                        text = {
                            Text(
                                text = title,
                                fontSize = 12.sp,
                                fontWeight = if (selectedSubTab == index) FontWeight.Bold else FontWeight.Medium
                            )
                        },
                        modifier = Modifier.testTag("portal_subtab_$index")
                    )
                }
            }
        }

        // SubTab Content
        when (selectedSubTab) {
            0 -> BookingsTabContent(
                bookings = bookings,
                onCancelBooking = onCancelBooking,
                onExploreCourses = { onNavigateTab(AppTab.COURSES) }
            )
            1 -> SavedCoursesTabContent(
                savedCourseIds = savedCourseIds,
                onCourseSelected = onCourseSelected,
                onBookCourse = onBookCourse,
                onToggleSave = onToggleSave,
                onExploreCourses = { onNavigateTab(AppTab.COURSES) }
            )
            2 -> TestScoresTabContent(
                mockScores = mockScores,
                onClearHistory = onClearMockHistory,
                onStartPractice = { onNavigateTab(AppTab.MOCK_TESTS) }
            )
            3 -> ChecklistTabContent()
        }
    }
}

@Composable
private fun BookingsTabContent(
    bookings: List<CourseBookingEntity>,
    onCancelBooking: (Long) -> Unit,
    onExploreCourses: () -> Unit
) {
    val context = LocalContext.current

    if (bookings.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE2E8F0)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Assignment,
                        contentDescription = null,
                        tint = Color(0xFF64748B),
                        modifier = Modifier.size(32.dp)
                    )
                }
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "No Bookings Yet",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = GrupoNavyPrimary
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Reserve your seat in upcoming SIA, CSCS, or TFL SERU classes at our London Romford Road campus.",
                    fontSize = 12.sp,
                    color = Color(0xFF64748B),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                GrupoPrimaryButton(
                    text = "Explore Courses",
                    onClick = onExploreCourses,
                    height = 44.dp
                )
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(bookings, key = { it.id }) { booking ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("booking_card_${booking.id}")
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                color = GrupoEmeraldDark.copy(alpha = 0.12f),
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text(
                                    text = booking.bookingStatus,
                                    color = GrupoEmeraldDark,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }

                            Text(
                                text = booking.bookingReference,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Black,
                                color = GrupoNavyPrimary
                            )
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = booking.courseTitle,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = GrupoNavyPrimary
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Candidate: ",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF64748B)
                            )
                            Text(
                                text = booking.candidateName,
                                fontSize = 12.sp,
                                color = Color(0xFF1E293B)
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Start Date: ",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF64748B)
                            )
                            Text(
                                text = booking.preferredDate,
                                fontSize = 12.sp,
                                color = Color(0xFF1E293B)
                            )
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Venue: ",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF64748B)
                            )
                            Text(
                                text = "252-256 Romford Rd, E7 9HZ",
                                fontSize = 12.sp,
                                color = Color(0xFF1E293B)
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = booking.price,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = GrupoNavyPrimary
                            )

                            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                GrupoOutlinedButton(
                                    text = "Call Desk",
                                    onClick = {
                                        val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                                            data = Uri.parse("tel:02039834565")
                                        }
                                        context.startActivity(dialIntent)
                                    },
                                    icon = Icons.Default.Phone,
                                    height = 36.dp,
                                    fontSize = 11.sp,
                                    borderColor = Color(0xFFCBD5E1),
                                    textColor = GrupoNavyDark
                                )

                                IconButton(
                                    onClick = { onCancelBooking(booking.id) },
                                    modifier = Modifier.testTag("cancel_booking_${booking.id}")
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.DeleteOutline,
                                        contentDescription = "Cancel Booking",
                                        tint = Color(0xFFEF4444)
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun SavedCoursesTabContent(
    savedCourseIds: Set<String>,
    onCourseSelected: (Course) -> Unit,
    onBookCourse: (Course) -> Unit,
    onToggleSave: (String) -> Unit,
    onExploreCourses: () -> Unit
) {
    val savedCourses = TrainingData.courses.filter { savedCourseIds.contains(it.id) }

    if (savedCourses.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE2E8F0)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Bookmark,
                        contentDescription = null,
                        tint = Color(0xFF64748B),
                        modifier = Modifier.size(32.dp)
                    )
                }
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "No Saved Courses",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = GrupoNavyPrimary
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Bookmark courses while exploring to compare modules and tuition fees.",
                    fontSize = 12.sp,
                    color = Color(0xFF64748B),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                GrupoPrimaryButton(
                    text = "Browse Courses",
                    onClick = onExploreCourses,
                    height = 44.dp
                )
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(savedCourses, key = { it.id }) { course ->
                CourseCard(
                    course = course,
                    isSaved = true,
                    onCourseSelected = { onCourseSelected(course) },
                    onBookCourse = { onBookCourse(course) },
                    onToggleSave = { onToggleSave(course.id) }
                )
            }
        }
    }
}

@Composable
private fun TestScoresTabContent(
    mockScores: List<MockScoreEntity>,
    onClearHistory: () -> Unit,
    onStartPractice: () -> Unit
) {
    val dateFormat = remember { SimpleDateFormat("dd MMM, HH:mm", Locale.getDefault()) }

    if (mockScores.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE2E8F0)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Quiz,
                        contentDescription = null,
                        tint = Color(0xFF64748B),
                        modifier = Modifier.size(32.dp)
                    )
                }
                Spacer(modifier = Modifier.height(14.dp))
                Text(
                    text = "No Practice Tests Taken Yet",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = GrupoNavyPrimary
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(
                    text = "Take free 10-question mock tests for SIA Door Supervisor, TFL SERU, and CSCS Green Card.",
                    fontSize = 12.sp,
                    color = Color(0xFF64748B),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                GrupoPrimaryButton(
                    text = "Start Mock Test",
                    onClick = onStartPractice,
                    height = 44.dp
                )
            }
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Recent Test Attempts (${mockScores.size})",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        color = GrupoNavyPrimary
                    )

                    GrupoDangerButton(
                        text = "Clear History",
                        onClick = onClearHistory,
                        icon = Icons.Default.Delete,
                        height = 34.dp
                    )
                }
            }

            items(mockScores, key = { it.id }) { score ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(12.dp),
                    border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = score.quizTitle,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = GrupoNavyPrimary
                            )
                            Text(
                                text = "${score.categoryName} • ${dateFormat.format(Date(score.timestamp))}",
                                fontSize = 11.sp,
                                color = Color(0xFF64748B)
                            )
                        }

                        Surface(
                            color = if (score.passed) Color(0xFFECFDF5) else Color(0xFFFEF2F2),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Column(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "${score.percent}%",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Black,
                                    color = if (score.passed) GrupoEmeraldDark else Color(0xFFB91C1C)
                                )
                                Text(
                                    text = if (score.passed) "PASSED" else "FAILED",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (score.passed) GrupoEmeraldDark else Color(0xFFB91C1C)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ChecklistTabContent() {
    val items = listOf(
        Pair("Valid Photographic ID", "Original UK Passport, Foreign Passport with Visa/BRP, or UK Photocard Driving Licence."),
        Pair("Proof of Address (Dated within 3 months)", "Bank statement, utility bill, council tax statement, or HMRC letter showing current address."),
        Pair("Two Passport-Sized Photographs", "Recent color photographs with white background required for candidate file & accreditation body."),
        Pair("National Insurance (NI) Number", "Required for SIA licence submission and CITB Pearson VUE exam registration."),
        Pair("First Aid (EFAW) Certificate", "Mandatory prerequisite for SIA Door Supervisor and Security Officer courses (can be taken together at Grupo)."),
        Pair("Notebook & Pen", "For classroom note-taking and revision mock tests.")
    )

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = GrupoNavyPrimary),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            Icons.Default.Info,
                            contentDescription = null,
                            tint = GrupoGoldAccent,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Exam Day Mandatory Requirements",
                            color = Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Text(
                        text = "UK regulatory bodies (SIA, CITB, TfL) require strict identity checks before you can enter the examination room at our Romford Road campus.",
                        color = Color.White.copy(alpha = 0.85f),
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                }
            }
        }

        items(items) { item ->
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(10.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(14.dp),
                    verticalAlignment = Alignment.Top
                ) {
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .clip(CircleShape)
                            .background(GrupoEmeraldDark.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = null,
                            tint = GrupoEmeraldDark,
                            modifier = Modifier.size(16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column {
                        Text(
                            text = item.first,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = GrupoNavyPrimary
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = item.second,
                            fontSize = 12.sp,
                            color = Color(0xFF475569),
                            lineHeight = 16.sp
                        )
                    }
                }
            }
        }
    }
}
