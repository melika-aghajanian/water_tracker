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

@ExperimentalMaterialApi
@Composable
fun AppNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Routes.HomePage.routeName
    ) {

        composable(Routes.HomePage.routeName) {
            HomeScreen()
        }

        composable(Routes.SettingsPage.routeName) {
            SettingsScreen()
        }

        composable(Routes.HistoryPage.routeName) {
            HistoryScreen()
        }
    }
}