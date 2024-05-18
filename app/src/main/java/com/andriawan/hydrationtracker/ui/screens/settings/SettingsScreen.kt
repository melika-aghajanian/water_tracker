package com.andriawan.hydrationtracker.ui.screens.settings

import android.content.res.Configuration
import androidx.compose.runtime.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.andriawan.hydrationtracker.R
import com.andriawan.hydrationtracker.theme.HydrationTrackerTheme
import com.andriawan.hydrationtracker.ui.components.DialogEditValue
import com.andriawan.hydrationtracker.ui.components.Setting
import com.andriawan.hydrationtracker.ui.components.SettingHeader

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    val state = viewModel.state
    var showDialog by remember { mutableStateOf(false) }
    var dialogTitle by remember { mutableStateOf("") }
    var dialogValue by remember { mutableStateOf("") }
    var dialogOnSubmit by remember { mutableStateOf<(String) -> Unit>({}) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        SettingsPageTopBar()
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            SettingHeader(title = stringResource(id = R.string.application_settings))
            Column {
                Setting(
                    title = stringResource(id = R.string.settings_user_name),
                    value = state.userName.toString(),
                    onItemClicked = {
                        showDialog = true
                    //    dialogTitle = stringResource(id = R.string.settings_user_name)
                        dialogValue = state.userName
                        dialogOnSubmit = { newValue ->
                            viewModel.saveNewUserName(newValue)
                            showDialog = false
                        }
                    }
                )

                Setting(
                    title = stringResource(id = R.string.settings_goal_amount),
                    value = state.dailyGoals.toString(),
                    onItemClicked = {
                        showDialog = true
                   //     dialogTitle = stringResource(id = R.string.settings_goal_amount)
                        dialogValue = state.dailyGoals.toString()
                        dialogOnSubmit = { newValue ->
                            viewModel.saveNewGoals(newValue.toInt())
                            showDialog = false
                        }
                    }
                )

                Setting(
                    title = stringResource(id = R.string.settings_weight),
                    value = state.weight.toString(),
                    onItemClicked = {
                        showDialog = true
                    //    dialogTitle = stringResource(id = R.string.settings_weight)
                        dialogValue = state.weight.toString()
                        dialogOnSubmit = { newValue ->
                            viewModel.saveNewWeight(newValue.toInt())
                            showDialog = false
                        }
                    }
                )

                Setting(
                    title = stringResource(id = R.string.settings_height),
                    value = state.height.toString(),
                    onItemClicked = {
                        showDialog = true
                    //    dialogTitle = stringResource(id = R.string.settings_height)
                        dialogValue = state.height.toString()
                        dialogOnSubmit = { newValue ->
                            viewModel.saveNewHeight(newValue.toInt())
                            showDialog = false
                        }
                    }
                )

                Setting(
                    title = stringResource(id = R.string.settings_wake_up_time),
                    value = state.wakeUpTime,
                    onItemClicked = {
                        showDialog = true
                    //    dialogTitle = stringResource(id = R.string.settings_wake_up_time)
                        dialogValue = state.wakeUpTime
                        dialogOnSubmit = { newValue ->
                            viewModel.saveNewWakeUpTime(newValue)
                            showDialog = false
                        }
                    }
                )

                Setting(
                    title = stringResource(id = R.string.settings_sleep_time),
                    value = state.sleepTime,
                    onItemClicked = {
                        showDialog = true
                    //    dialogTitle = stringResource(id = R.string.settings_sleep_time)
                        dialogValue = state.sleepTime
                        dialogOnSubmit = { newValue ->
                            viewModel.saveNewSleepTime(newValue)
                            showDialog = false
                        }
                    }
                )
            }
        }
    }

    if (showDialog) {
        DialogEditValue(
            title = dialogTitle,
            value = dialogValue,
            onSubmit = dialogOnSubmit,
            onDismiss = {
                showDialog = false
            }
        )
    }
}

@Composable
fun SettingsPageTopBar() {
    Text(
        text = stringResource(id = R.string.settings),
        style = MaterialTheme.typography.h1,
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
        textAlign = TextAlign.Center
    )
}

@Preview
@Composable
fun SettingsScreenPreview() {
    HydrationTrackerTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            SettingsScreen()
        }
    }
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SettingsScreenPreviewDarkMode() {
    HydrationTrackerTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.background)
        ) {
            SettingsScreen()
        }
    }
}