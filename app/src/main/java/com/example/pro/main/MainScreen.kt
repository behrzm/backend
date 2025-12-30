package com.example.pro.main

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.*
import com.example.pro.LoginActivity
import com.example.pro.screens.*
import com.google.firebase.auth.FirebaseAuth

@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val context = LocalContext.current

    val items = listOf(
        BottomItem.Home,
        BottomItem.Progress,
        BottomItem.Achievements,
        BottomItem.Profile
    )

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId)
                                launchSingleTop = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title
                            )
                        },
                        label = { Text(item.title) }
                    )
                }
            }
        }
    ) { padding ->

        NavHost(
            navController = navController,
            startDestination = BottomItem.Home.route,
            modifier = Modifier.padding(padding)
        ) {

            composable("home") { HomeScreen() }
            composable("progress") { ProgressScreen() }
            composable("achievements") { AchievementsScreen() }

            composable("profile") {
                ProfileScreen(
                    onLogout = {
                        // 🔥 Firebase logout
                        FirebaseAuth.getInstance().signOut()

                        // 🔥 Go to LoginActivity and clear back stack
                        val intent = Intent(context, LoginActivity::class.java)
                        intent.flags =
                            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                        context.startActivity(intent)
                        (context as Activity).finish()
                    }
                )
            }
        }
    }
}
