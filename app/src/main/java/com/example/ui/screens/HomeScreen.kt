package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.Course
import com.example.data.model.CourseCategory
import com.example.data.repository.TrainingData
import com.example.ui.components.GrupoOutlinedButton
import com.example.ui.components.GrupoPrimaryButton
import com.example.ui.components.GrupoSecondaryButton
import com.example.ui.theme.GrupoBlue
import com.example.ui.theme.GrupoGold
import com.example.ui.theme.GrupoGreen
import com.example.ui.theme.GrupoGreenDark
import com.example.ui.theme.GrupoNavyDark
import com.example.ui.theme.GrupoNavyPrimary
import com.example.ui.viewmodel.AppTab

@Composable
fun HomeScreen(
    searchQuery: String,
    onSearchQueryChange: (String) -> Unit,
    savedCourseIds: Set<String>,
    onCourseSelected: (Course) -> Unit,
    onBookCourse: (Course) -> Unit,
    onToggleSave: (String) -> Unit,
    onNavigateTab: (AppTab) -> Unit,
    onSelectCategory: (CourseCategory) -> Unit
) {
    val context = LocalContext.current
    val featuredCourses = TrainingData.courses.take(5)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .testTag("home_screen"),
        contentPadding = PaddingValues(bottom = 28.dp)
    ) {
        // Hero Visual Banner with Real Website Background Image
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(264.dp)
            ) {
                // Official website background banner
                Image(
                    painter = painterResource(id = R.drawable.hero_banner_real),
                    contentDescription = "Grupo Training Official Background Banner",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )

                // High contrast dark gradient overlay ensuring clear visibility on mobile
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    GrupoNavyDark.copy(alpha = 0.65f),
                                    GrupoNavyDark.copy(alpha = 0.95f)
                                )
                            )
                        )
                )

                // Hero Content with official website headings optimized for mobile screens
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 14.dp),
                    verticalArrangement = Arrangement.Bottom
                ) {
                    Surface(
                        color = GrupoGreen,
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(
                            text = "GET INSPIRED! LEARN SOMETHING NEW EVERYDAY",
                            color = Color.White,
                            fontSize = 9.sp,
                            fontWeight = FontWeight.Black,
                            letterSpacing = 0.5.sp,
                            modifier = Modifier.padding(horizontal = 9.dp, vertical = 4.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "We’re Grupo,",
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Black,
                        lineHeight = 29.sp
                    )

                    Text(
                        text = "A Leading Training Provider & Education Consultancy",
                        color = GrupoGold,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 17.sp
                    )

                    Text(
                        text = "London Campus • Birmingham • Manchester • Liverpool • Glasgow",
                        color = Color.White.copy(alpha = 0.90f),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.padding(top = 2.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        GrupoPrimaryButton(
                            text = "View Courses",
                            onClick = { onNavigateTab(AppTab.COURSES) },
                            height = 40.dp,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .weight(1f)
                                .testTag("hero_explore_button")
                        )

                        GrupoSecondaryButton(
                            text = "Free Mock Tests",
                            onClick = { onNavigateTab(AppTab.MOCK_TESTS) },
                            icon = Icons.Default.Quiz,
                            height = 40.dp,
                            fontSize = 12.sp,
                            modifier = Modifier
                                .weight(1f)
                                .testTag("hero_mock_tests_button")
                        )
                    }
                }
            }
        }

        // REAL WORKING ROUNDED & RESPONSIVE SEARCH BAR COMPONENT
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                tint = GrupoGreen,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Search Courses & Licences",
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = GrupoNavyPrimary
                            )
                        }

                        if (searchQuery.isNotEmpty()) {
                            Text(
                                text = "Clear",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = GrupoGreen,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .clickable { onSearchQueryChange("") }
                                    .padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Rounded pill responsive search input with explicit high-contrast text color
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = onSearchQueryChange,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("home_search_input"),
                        placeholder = {
                            Text(
                                text = "Search SIA Door, CSCS, SERU, CCTV...",
                                fontSize = 13.sp,
                                color = Color(0xFF64748B)
                            )
                        },
                        textStyle = androidx.compose.ui.text.TextStyle(
                            color = Color(0xFF0F172A),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = null,
                                tint = if (searchQuery.isNotEmpty()) GrupoGreen else Color(0xFF64748B),
                                modifier = Modifier.size(20.dp)
                            )
                        },
                        trailingIcon = {
                            if (searchQuery.isNotEmpty()) {
                                IconButton(
                                    onClick = { onSearchQueryChange("") },
                                    modifier = Modifier.size(32.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Clear,
                                        contentDescription = "Clear search",
                                        tint = Color(0xFF475569),
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(28.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedTextColor = Color(0xFF0F172A),
                            unfocusedTextColor = Color(0xFF0F172A),
                            cursorColor = GrupoGreen,
                            focusedPlaceholderColor = Color(0xFF64748B),
                            unfocusedPlaceholderColor = Color(0xFF94A3B8),
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color(0xFFF8FAFC),
                            focusedBorderColor = GrupoGreen,
                            unfocusedBorderColor = Color(0xFFCBD5E1)
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Suggestion Chips
                    Text(
                        text = "Quick Filters:",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF64748B)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        listOf(
                            "Door Supervisor",
                            "CSCS Green Card",
                            "SERU Taxi",
                            "CCTV Operator",
                            "First Aid",
                            "Life in the UK",
                            "Teacher AET"
                        ).forEach { keyword ->
                            val isSelected = searchQuery.equals(keyword, ignoreCase = true)
                            Surface(
                                color = if (isSelected) GrupoGreen else Color(0xFFF1F5F9),
                                shape = RoundedCornerShape(16.dp),
                                border = if (isSelected) null else BorderStroke(1.dp, Color(0xFFE2E8F0)),
                                modifier = Modifier.clickable {
                                    onSearchQueryChange(keyword)
                                    onNavigateTab(AppTab.COURSES)
                                }
                            ) {
                                Text(
                                    text = keyword,
                                    fontSize = 11.sp,
                                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                    color = if (isSelected) Color.White else Color(0xFF334155),
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                                )
                            }
                        }
                    }

                    // Live Instant Search Preview on Home Screen
                    if (searchQuery.isNotBlank()) {
                        Spacer(modifier = Modifier.height(12.dp))
                        val trimmed = searchQuery.trim()
                        val matches = TrainingData.courses.filter {
                            it.title.contains(trimmed, ignoreCase = true) ||
                            it.subtitle.contains(trimmed, ignoreCase = true) ||
                            it.description.contains(trimmed, ignoreCase = true) ||
                            it.category.displayName.contains(trimmed, ignoreCase = true) ||
                            it.accreditation.contains(trimmed, ignoreCase = true)
                        }

                        if (matches.isEmpty()) {
                            Surface(
                                color = Color(0xFFF8FAFC),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = "No qualifications match \"$searchQuery\". Try checking the spelling or browse all categories below.",
                                    fontSize = 12.sp,
                                    color = Color(0xFF64748B),
                                    modifier = Modifier.padding(12.dp)
                                )
                            }
                        } else {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "${matches.size} Live Result${if (matches.size > 1) "s" else ""}:",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = GrupoGreenDark
                                )

                                Text(
                                    text = "View in Catalog →",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = GrupoBlue,
                                    modifier = Modifier
                                        .clickable { onNavigateTab(AppTab.COURSES) }
                                        .padding(4.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            matches.take(3).forEach { match ->
                                Surface(
                                    color = Color(0xFFF8FAFC),
                                    shape = RoundedCornerShape(8.dp),
                                    border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 3.dp)
                                        .clickable { onCourseSelected(match) }
                                ) {
                                    Row(
                                        modifier = Modifier.padding(10.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(
                                                text = match.title,
                                                fontSize = 13.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = GrupoNavyPrimary,
                                                maxLines = 1
                                            )
                                            Text(
                                                text = "${match.duration} • ${match.passRate} Pass • ${match.price}",
                                                fontSize = 11.sp,
                                                fontWeight = FontWeight.SemiBold,
                                                color = GrupoGreenDark
                                            )
                                        }

                                        Spacer(modifier = Modifier.width(8.dp))

                                        GrupoPrimaryButton(
                                            text = "View",
                                            onClick = { onCourseSelected(match) },
                                            height = 34.dp,
                                            fontSize = 11.sp
                                        )
                                    }
                                }
                            }

                            if (matches.size > 3) {
                                Spacer(modifier = Modifier.height(8.dp))
                                GrupoPrimaryButton(
                                    text = "See All ${matches.size} Matches in Courses",
                                    onClick = { onNavigateTab(AppTab.COURSES) },
                                    modifier = Modifier.fillMaxWidth(),
                                    height = 42.dp
                                )
                            }
                        }
                    }
                }
            }
        }

        // Trust Badges Banner
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TrustItem(
                        icon = Icons.Default.CheckCircle,
                        title = "98.7% Pass",
                        subtitle = "First Attempt",
                        tintColor = GrupoGreen
                    )
                    TrustItem(
                        icon = Icons.Default.Star,
                        title = "Free Retake",
                        subtitle = "100% Guaranteed",
                        tintColor = GrupoGold
                    )
                    TrustItem(
                        icon = Icons.Default.Security,
                        title = "SIA & CITB",
                        subtitle = "Approved Center",
                        tintColor = GrupoBlue
                    )
                }
            }
        }

        // Categories Header with Official Green Accent
        item {
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
                Text(
                    text = "Our Courses",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = GrupoNavyDark
                )
                Text(
                    text = "Industry-accredited qualifications & licences in London and nationwide",
                    fontSize = 12.sp,
                    color = Color(0xFF64748B)
                )

                Spacer(modifier = Modifier.height(10.dp))

                // Real category cards with actual photos from grupotraining.co.uk
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    RealCategoryCard(
                        modifier = Modifier.weight(1f),
                        title = "SIA Security",
                        subtitle = "Door & CCTV Licences",
                        imageResId = R.drawable.img_sia_security,
                        badgeColor = GrupoBlue,
                        onClick = {
                            onSelectCategory(CourseCategory.SECURITY)
                            onNavigateTab(AppTab.COURSES)
                        }
                    )
                    RealCategoryCard(
                        modifier = Modifier.weight(1f),
                        title = "CSCS Green Card",
                        subtitle = "Construction Health & Safety",
                        imageResId = R.drawable.img_cscs,
                        badgeColor = GrupoGold,
                        onClick = {
                            onSelectCategory(CourseCategory.CONSTRUCTION)
                            onNavigateTab(AppTab.COURSES)
                        }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    RealCategoryCard(
                        modifier = Modifier.weight(1f),
                        title = "Taxi Courses",
                        subtitle = "SERU & Topographical",
                        imageResId = R.drawable.img_taxi,
                        badgeColor = GrupoGreen,
                        onClick = {
                            onSelectCategory(CourseCategory.TAXI_TFL)
                            onNavigateTab(AppTab.COURSES)
                        }
                    )
                    RealCategoryCard(
                        modifier = Modifier.weight(1f),
                        title = "Life Skills & ESOL",
                        subtitle = "UKVI Approved & British Test",
                        imageResId = R.drawable.img_life_uk,
                        badgeColor = Color(0xFF059669),
                        onClick = {
                            onSelectCategory(CourseCategory.ESOL)
                            onNavigateTab(AppTab.COURSES)
                        }
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    RealCategoryCard(
                        modifier = Modifier.weight(1f),
                        title = "Teacher Training",
                        subtitle = "Level 3 AET & CAVA Assessor",
                        imageResId = R.drawable.img_esol,
                        badgeColor = Color(0xFF8B5CF6),
                        onClick = {
                            onSelectCategory(CourseCategory.TRAINER)
                            onNavigateTab(AppTab.COURSES)
                        }
                    )
                    RealCategoryCard(
                        modifier = Modifier.weight(1f),
                        title = "IT & Tech Support",
                        subtitle = "1st Line & Cyber Defense",
                        imageResId = R.drawable.hero_banner_real,
                        badgeColor = Color(0xFF0284C7),
                        onClick = {
                            onSelectCategory(CourseCategory.IT_TECH)
                            onNavigateTab(AppTab.COURSES)
                        }
                    )
                }
            }
        }

        // Mock Exam Feature Card
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = GrupoNavyDark),
                shape = RoundedCornerShape(14.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .clickable { onNavigateTab(AppTab.MOCK_TESTS) }
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        color = GrupoGreen,
                        shape = CircleShape,
                        modifier = Modifier.size(44.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.Quiz,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(14.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Free Exam Simulators",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                color = GrupoGold,
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    text = "FREE",
                                    fontSize = 9.sp,
                                    fontWeight = FontWeight.Black,
                                    color = Color(0xFF0F172A),
                                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                                )
                            }
                        }
                        Text(
                            text = "Practice real SIA Door, TFL SERU, and CITB Health & Safety mock tests with instant answer feedback",
                            fontSize = 11.sp,
                            color = Color(0xFFCBD5E1),
                            lineHeight = 15.sp
                        )
                    }

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Go to mock tests",
                        tint = GrupoGreen,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        }

        // Featured Courses Header
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Featured Qualifications",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = GrupoNavyPrimary
                    )
                    Text(
                        text = "Most popular courses starting weekly at Romford Road",
                        fontSize = 11.sp,
                        color = Color(0xFF64748B)
                    )
                }

                Text(
                    text = "See All (${TrainingData.courses.size})",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = GrupoGreenDark,
                    modifier = Modifier
                        .clickable { onNavigateTab(AppTab.COURSES) }
                        .padding(4.dp)
                )
            }
        }

        // Featured Course Items with Standard Grupo Buttons
        items(featuredCourses.size) { index ->
            val course = featuredCourses[index]
            val isSaved = savedCourseIds.contains(course.id)

            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 5.dp)
                    .clickable { onCourseSelected(course) }
                    .testTag("featured_course_${course.id}")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Category Thumbnail
                    Box(
                        modifier = Modifier
                            .size(76.dp)
                            .clip(RoundedCornerShape(8.dp))
                    ) {
                        Image(
                            painter = painterResource(id = course.imageResId),
                            contentDescription = course.title,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Surface(
                                color = Color(course.category.badgeColorHex).copy(alpha = 0.12f),
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    text = course.category.displayName,
                                    color = Color(course.category.badgeColorHex),
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }

                            IconButton(
                                onClick = { onToggleSave(course.id) },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(
                                    imageVector = if (isSaved) Icons.Default.Bookmark else Icons.Default.BookmarkBorder,
                                    contentDescription = "Save course",
                                    tint = if (isSaved) GrupoGreen else Color(0xFF94A3B8)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(3.dp))

                        Text(
                            text = course.title,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = GrupoNavyPrimary,
                            maxLines = 1
                        )

                        Text(
                            text = "${course.duration} • Pass Rate ${course.passRate}",
                            fontSize = 11.sp,
                            color = Color(0xFF64748B)
                        )

                        Spacer(modifier = Modifier.height(6.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = course.price,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Black,
                                color = GrupoNavyPrimary
                            )

                            GrupoPrimaryButton(
                                text = "Book Seat",
                                onClick = { onBookCourse(course) },
                                height = 34.dp,
                                fontSize = 11.sp,
                                modifier = Modifier.testTag("book_button_${course.id}")
                            )
                        }
                    }
                }
            }
        }

        // Nationwide Locations & Campus Visit Banner
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(14.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 10.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            color = GrupoGreen.copy(alpha = 0.15f),
                            shape = CircleShape,
                            modifier = Modifier.size(36.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.LocationCity,
                                    contentDescription = null,
                                    tint = GrupoGreenDark,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Column {
                            Text(
                                text = "London Head Campus & UK Centres",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = GrupoNavyPrimary
                            )
                            Text(
                                text = "252-256 Romford Road, Forest Gate, London E7 9HZ",
                                fontSize = 11.sp,
                                color = Color(0xFF475569)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        GrupoPrimaryButton(
                            text = "Directions",
                            onClick = {
                                val mapIntent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("geo:0,0?q=252-256+Romford+Road+London+E7+9HZ")
                                )
                                context.startActivity(mapIntent)
                            },
                            icon = Icons.Default.Directions,
                            height = 42.dp,
                            fontSize = 12.sp,
                            modifier = Modifier.weight(1f)
                        )

                        GrupoOutlinedButton(
                            text = "020 3983 4565",
                            onClick = {
                                val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                                    data = Uri.parse("tel:02039834565")
                                }
                                context.startActivity(dialIntent)
                            },
                            icon = Icons.Default.Phone,
                            height = 42.dp,
                            fontSize = 12.sp,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TrustItem(
    icon: ImageVector,
    title: String,
    subtitle: String,
    tintColor: Color
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = tintColor,
            modifier = Modifier.size(22.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = GrupoNavyPrimary
        )
        Text(
            text = subtitle,
            fontSize = 10.sp,
            color = Color(0xFF64748B)
        )
    }
}

@Composable
private fun RealCategoryCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    imageResId: Int,
    badgeColor: Color,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onClick() }
            .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(12.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(84.dp)
            ) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                Surface(
                    color = badgeColor.copy(alpha = 0.90f),
                    shape = RoundedCornerShape(bottomEnd = 8.dp),
                    modifier = Modifier.align(Alignment.TopStart)
                ) {
                    Text(
                        text = title,
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }
            Column(modifier = Modifier.padding(8.dp)) {
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = GrupoNavyPrimary,
                    maxLines = 1
                )
                Text(
                    text = subtitle,
                    fontSize = 10.sp,
                    color = Color(0xFF64748B),
                    maxLines = 1
                )
            }
        }
    }
}
