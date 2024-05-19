package com.example.water_reminder.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.example.water_reminder.data.database.AppDatabase
import com.example.water_reminder.data.database.dao.DailyDrinkDAO
import com.example.water_reminder.data.models.DailyHistory
import com.example.water_reminder.utils.DateFormatter
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.*
import javax.inject.Inject
import javax.inject.Named

@HiltAndroidTest
@SmallTest
class DailyHistoryRepositoryTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: AppDatabase
    private lateinit var historyDAO: DailyDrinkDAO
    private lateinit var historyRepository: DailyHistoryRepository

    @Before
    fun setUp() {
        hiltRule.inject()
        historyDAO = database.dailyDrinkDao()
        historyRepository = DailyHistoryRepository(historyDAO)
    }

    @Test
    fun getHistory() {
        assertTrue(1 + 1 == 2)
    }

    @Test
    fun getHistories() {
        val potter = "Potter"
        assertEquals("Harry $potter", "Harry Potter")
    }

    @Test
    fun createHistory() = runBlocking {
        val date = DateFormatter.formatDate(Date()) ?: Date()
        val dailyHistory = DailyHistory(
            id = 1,
            date = date,
            totalAmount = 0
        )
        historyRepository.createHistory(dailyHistory)
        val results = historyRepository.getHistories()
        assertTrue(results.isNotEmpty())
        assertEquals(results.first(), dailyHistory)
    }

    @After
    fun tearDown() {
        database.close()
    }
}