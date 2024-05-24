package com.example.water_reminder.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.water_reminder.theme.WaterReminderTheme
import com.example.water_reminder.R

/**
 * Composable function for rendering a history item.
 *
 * @param date The date associated with the history item.
 * @param value The value associated with the history item.
 */
@Composable
fun HistoryItem(
    date: String,
    value: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 18.dp, vertical = 8.dp),
        elevation = 2.dp,
        shape = MaterialTheme.shapes.medium,
        backgroundColor = Color.White
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 18.dp, vertical = 16.dp),
        ) {
            Text(
                text = date,
                modifier = Modifier
                    .weight(1F),
                style = MaterialTheme.typography.h3
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_water_drop),
                    contentDescription = "Water Icon",
                    tint = MaterialTheme.colors.primary
                )

                Text(text = "${value}ml", style = MaterialTheme.typography.body1)
            }
        }
    }
}

/**
 * Preview function for the HistoryItem composable.
 */
@Preview
@Composable
fun HistoryItemPreview() {
    WaterReminderTheme {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.background)
        ) {
            HistoryItem(
                date = "20 Jan 2022",
                value = "200"
            )
        }
    }
}
