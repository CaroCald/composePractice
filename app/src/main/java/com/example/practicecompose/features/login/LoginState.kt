package com.example.practicecompose.features.login

import com.example.practicecompose.domain.entities.generics.forms.UiText

data class LoginState(
    var email: String = "",
    val emailError: UiText? = null,
    var password: String = "",
    val passwordError: UiText? = null,
    var isValid : Boolean = false
)
