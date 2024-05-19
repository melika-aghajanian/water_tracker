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

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val historyRepository: DailyHistoryRepository
): ViewModel() {

    var state by mutableStateOf(HistoryState())
    private set

    fun initData() {
        viewModelScope.launch {
            historyRepository.getHistories().let {
                state = state.copy(
                    histories = it
                )
            }
        }
    }
}