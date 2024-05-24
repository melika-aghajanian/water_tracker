package com.example.water_reminder.ui.screens.history

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.water_reminder.data.repository.DailyHistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for managing the state and data of the HistoryScreen.
 *
 * @property historyRepository The repository for accessing daily history data.
 */
@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyRepository: DailyHistoryRepository
) : ViewModel() {

    // Mutable state to hold the current state of the history screen
    var state by mutableStateOf(HistoryState())
        private set

    /**
     * Initializes the data by fetching histories from the repository.
     * This function is called when the app is resumed.
     */
    fun initData() {
        viewModelScope.launch {
            historyRepository.getHistories().let { histories ->
                state = state.copy(
                    histories = histories
                )
            }
        }
    }
}
