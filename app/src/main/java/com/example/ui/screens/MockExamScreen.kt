package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Engineering
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.LocalTaxi
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.MockScoreEntity
import com.example.data.model.CourseCategory
import com.example.data.model.MockQuiz
import com.example.data.repository.TrainingData
import com.example.ui.components.GrupoOutlinedButton
import com.example.ui.components.GrupoPrimaryButton
import com.example.ui.theme.GrupoEmeraldDark
import com.example.ui.theme.GrupoGoldAccent
import com.example.ui.theme.GrupoGoldDark
import com.example.ui.theme.GrupoGreen
import com.example.ui.theme.GrupoNavyDark
import com.example.ui.theme.GrupoNavyPrimary

@Composable
fun MockExamScreen(
    activeQuiz: MockQuiz?,
    userAnswers: Map<Int, Int>,
    isSubmitted: Boolean,
    lastScore: MockScoreEntity?,
    pastScores: List<MockScoreEntity>,
    onStartQuiz: (MockQuiz) -> Unit,
    onSelectAnswer: (Int, Int) -> Unit,
    onSubmitQuiz: () -> Unit,
    onCloseQuiz: () -> Unit
) {
    if (activeQuiz != null) {
        ActiveQuizView(
            quiz = activeQuiz,
            userAnswers = userAnswers,
            isSubmitted = isSubmitted,
            lastScore = lastScore,
            onSelectAnswer = onSelectAnswer,
            onSubmitQuiz = onSubmitQuiz,
            onCloseQuiz = onCloseQuiz
        )
    } else {
        MockExamHubView(
            quizzes = TrainingData.mockQuizzes,
            pastScores = pastScores,
            onStartQuiz = onStartQuiz
        )
    }
}

