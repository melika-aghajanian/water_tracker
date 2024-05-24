package com.example.water_reminder.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.water_reminder.ui.screens.history.HistoryScreen
import com.example.water_reminder.ui.screens.home.HomeScreen
import com.example.water_reminder.ui.screens.settings.SettingsScreen
import com.example.water_reminder.utils.navigation.Routes

/**
 * Defines the navigation graph of the app using Jetpack Compose Navigation.
 *
 * @param navController The navigation controller responsible for managing navigation within the app.
 */
@ExperimentalMaterialApi
@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.HomePage.routeName
    ) {

        // Home Screen
        composable(Routes.HomePage.routeName) {
            HomeScreen()
        }

        // Settings Screen
        composable(Routes.SettingsPage.routeName) {
            SettingsScreen()
        }

        // History Screen
        composable(Routes.HistoryPage.routeName) {
            HistoryScreen()
        }
    }
}
