package com.example.water_reminder.ui.screens.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.water_reminder.data.repository.DailyHistoryRepository
import com.example.water_reminder.utils.DateFormatter
import com.example.water_reminder.utils.DrinkTypeData
import com.example.water_reminder.utils.SharedPrefHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

/**
 * ViewModel for managing the state and logic of the HomeScreen.
 *
 * @property dailyHistoryRepository The repository for accessing daily history data.
 */
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dailyHistoryRepository: DailyHistoryRepository
) : ViewModel() {

    // Mutable state to hold the current state of the home screen
    var state by mutableStateOf(HomeState())
        private set

    /**
     * Initializes the available drink types.
     */
    fun initDrinkType() {
        val totalAmount = SharedPrefHelper.readInt(SharedPrefHelper.PREF_DAILY_GOAL, 2700)
        state = state.copy(
            drinkTypes = DrinkTypeData.getData(),
            totalAmount = totalAmount
        )
    }

    /**
     * Initializes the data, including the history and percentage of daily water intake completion.
     */
    fun initData() {
        viewModelScope.launch {
            val date = DateFormatter.formatDate(Date()) ?: Date()
            Log.i(HomeViewModel::class.simpleName, "initData: $date")
            dailyHistoryRepository.getHistory(date)?.let {
                state = state.copy(
                    history = it,
                    percentage = it.totalAmount.toFloat() / state.totalAmount.toFloat()
                )
            }
        }
    }

    /**
     * Adds water to the daily water intake total.
     *
     * @param amount The amount of water to add.
     */
    fun addWater(amount: Int) {
        if (state.history == null) {
            initData()
        }

        viewModelScope.launch {
            state.history?.let {
                dailyHistoryRepository.createHistory(it.copy(totalAmount = it.totalAmount + amount))
                initData()
            }
        }
    }

    /**
     * Reduces water from the daily water intake total.
     *
     * @param amount The amount of water to reduce.
     */
    fun reduceWater(amount: Int) {
        if (state.history == null) {
            initData()
        }

        viewModelScope.launch {
            initData()
            state.history?.let {
                dailyHistoryRepository.createHistory(it.copy(totalAmount = it.totalAmount - amount))
                initData()
            }
        }
    }
}
