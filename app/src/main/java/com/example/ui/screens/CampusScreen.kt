package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Directions
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Train
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
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
import com.example.ui.components.GrupoOutlinedButton
import com.example.ui.components.GrupoPrimaryButton
import com.example.ui.components.GrupoSecondaryButton
import com.example.ui.theme.GrupoBlue
import com.example.ui.theme.GrupoEmeraldDark
import com.example.ui.theme.GrupoGoldAccent
import com.example.ui.theme.GrupoGoldDark
import com.example.ui.theme.GrupoGreen
import com.example.ui.theme.GrupoNavyDark
import com.example.ui.theme.GrupoNavyPrimary

@Composable
fun CampusScreen() {
    val context = LocalContext.current

    val faqs = listOf(
        Pair(
            "What is Grupo Training's 100% Free Retake Guarantee?",
            "If you do not pass your official exam on the first attempt after attending all scheduled classroom training sessions, Grupo Training provides you with free revision classes and your first re-sit at zero additional tuition cost."
        ),
        Pair(
            "How quickly is my SIA licence processed after passing?",
            "Once you successfully pass your exams at our Romford Road center, your results are uploaded to the SIA database within 5 to 7 working days. You can then submit your £184 licence application on the official SIA portal."
        ),
        Pair(
            "How do I receive my official CSCS Green Card?",
            "Upon completing our 1-day Level 1 Health & Safety course and passing the CITB Touch Screen test, your qualification is registered on CITB's portal. Your physical 5-year Green Card is mailed to your UK address in 5-10 business days."
        ),
        Pair(
            "Can I train with Grupo Training if English is my second language?",
            "Yes! We specialize in ESOL and bilingual support. Our instructors break down technical exam terms simply, and we provide vocabulary glossaries and unlimited practice mock tests in our computer lab."
        ),
        Pair(
            "What parking is available at the Romford Road center?",
            "Pay-and-display street parking is available on surrounding streets. We highly recommend using public transport: Forest Gate Elizabeth Line station is just a 5-minute walk away."
        )
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAFC))
            .testTag("campus_screen"),
        contentPadding = androidx.compose.foundation.layout.PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Location Card
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = GrupoNavyPrimary),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Surface(
                            color = GrupoGreen,
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = "OFFICIAL CAMPUS",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }

                        Spacer(modifier = Modifier.weight(1f))

                        Surface(
                            color = GrupoGoldAccent,
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = "LONDON HQ",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF091B2E),
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(46.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color.White)
                                .padding(4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            androidx.compose.foundation.Image(
                                painter = androidx.compose.ui.res.painterResource(id = com.example.R.drawable.ic_grupo_logo),
                                contentDescription = "Grupo Training Logo",
                                modifier = Modifier.size(38.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Text(
                                text = "Grupo Training UK",
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            Text(
                                text = "Vocational Academy & Testing Center",
                                fontSize = 12.sp,
                                color = GrupoGoldAccent
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.padding(top = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = GrupoGreen,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "252-256 Romford Road, Forest Gate, London E7 9HZ",
                            fontSize = 13.sp,
                            color = Color.White.copy(alpha = 0.9f)
                        )
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        GrupoPrimaryButton(
                            text = "Navigate",
                            onClick = {
                                val mapIntent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("geo:0,0?q=252-256+Romford+Road+London+E7+9HZ")
                                )
                                context.startActivity(mapIntent)
                            },
                            icon = Icons.Default.Directions,
                            height = 44.dp,
                            fontSize = 12.sp,
                            modifier = Modifier.weight(1f)
                        )

                        GrupoSecondaryButton(
                            text = "Call Center",
                            onClick = {
                                val dialIntent = Intent(Intent.ACTION_DIAL).apply {
                                    data = Uri.parse("tel:02039834565")
                                }
                                context.startActivity(dialIntent)
                            },
                            icon = Icons.Default.Phone,
                            height = 44.dp,
                            fontSize = 12.sp,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        // Contact & Transport Details
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(14.dp),
                border = BorderStroke(1.dp, Color(0xFFE2E8F0)),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Campus Operations & Travel",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = GrupoNavyPrimary
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Public Transport
                    Row(verticalAlignment = Alignment.Top) {
                        Icon(
                            Icons.Default.Train,
                            contentDescription = null,
                            tint = GrupoNavyPrimary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Nearest Stations",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = GrupoNavyPrimary
                            )
                            Text(
                                text = "• Forest Gate Station (Elizabeth Line) — 5 mins walk\n• Wanstead Park (London Overground) — 7 mins walk\n• Bus routes 25, 86, and 425 stop outside",
                                fontSize = 11.sp,
                                color = Color(0xFF475569),
                                lineHeight = 16.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Office Hours
                    Row(verticalAlignment = Alignment.Top) {
                        Icon(
                            Icons.Default.AccessTime,
                            contentDescription = null,
                            tint = GrupoNavyPrimary,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Opening Hours",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = GrupoNavyPrimary
                            )
                            Text(
                                text = "• Monday – Friday: 9:00 AM – 6:00 PM\n• Saturday: 9:30 AM – 5:00 PM\n• Sunday: Closed (Mock exams & Online Hub active)",
                                fontSize = 11.sp,
                                color = Color(0xFF475569),
                                lineHeight = 16.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    // Direct Contact Actions
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        GrupoOutlinedButton(
                            text = "Email Us",
                            onClick = {
                                val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                                    data = Uri.parse("mailto:info@grupotraining.co.uk")
                                    putExtra(Intent.EXTRA_SUBJECT, "Course Enquiry - Grupo Training App")
                                }
                                context.startActivity(emailIntent)
                            },
                            icon = Icons.Default.Email,
                            height = 42.dp,
                            fontSize = 12.sp,
                            borderColor = Color(0xFFCBD5E1),
                            textColor = GrupoNavyDark,
                            modifier = Modifier.weight(1f)
                        )

                        GrupoOutlinedButton(
                            text = "Website",
                            onClick = {
                                val webIntent = Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("https://www.grupotraining.co.uk")
                                )
                                context.startActivity(webIntent)
                            },
                            icon = Icons.Default.Language,
                            height = 42.dp,
                            fontSize = 12.sp,
                            borderColor = Color(0xFFCBD5E1),
                            textColor = GrupoNavyDark,
                            modifier = Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        // Accreditations Grid
        item {
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(12.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Official Accreditations & Regulators",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = GrupoNavyPrimary
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                    val accreditations = listOf(
                        "Security Industry Authority (SIA)",
                        "Construction Industry Training Board (CITB)",
                        "Transport for London (TFL)",
                        "Highfield Qualifications",
                        "Ofqual Regulated Awarding Bodies",
                        "Qualsafe Awards First Aid"
                    )

                    accreditations.chunked(2).forEach { rowItems ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 3.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            rowItems.forEach { item ->
                                Surface(
                                    color = Color(0xFFF1F5F9),
                                    shape = RoundedCornerShape(6.dp),
                                    modifier = Modifier.weight(1f)
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            Icons.Default.Security,
                                            contentDescription = null,
                                            tint = GrupoEmeraldDark,
                                            modifier = Modifier.size(14.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = item,
                                            fontSize = 11.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = Color(0xFF1E293B)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // FAQs Section Header
        item {
            Text(
                text = "Candidate Frequently Asked Questions",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = GrupoNavyPrimary
            )
        }

        // Expandable FAQ Items
        items(faqs) { faq ->
            FaqAccordionItem(question = faq.first, answer = faq.second)
        }
    }
}

@Composable
fun FaqAccordionItem(question: String, answer: String) {
    var expanded by remember { mutableStateOf(false) }

    Card(
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { expanded = !expanded }
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = question,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = GrupoNavyPrimary,
                    modifier = Modifier.weight(1f)
                )

                Icon(
                    imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = if (expanded) "Collapse" else "Expand",
                    tint = GrupoNavyPrimary
                )
            }

            AnimatedVisibility(visible = expanded) {
                Column(modifier = Modifier.padding(top = 8.dp)) {
                    Text(
                        text = answer,
                        fontSize = 12.sp,
                        color = Color(0xFF475569),
                        lineHeight = 17.sp
                    )
                }
            }
        }
    }
}
