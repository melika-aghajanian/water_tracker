package com.example.water_reminder.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.water_reminder.theme.WaterReminderTheme
import com.example.water_reminder.theme.OnPrimaryColor
import com.example.water_reminder.utils.navigation.Routes

/**
 * Composable function for rendering the bottom navigation bar.
 *
 * @param navController The NavHostController used for navigation.
 */
@Composable
fun AppBottomNav(
    navController: NavHostController
) {
    // List of bottom navigation items
    val bottomNavList = listOf(
        Routes.HistoryPage,
        Routes.HomePage,
        Routes.SettingsPage
    )

    // Get the current route from the navigation controller
    val navBackstackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackstackEntry?.destination?.route

    // Bottom navigation bar
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primaryVariant,
        elevation = 0.dp
    ) {
        bottomNavList.map {
            BottomNavigationItem(
                selected = it.routeName == currentRoute,
                onClick = {
                    navController.navigate(it.routeName) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = null
                    )
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = OnPrimaryColor
            )
        }
    }
}

/**
 * Preview function for the AppBottomNav composable.
 */
@Preview
@Composable
fun AppBottomNavPreview() {
    WaterReminderTheme {
        Surface(modifier = Modifier.fillMaxWidth()) {
            AppBottomNav(
                navController = rememberNavController()
            )
        }
    }
}
