package com.example.water_reminder.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.water_reminder.R
import com.example.water_reminder.theme.WaterReminderTheme

/**
 * Composable function for displaying a setting header with the specified [title].
 *
 * @param title The title of the setting header.
 */
@Composable
fun SettingHeader(
    title: String
) {
    Row(modifier = Modifier.padding(horizontal = 18.dp, vertical = 4.dp)) {
        Text(
            text = title,
            style = MaterialTheme.typography.body1
        )
    }
}

/**
 * Composable function for displaying a setting item with the specified [title], [value], and [onItemClicked] action.
 *
 * @param backgroundColor The background color of the setting item.
 * @param title The title of the setting item.
 * @param value The value of the setting item, nullable.
 * @param onItemClicked The action to be executed when the setting item is clicked.
 */
@Composable
fun Setting(
    backgroundColor: Color = Color.White,
    title: String,
    value: String? = null,
    onItemClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .background(backgroundColor)
            .padding(horizontal = 18.dp, vertical = 12.dp)
            .clickable { onItemClicked() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Medium),
            modifier = Modifier.weight(1F)
        )

        value?.let {
            Text(
                text = it
            )
        }

        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_right),
            contentDescription = "Next Button"
        )
    }
}

@Preview
@Composable
fun SettingHeaderPreview() {
    WaterReminderTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
        ) {
            SettingHeader(
                title = "Main Settings"
            )
        }
    }
}

@Preview
@Composable
fun SettingPreview() {
    WaterReminderTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
        ) {
            Setting(
                title = "Test",
                onItemClicked = { },
                value = "Test Value"
            )
        }
    }
}