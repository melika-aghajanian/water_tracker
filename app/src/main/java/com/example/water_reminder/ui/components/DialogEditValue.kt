package com.example.water_reminder.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.water_reminder.theme.WaterReminderTheme

/**
 * Composable function for rendering a dialog to edit a value.
 *
 * @param title The title of the dialog.
 * @param value The current value to be edited.
 * @param onSubmit The callback to be invoked when submitting the edited value.
 * @param onDismiss The callback to be invoked when dismissing the dialog.
 */
@Composable
fun DialogEditValue(
    title: String,
    value: String,
    onSubmit: (newValue: String) -> Unit,
    onDismiss: () -> Unit
) {
    var newValue by remember { mutableStateOf(value) }

    Dialog(onDismissRequest = { onDismiss() }) {
        Card(
            shape = MaterialTheme.shapes.small
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp)
            ) {
                Text(
                    text = title, style = MaterialTheme.typography.h3
                )

                OutlinedTextField(
                    value = newValue,
                    onValueChange = {
                        newValue = it
                    },
                    placeholder = {
                        Text(text = "Please enter a new value")
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Text // Set keyboard type to Text
                    ),
                    textStyle = MaterialTheme.typography.body1,
                    modifier = Modifier.fillMaxWidth()
                )

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = { onDismiss() },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = Color.Red
                        )
                    ) {
                        Text(
                            text = "Cancel",
                            style = MaterialTheme.typography.body1.copy(
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Button(
                        onClick = {
                            onSubmit(newValue)
                        }
                    ) {
                        Text(
                            text = "Save",
                            style = MaterialTheme.typography.body1.copy(
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                }
            }
        }
    }
}

/**
 * Preview function for the DialogEditValue composable.
 */
@Preview
@Composable
fun DialogEditValuePreview() {
    WaterReminderTheme {
        DialogEditValue(title = "Hello", value = "100", onSubmit = {}, onDismiss = {})
    }
}
