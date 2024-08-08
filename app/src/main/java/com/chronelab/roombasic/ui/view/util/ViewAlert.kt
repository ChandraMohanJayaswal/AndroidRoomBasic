package com.chronelab.roombasic.ui.view.util

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun ViewAlert(message: String, dismissButtonText: String, dismissButtonAction: () -> Unit) {
    AlertDialog(
        onDismissRequest = { /*TODO*/ },
        confirmButton = { /*TODO*/ },
        text = { Text(text = message) },
        dismissButton = {
            TextButton(onClick = {
                dismissButtonAction()
            }) {
                Text(text = dismissButtonText)
            }
        }
    )
}