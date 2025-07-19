package com.example.practicecompose.domain.commons.components.buttons

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.practicecompose.domain.commons.components.text.TextCustom
import androidx.compose.foundation.shape.RoundedCornerShape


@Composable
fun PrimaryButton(
    text: String,
    onClick: () -> Unit,
    isEnable : Boolean = true,
    modifier: Modifier = Modifier
        .width(300.dp)
        .height(48.dp)
) {

    val focusManager = LocalFocusManager.current

    Button(
        enabled= isEnable,
        modifier = modifier,
        onClick = {
            focusManager.clearFocus()
            onClick()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        ),
        shape = RoundedCornerShape(12.dp),
    ) {
        TextCustom(
            text =text,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onPrimary,
            textAlign = TextAlign.Center
        )
    }
}

