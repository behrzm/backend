package com.example.pro.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomItem(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomItem("home", "Home", Icons.Filled.Home)
    object Progress : BottomItem("progress", "Progress", Icons.Filled.List)
    object Achievements : BottomItem("achievements", "Awards", Icons.Filled.Star)
    object Profile : BottomItem("profile", "Profile", Icons.Filled.Person)
}
