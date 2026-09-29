package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.data.local.CourseBookingEntity
import com.example.data.model.Course
import com.example.ui.components.GrupoOutlinedButton
import com.example.ui.components.GrupoPrimaryButton
import com.example.ui.components.GrupoSecondaryButton
import com.example.ui.theme.GrupoEmeraldDark
import com.example.ui.theme.GrupoGoldAccent
import com.example.ui.theme.GrupoGoldDark
import com.example.ui.theme.GrupoNavyDark
import com.example.ui.theme.GrupoNavyPrimary

@Composable
fun BookingDialog(
    course: Course,
    onDismiss: () -> Unit,
    onConfirm: (
        course: Course,
        name: String,
        phone: String,
        email: String,
        date: String,
        deliveryMode: String,
        notes: String
    ) -> Unit
) {
    var candidateName by remember { mutableStateOf("") }
    var candidatePhone by remember { mutableStateOf("") }
    var candidateEmail by remember { mutableStateOf("") }
    var selectedDate by remember { mutableStateOf(course.nextDates.firstOrNull() ?: "Next Monday") }
    var selectedMode by remember { mutableStateOf("Classroom (Romford Rd Campus)") }
    var notes by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .fillMaxHeight(0.92f)
                .testTag("booking_dialog"),
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                // Header
                Surface(color = GrupoNavyPrimary, modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Course Enrollment & Seat Reservation",
                                color = GrupoGoldAccent,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = course.title,
                                color = Color.White,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                maxLines = 1
                            )
                        }

                        IconButton(onClick = onDismiss) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                tint = Color.White
                            )
                        }
                    }
                }

                // Scrollable Form
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 16.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    Spacer(modifier = Modifier.height(12.dp))

                    // Summary Card
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F5F9)),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(text = "Tuition Fee", fontSize = 11.sp, color = Color(0xFF64748B))
                                Text(
                                    text = course.price,
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight.Black,
                                    color = GrupoNavyPrimary
                                )
                            }
                            Column(horizontalAlignment = Alignment.End) {
                                Text(text = "Campus Venue", fontSize = 11.sp, color = Color(0xFF64748B))
                                Text(
                                    text = "252-256 Romford Rd, E7 9HZ",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = GrupoNavyPrimary
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    if (errorMessage != null) {
                        Surface(
                            color = Color(0xFFFEE2E2),
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = errorMessage ?: "",
                                color = Color(0xFFB91C1C),
                                fontSize = 12.sp,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    // Candidate Full Name
                    Text(
                        text = "Full Name (as on Passport/ID) *",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = GrupoNavyPrimary
                    )
                    OutlinedTextField(
                        value = candidateName,
                        onValueChange = {
                            candidateName = it
                            errorMessage = null
                        },
                        placeholder = { Text("e.g. John Smith", fontSize = 13.sp) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("booking_name_input"),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = GrupoNavyPrimary,
                            unfocusedBorderColor = Color(0xFFCBD5E1)
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Phone Number
                    Text(
                        text = "UK Contact Phone Number *",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = GrupoNavyPrimary
                    )
                    OutlinedTextField(
                        value = candidatePhone,
                        onValueChange = {
                            candidatePhone = it
                            errorMessage = null
                        },
                        placeholder = { Text("e.g. 07123 456789 or 020...", fontSize = 13.sp) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("booking_phone_input"),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = GrupoNavyPrimary,
                            unfocusedBorderColor = Color(0xFFCBD5E1)
                        )
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Email Address
                    Text(
                        text = "Email Address (for Exam Pack & Receipt) *",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = GrupoNavyPrimary
                    )
                    OutlinedTextField(
                        value = candidateEmail,
                        onValueChange = {
                            candidateEmail = it
                            errorMessage = null
                        },
                        placeholder = { Text("e.g. candidate@example.co.uk", fontSize = 13.sp) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("booking_email_input"),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = GrupoNavyPrimary,
                            unfocusedBorderColor = Color(0xFFCBD5E1)
                        )
                    )

                    Spacer(modifier = Modifier.height(14.dp))

                    // Preferred Starting Date
                    Text(
                        text = "Preferred Start Schedule",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = GrupoNavyPrimary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    course.nextDates.forEach { dateOption ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedDate = dateOption }
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = selectedDate == dateOption,
                                onClick = { selectedDate = dateOption },
                                colors = RadioButtonDefaults.colors(selectedColor = GrupoNavyPrimary)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = dateOption, fontSize = 13.sp, color = Color(0xFF1E293B))
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Delivery Mode
                    Text(
                        text = "Learning Mode",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = GrupoNavyPrimary
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    listOf(
                        "Classroom (Romford Rd Campus)",
                        "Online Live & Mock Lab Access"
                    ).forEach { mode ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedMode = mode }
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = selectedMode == mode,
                                onClick = { selectedMode = mode },
                                colors = RadioButtonDefaults.colors(selectedColor = GrupoNavyPrimary)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = mode, fontSize = 13.sp, color = Color(0xFF1E293B))
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Special Notes
                    Text(
                        text = "Special Requirements or Inquiries (Optional)",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = GrupoNavyPrimary
                    )
                    OutlinedTextField(
                        value = notes,
                        onValueChange = { notes = it },
                        placeholder = { Text("e.g. Need First Aid bundle, ESOL help...", fontSize = 13.sp) },
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 3,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = GrupoNavyPrimary,
                            unfocusedBorderColor = Color(0xFFCBD5E1)
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))
                }

                // Footer Submit
                Surface(
                    color = Color(0xFFF8FAFC),
                    shadowElevation = 8.dp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        GrupoPrimaryButton(
                            text = "Confirm & Reserve Seat",
                            onClick = {
                                if (candidateName.isBlank()) {
                                    errorMessage = "Please enter your full legal name."
                                    return@GrupoPrimaryButton
                                }
                                if (candidatePhone.isBlank() || candidatePhone.length < 7) {
                                    errorMessage = "Please enter a valid UK phone number."
                                    return@GrupoPrimaryButton
                                }
                                if (candidateEmail.isBlank() || !candidateEmail.contains("@")) {
                                    errorMessage = "Please enter a valid email address."
                                    return@GrupoPrimaryButton
                                }
                                onConfirm(
                                    course,
                                    candidateName,
                                    candidatePhone,
                                    candidateEmail,
                                    selectedDate,
                                    selectedMode,
                                    notes
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp)
                                .testTag("submit_booking_button"),
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BookingConfirmationDialog(
    booking: CourseBookingEntity,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth(0.92f)
                .testTag("booking_confirmation_dialog"),
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .clip(CircleShape)
                        .background(GrupoEmeraldDark.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = GrupoEmeraldDark,
                        modifier = Modifier.size(36.dp)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Seat Reservation Confirmed!",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = GrupoNavyPrimary
                )

                Text(
                    text = "Your enrollment has been logged with admissions.",
                    fontSize = 12.sp,
                    color = Color(0xFF64748B),
                    modifier = Modifier.padding(top = 2.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Pass Card
                Card(
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF1F5F9)),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Reference No:", fontSize = 11.sp, color = Color(0xFF64748B))
                            Text(
                                text = booking.bookingReference,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Black,
                                color = GrupoNavyPrimary
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Candidate:", fontSize = 11.sp, color = Color(0xFF64748B))
                            Text(
                                text = booking.candidateName,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF0F172A)
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Course:", fontSize = 11.sp, color = Color(0xFF64748B))
                            Text(
                                text = booking.courseTitle,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = GrupoNavyPrimary,
                                modifier = Modifier.width(180.dp),
                                maxLines = 1
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Start Schedule:", fontSize = 11.sp, color = Color(0xFF64748B))
                            Text(
                                text = booking.preferredDate,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF0F172A)
                            )
                        }

                        Spacer(modifier = Modifier.height(6.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Fee:", fontSize = 11.sp, color = Color(0xFF64748B))
                            Text(
                                text = booking.price,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold,
                                color = GrupoEmeraldDark
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.LocationOn,
                        contentDescription = null,
                        tint = GrupoGoldDark,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Venue: 252-256 Romford Road, London E7 9HZ",
                        fontSize = 11.sp,
                        color = Color(0xFF475569)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    GrupoOutlinedButton(
                        text = "Call Center",
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

                    GrupoSecondaryButton(
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
                }

                Spacer(modifier = Modifier.height(12.dp))

                GrupoPrimaryButton(
                    text = "Done",
                    onClick = onDismiss,
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("dismiss_confirmation_button"),
                    height = 44.dp
                )
            }
        }
    }
}
