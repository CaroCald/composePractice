package com.example.practicecompose.domain.commons.components.alerts

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.example.practicecompose.R
import com.example.practicecompose.domain.commons.components.text.TextCustom


@Composable
fun CustomAlertDialog(
    shouldShowDialog: MutableState<Boolean>,
    title: @Composable () -> Unit = {
        TextCustom(
            text = stringResource(id = R.string.warning_title),
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    },
    message: String,
    confirmButton: @Composable () -> Unit = {
        Button(onClick = { shouldShowDialog.value = false }) {
            TextCustom(
                text = stringResource(id = R.string.confirm),
                color = Color.White,
                textAlign = TextAlign.Center
            )
        }
    },
    dismissButton: @Composable (() -> Unit)? = null
) {
    if (shouldShowDialog.value) {
        AlertDialog(
            onDismissRequest = { shouldShowDialog.value = false },
            title = title,
            text = {
                TextCustom(
                    text = message,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            },
            confirmButton = confirmButton,
            dismissButton = dismissButton
        )
    }
}
