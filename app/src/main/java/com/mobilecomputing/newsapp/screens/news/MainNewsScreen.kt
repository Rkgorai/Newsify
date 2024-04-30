package com.mobilecomputing.newsapp.screens.news

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainNewsScreen() {
    val items = listOf(
    Pair(Icons.Filled.Home, Icons.Outlined.Home) to "Home",
    Pair(Icons.Filled.Search, Icons.Outlined.Search) to "Search",
    Pair(Icons.Filled.Favorite, Icons.Outlined.FavoriteBorder) to "Favourite",
    Pair(Icons.Filled.Person, Icons.Outlined.Person) to "About Us"
)

val selectedItem = remember { mutableStateOf(items[0].second) }

    Scaffold(
        topBar = {
            // Replace with your own top bar
            TopAppBar(title = {
                Text("News App")
            })
        },
        bottomBar = {
            BottomAppBar(
                containerColor = MaterialTheme.colorScheme.background,
                contentColor = MaterialTheme.colorScheme.primary,
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    items.forEach { (iconPair, item) ->
                        IconButton(
                            onClick = { selectedItem.value = item }
                        ) {
                            val icon =
                                if (selectedItem.value == item) iconPair.first else iconPair.second
                            Icon(icon, contentDescription = null, modifier = Modifier.size(32.dp))
//                        Text(item)
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when (selectedItem.value) {
                "Home" -> NewsHome()
                "Search" -> SearchScreen()
                "Favourite" -> FavScreen()
                "About Us" -> AboutScreen()
            }
        }
    }
}