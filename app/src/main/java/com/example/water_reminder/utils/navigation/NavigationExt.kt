package com.example.water_reminder.utils.navigation

import androidx.navigation.NavHostController

fun NavHostController.navigateWithParams(routeName: String, vararg params: String) {
    this.navigate(route = "$routeName/${params.joinToString(separator = "/")}")
}