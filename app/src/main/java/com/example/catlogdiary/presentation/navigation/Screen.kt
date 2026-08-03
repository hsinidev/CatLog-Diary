package com.example.catlogdiary.presentation.navigation

sealed class Screen(val route: String, val label: String) {
    object Dashboard : Screen("dashboard", "Dashboard")
    object Action : Screen("action", "Actions")
    object Settings : Screen("settings", "Settings")
}
