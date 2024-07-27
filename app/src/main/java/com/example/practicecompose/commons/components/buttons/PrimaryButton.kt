package com.example.practicecompose.commons.components.buttons

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.practicecompose.commons.components.text.TextCustom


@Composable
fun PrimaryButton(
     text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier.width(300.dp)
        .height(54.dp)
) {
    Button(
        modifier = modifier,
        onClick = onClick
    ) {
        TextCustom(
            text =text,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )
    }
}

