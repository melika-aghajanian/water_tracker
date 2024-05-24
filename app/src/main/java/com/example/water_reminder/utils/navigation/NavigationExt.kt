package com.example.water_reminder.utils.navigation

import androidx.navigation.NavHostController

/**
 * Extension function for navigating to a destination with parameters using a NavHostController.
 * This function constructs the navigation route with the provided route name and parameters,
 * then navigates to the destination.
 *
 * @param routeName The name of the destination route.
 * @param params The parameters to be passed to the destination route.
 */
fun NavHostController.navigateWithParams(routeName: String, vararg params: String) {
    this.navigate(route = "$routeName/${params.joinToString(separator = "/")}")
}
