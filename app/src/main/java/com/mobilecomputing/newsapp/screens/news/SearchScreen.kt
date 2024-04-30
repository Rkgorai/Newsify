package com.mobilecomputing.newsapp.screens.news

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchScreen() {
    val searchQuery = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxWidth()
            .padding(8.dp)
    ) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth()
                .padding(8.dp),
            value = searchQuery.value,
            onValueChange = { newValue -> searchQuery.value = newValue },
            label = { Text("Search") },
            singleLine = true
        )

        Button(
            onClick = { searchForQuery(searchQuery.value) },
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text("Submit")
        }

        // Display the results of the API call
        // This will depend on the structure of your API and how you want to display the results
        Text(text ="Results will be displayed here...: ${searchQuery.value}")
    }
}

fun searchForQuery(query: String) {
    // Make an API call with the search query
    // This will depend on the specifics of your API
    // You might use a library like Retrofit to make the API call
}