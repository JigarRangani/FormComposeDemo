package com.jigar.formcomposedemo

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MobileTopUpScreen(onBackClicked: () -> Unit) {
    Scaffold(
        topBar = { CustomTopAppBar(title = "Mobile Top Up", onBackClicked = onBackClicked) },
        content = { PaddingValues ->
            Column(
                modifier = Modifier
                    .padding(PaddingValues)
                    .fillMaxSize()
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    label = { Text("Enter phone number") },
                    trailingIcon = { Icon(Icons.Default.Face, contentDescription = "Scan") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                ContactList(contacts)
                // AlphabetScroller() - Implement this based on your specific UI/UX needs.
            }
        }
    )
}

/*@Composable
fun TopAppBar(title: String) {
    SmallTopAppBar(
        title = { Text(text = title) },
        navigationIcon = { IconButton(onClick = { *//* Handle back press *//* }) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
        }}
    )
}*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(title: String, onBackClicked: () -> Unit) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            IconButton(onClick = onBackClicked) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        }
        // Add more parameters if needed, like actions for additional icons
    )
}

@Composable
fun ContactList(contacts: List<Contact>) {
    LazyColumn {
        items(contacts.size) { index ->
            val contact = contacts[index]
            ContactItem(contact = contact)
        }
    }
}

@Composable
fun ContactItem(contact: Contact) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* Handle click */ }
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Contact Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = contact.name,
                style = MaterialTheme.typography.titleMedium,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = contact.phone,
                style = MaterialTheme.typography.bodySmall,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}


data class Contact(val name: String, val phone: String)
// Sample contacts
val contacts = listOf(
    Contact("Alex Armstrong", "324-457-4701"),
    Contact("Blex Armstrong", "324-457-4701"),
    Contact("Alex Armstrong", "324-437-4701"),
    Contact("Dlex Armstrong", "324-457-4701"),
    Contact("Flex Armstrong", "324-457-4701"),
    Contact("Nlex Armstrong", "324-457-4701"),
    // Add other contacts here
)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MobileTopUpScreen(onBackClicked = {

    })
}
