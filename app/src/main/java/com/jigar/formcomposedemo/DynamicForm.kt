package com.jigar.formcomposedemo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun DynamicForm(fields: List<FormFieldState>, onSubmit: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        fields.forEach { field ->
            ValidatedTextField(field)
        }

        val isFormValid = fields.all { it.validator(it.value.value) }
        Button(
            onClick = onSubmit,
            enabled = isFormValid,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isFormValid) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Text("Submit")
        }
    }
}

@Composable
fun ValidatedTextField(
    formFieldState: FormFieldState,
    modifier: Modifier = Modifier
) {
    val isError = formFieldState.hasInteracted && !formFieldState.validator(formFieldState.value.value)

    OutlinedTextField(
        value = formFieldState.value.value,
        onValueChange = { formFieldState.value.value = it },
        label = { Text(formFieldState.label) },
        isError = isError,
        modifier = modifier
            .onFocusChanged { focusState ->
                if (!focusState.isFocused && !formFieldState.hasInteracted) {
                    formFieldState.hasInteracted = true
                }
            }
    )
    if (isError) {
        Text(formFieldState.errorMessage, style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.error))
    }
}
