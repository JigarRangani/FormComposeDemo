package com.jigar.formcomposedemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jigar.formcomposedemo.ui.theme.FormComposeDemoTheme
import java.util.regex.Pattern

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FormComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DemoScreen()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FormComposeDemoTheme {
        Greeting("Android")
    }
}
@Composable
fun DemoScreen() {
    val formFields = remember {
        listOf(
            FormFieldState(
                label = "Name",
                value = mutableStateOf(""),
                validator = { it.length >= 4 },
                errorMessage = "Name must be at least 4 characters"
            ),
            FormFieldState(
                label = "Email",
                value = mutableStateOf(""),
                validator = { Pattern.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}", it) },
                errorMessage = "Invalid email format"
            ),
            FormFieldState(
                label = "Mobile Number",
                value = mutableStateOf(""),
                validator = { Pattern.matches("[6-9]\\d{9}", it) },
                errorMessage = "Invalid mobile number"
            ),
            FormFieldState(
                label = "Age",
                value = mutableStateOf(""),
                validator = { it.toIntOrNull()?.let { age -> age in 18..60 } ?: false },
                errorMessage = "Age must be between 18 and 60"
            ),
            FormFieldState(
                label = "Zipcode",
                value = mutableStateOf(""),
                validator = { Pattern.matches("^[1-9][0-9]{5}$", it) },
                errorMessage = "Invalid zipcode"
            ),
            FormFieldState(
                label = "Password",
                value = mutableStateOf(""),
                validator = { Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#\$%^&+=])(?=\\S+$).{8,}$", it) },
                errorMessage = "Password must contain an uppercase, a number, and a special character"
            )

        )
    }

    DynamicForm(fields = formFields) {
        // Handle form submission
    }
}
