package com.jigar.formcomposedemo

import androidx.compose.runtime.MutableState

data class FormFieldState(
    val label: String,
    var value: MutableState<String>,
    val validator: (String) -> Boolean,
    val errorMessage: String,
    var hasInteracted: Boolean = false
)