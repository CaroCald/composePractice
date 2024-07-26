package com.example.practicecompose.commons.components.input

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun PrimaryInput(
    text: String,
    onTextChange: (String) -> Unit,
    title: String,
    isPassword : Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
) {
    var showPassword by remember { mutableStateOf(value = false) }
    TextField(
        value = text,
        onValueChange = { onTextChange(it) },
        label = { Text(title) },
        visualTransformation = if (showPassword || !isPassword) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIcon = {
            if(isPassword){
                if (showPassword) {
                    IconButton(onClick = { showPassword = false }) {
                        Icon(
                            imageVector = Icons.Filled.Visibility,
                            contentDescription = "hide_password"
                        )
                    }
                } else {
                    IconButton(
                        onClick = { showPassword = true }) {
                        Icon(
                            imageVector = Icons.Filled.VisibilityOff,
                            contentDescription = "hide_password"
                        )
                    }
                }
            }
          },
        keyboardOptions = if (isPassword)  KeyboardOptions(keyboardType = KeyboardType.Password) else keyboardOptions
    )
}