package com.example.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assignment
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.outlined.Assignment
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Quiz
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.ui.theme.GrupoBlue
import com.example.ui.theme.GrupoGreen
import com.example.ui.theme.GrupoGreenDark
import com.example.ui.theme.GrupoNavyDark
import com.example.ui.theme.GrupoNavyPrimary
import com.example.ui.viewmodel.AppTab

data class NavItem(
    val tab: AppTab,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val label: String
)

@Composable
fun GrupoBottomBar(
    currentTab: AppTab,
    onTabSelected: (AppTab) -> Unit,
    bookingsCount: Int
) {
    val items = listOf(
        NavItem(AppTab.HOME, Icons.Filled.Home, Icons.Outlined.Home, "Home"),
        NavItem(AppTab.COURSES, Icons.Filled.MenuBook, Icons.Outlined.MenuBook, "Courses"),
        NavItem(AppTab.MOCK_TESTS, Icons.Filled.Quiz, Icons.Outlined.Quiz, "Mock Tests"),
        NavItem(AppTab.MY_PORTAL, Icons.Filled.Assignment, Icons.Outlined.Assignment, "Bookings"),
        NavItem(AppTab.CAMPUS, Icons.Filled.LocationOn, Icons.Outlined.LocationOn, "Campus")
    )

    NavigationBar(
        containerColor = Color.White,
        contentColor = GrupoNavyPrimary,
        windowInsets = NavigationBarDefaults.windowInsets,
        modifier = Modifier.testTag("grupo_bottom_bar")
    ) {
        items.forEach { item ->
            val isSelected = currentTab == item.tab
            NavigationBarItem(
                selected = isSelected,
                onClick = { onTabSelected(item.tab) },
                icon = {
                    if (item.tab == AppTab.MY_PORTAL && bookingsCount > 0) {
                        BadgedBox(
                            badge = {
                                Badge(
                                    containerColor = GrupoGreen,
                                    contentColor = Color.White
                                ) {
                                    Text(
                                        text = bookingsCount.toString(),
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 10.sp
                                    )
                                }
                            }
                        ) {
                            Icon(
                                imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                                contentDescription = item.label
                            )
                        }
                    } else {
                        Icon(
                            imageVector = if (isSelected) item.selectedIcon else item.unselectedIcon,
                            contentDescription = item.label
                        )
                    }
                },
                label = {
                    Text(
                        text = item.label,
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = GrupoNavyDark,
                    selectedTextColor = GrupoNavyDark,
                    indicatorColor = GrupoGreen.copy(alpha = 0.18f),
                    unselectedIconColor = Color(0xFF64748B),
                    unselectedTextColor = Color(0xFF64748B)
                ),
                modifier = Modifier.testTag("nav_tab_${item.tab.name.lowercase()}")
            )
        }
    }
}