@Composable
private fun MockExamHubView(
    quizzes: List<MockQuiz>,
    pastScores: List<MockScoreEntity>,
    onStartQuiz: (MockQuiz) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .testTag("mock_exam_hub"),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Hub Banner
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = GrupoNavyPrimary),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Surface(
                        color = GrupoGoldAccent,
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = "FREE EXAM REVISION LAB",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = GrupoNavyDark,
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Practice Official UK Exam Questions",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Text(
                        text = "Prepare for your SIA Door Supervisor, TFL SERU, CSCS Green Card, and Life in the UK tests. Get instant answers and statutory explanations.",
                        fontSize = 12.sp,
                        color = Color.White.copy(alpha = 0.85f),
                        lineHeight = 16.sp,
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(
                            text = "✓ 80% Pass Threshold",
                            fontSize = 11.sp,
                            color = GrupoGoldAccent,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "✓ Detailed Explanations",
                            fontSize = 11.sp,
                            color = GrupoGoldAccent,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }

        // Section Title
        item {
            Text(
                text = "Available Practice Tests",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = GrupoNavyPrimary
            )
        }

        // List of quizzes
        items(quizzes, key = { it.id }) { quiz ->
            val quizScores = pastScores.filter { it.quizId == quiz.id }
            val highestScore = quizScores.maxByOrNull { it.percent }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("quiz_card_${quiz.id}"),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = Color(quiz.category.badgeColorHex).copy(alpha = 0.12f),
                            shape = RoundedCornerShape(6.dp)
                        ) {
                            Text(
                                text = quiz.category.displayName,
                                color = Color(quiz.category.badgeColorHex),
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }

                        if (highestScore != null) {
                            Surface(
                                color = if (highestScore.passed) GrupoEmeraldDark.copy(alpha = 0.15f) else Color(0xFFFEE2E2),
                                shape = RoundedCornerShape(6.dp)
                            ) {
                                Text(
                                    text = "Best: ${highestScore.percent}% ${if (highestScore.passed) "PASS" else "FAIL"}",
                                    color = if (highestScore.passed) GrupoEmeraldDark else Color(0xFFB91C1C),
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = quiz.title,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = GrupoNavyPrimary
                    )

                    Text(
                        text = quiz.description,
                        fontSize = 12.sp,
                        color = Color(0xFF475569),
                        modifier = Modifier.padding(vertical = 4.dp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${quiz.questions.size} Questions • 15 Mins",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF64748B)
                        )

                        GrupoPrimaryButton(
                            text = "Start Test",
                            onClick = { onStartQuiz(quiz) },
                            icon = Icons.Default.PlayArrow,
                            height = 38.dp,
                            fontSize = 12.sp,
                            modifier = Modifier.testTag("start_quiz_button_${quiz.id}")
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ActiveQuizView(
    quiz: MockQuiz,
    userAnswers: Map<Int, Int>,
    isSubmitted: Boolean,
    lastScore: MockScoreEntity?,
    onSelectAnswer: (Int, Int) -> Unit,
    onSubmitQuiz: () -> Unit,
    onCloseQuiz: () -> Unit
) {
    val totalQuestions = quiz.questions.size
    val answeredCount = userAnswers.size
    val progress = if (totalQuestions > 0) answeredCount.toFloat() / totalQuestions else 0f

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .testTag("active_quiz_view")
    ) {
        // Active Quiz Top Bar
        Surface(
            color = GrupoNavyPrimary,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = quiz.title,
                            color = Color.White,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 1
                        )
                        Text(
                            text = if (isSubmitted) "Test Complete - Review Explanations" else "Answered $answeredCount of $totalQuestions",
                            color = GrupoGoldAccent,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    IconButton(onClick = onCloseQuiz) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Exit Quiz",
                            tint = Color.White
                        )
                    }
                }

                if (!isSubmitted) {
                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(RoundedCornerShape(3.dp)),
                        color = GrupoGoldAccent,
                        trackColor = Color.White.copy(alpha = 0.2f),
                    )
                }
            }
        }

        // If submitted, show result card at top
        if (isSubmitted && lastScore != null) {
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = if (lastScore.passed) Color(0xFFECFDF5) else Color(0xFFFEF2F2)
                ),
                shape = RoundedCornerShape(0.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = if (lastScore.passed) "CONGRATULATIONS - PASSED!" else "TEST NOT PASSED",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Black,
                            color = if (lastScore.passed) GrupoEmeraldDark else Color(0xFFB91C1C)
                        )
                        Text(
                            text = "Score: ${lastScore.score}/${lastScore.totalQuestions} (${lastScore.percent}%) • Pass Mark: ${quiz.passingScorePercent}%",
                            fontSize = 12.sp,
                            color = Color(0xFF334155),
                            fontWeight = FontWeight.SemiBold
                        )
                    }

                    Button(
                        onClick = onCloseQuiz,
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (lastScore.passed) GrupoEmeraldDark else Color(0xFFB91C1C)
                        ),
                        shape = RoundedCornerShape(8.dp)
                    ) {
                        Text("Finish", fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }

        // Questions List
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp),
            contentPadding = androidx.compose.foundation.layout.PaddingValues(vertical = 14.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            items(quiz.questions, key = { it.id }) { question ->
                val selectedOption = userAnswers[question.id]
                val isCorrect = selectedOption == question.correctIndex

                Card(
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Surface(
                                color = GrupoNavyPrimary,
                                shape = CircleShape,
                                modifier = Modifier.size(24.dp)
                            ) {
                                Box(contentAlignment = Alignment.Center) {
                                    Text(
                                        text = "${question.id}",
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = "Question ${question.id}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF64748B)
                            )

                            if (isSubmitted) {
                                Spacer(modifier = Modifier.weight(1f))
                                Surface(
                                    color = if (isCorrect) GrupoEmeraldDark.copy(alpha = 0.15f) else Color(0xFFFEE2E2),
                                    shape = RoundedCornerShape(4.dp)
                                ) {
                                    Text(
                                        text = if (isCorrect) "CORRECT" else "INCORRECT",
                                        color = if (isCorrect) GrupoEmeraldDark else Color(0xFFB91C1C),
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = question.question,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = GrupoNavyPrimary,
                            lineHeight = 18.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // 4 Options
                        question.options.forEachIndexed { index, optionText ->
                            val isThisSelected = selectedOption == index
                            val isThisCorrectAnswer = question.correctIndex == index

                            val backgroundColor = when {
                                !isSubmitted && isThisSelected -> GrupoNavyPrimary.copy(alpha = 0.08f)
                                isSubmitted && isThisCorrectAnswer -> GrupoEmeraldDark.copy(alpha = 0.15f)
                                isSubmitted && isThisSelected && !isThisCorrectAnswer -> Color(0xFFFEE2E2)
                                else -> Color(0xFFF8FAFC)
                            }

                            val borderColor = when {
                                !isSubmitted && isThisSelected -> GrupoNavyPrimary
                                isSubmitted && isThisCorrectAnswer -> GrupoEmeraldDark
                                isSubmitted && isThisSelected && !isThisCorrectAnswer -> Color(0xFFEF4444)
                                else -> Color(0xFFE2E8F0)
                            }

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(backgroundColor)
                                    .border(1.dp, borderColor, RoundedCornerShape(8.dp))
                                    .clickable(enabled = !isSubmitted) {
                                        onSelectAnswer(question.id, index)
                                    }
                                    .padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Surface(
                                    color = if (isThisSelected || (isSubmitted && isThisCorrectAnswer)) {
                                        if (isSubmitted) {
                                            if (isThisCorrectAnswer) GrupoEmeraldDark else Color(0xFFEF4444)
                                        } else {
                                            GrupoNavyPrimary
                                        }
                                    } else {
                                        Color(0xFFCBD5E1)
                                    },
                                    shape = CircleShape,
                                    modifier = Modifier.size(20.dp)
                                ) {
                                    Box(contentAlignment = Alignment.Center) {
                                        Text(
                                            text = listOf("A", "B", "C", "D").getOrElse(index) { "$index" },
                                            color = Color.White,
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.width(10.dp))

                                Text(
                                    text = optionText,
                                    fontSize = 12.sp,
                                    color = Color(0xFF1E293B),
                                    fontWeight = if (isThisSelected) FontWeight.Bold else FontWeight.Normal,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }

                        // Explanation Box (shown after submitting)
                        if (isSubmitted) {
                            Spacer(modifier = Modifier.height(10.dp))
                            Surface(
                                color = Color(0xFFF1F5F9),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(10.dp)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = Icons.Default.HelpOutline,
                                            contentDescription = null,
                                            tint = GrupoNavyPrimary,
                                            modifier = Modifier.size(14.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = "Official Revision Note:",
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = GrupoNavyPrimary
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = question.explanation,
                                        fontSize = 11.sp,
                                        color = Color(0xFF334155),
                                        lineHeight = 15.sp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Bottom Action Bar
        Surface(
            color = Color.White,
            shadowElevation = 8.dp,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                GrupoOutlinedButton(
                    text = if (isSubmitted) "Close" else "Quit Test",
                    onClick = onCloseQuiz,
                    height = 44.dp,
                    fontSize = 12.sp,
                    borderColor = Color(0xFFCBD5E1),
                    textColor = GrupoNavyDark,
                    modifier = Modifier.weight(1f)
                )

                if (!isSubmitted) {
                    GrupoPrimaryButton(
                        text = "Submit Test ($answeredCount/$totalQuestions)",
                        onClick = onSubmitQuiz,
                        enabled = userAnswers.isNotEmpty(),
                        height = 44.dp,
                        fontSize = 12.sp,
                        modifier = Modifier
                            .weight(1.5f)
                            .testTag("submit_quiz_button")
                    )
                }
            }
        }
    }
}
