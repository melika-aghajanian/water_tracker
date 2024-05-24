package com.example.water_reminder.ui.screens.history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.example.water_reminder.ui.components.HistoryItem
import com.example.water_reminder.utils.DateFormatter

/**
 * Composable function for displaying the history screen, showing the user's water intake history.
 *
 * @param lifecycleOwner The lifecycle owner for observing lifecycle events.
 * @param viewModel The view model for managing data and UI logic.
 */
@Composable
fun HistoryScreen(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    viewModel: HistoryViewModel = hiltViewModel()
) {
    // Observe the state from the view model
    val state = viewModel.state

    // Set up a disposable effect to observe lifecycle events
    DisposableEffect(lifecycleOwner) {
        val lifecycle = lifecycleOwner.lifecycle
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    // When the app is resumed, initialize data
                    viewModel.initData()
                }
                else -> {}
            }
        }

        // Add observer to the lifecycle
        lifecycle.addObserver(observer)

        // Remove observer when effect is disposed
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

    // Column composable for arranging UI elements vertically
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        // Display the top bar with "History" text
        HistoryPageTopBar()

        // If there are histories available, display them using LazyColumn
        state.histories?.let { histories ->
            LazyColumn {
                items(
                    items = histories,
                    key = {
                        it.id ?: 0
                    }
                ) {
                    // For each history item, display it using HistoryItem composable
                    HistoryItem(
                        date = DateFormatter.formatDateToString(it.date),
                        value = it.totalAmount.toString()
                    )
                }
            }
        }
    }
}

/**
 * Composable function for displaying the top bar of the history screen.
 */
@Composable
fun HistoryPageTopBar() {
    Text(
        text = "History",
        style = MaterialTheme.typography.h1,
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
        textAlign = TextAlign.Center
    )
}
