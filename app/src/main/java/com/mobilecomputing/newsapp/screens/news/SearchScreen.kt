package com.mobilecomputing.newsapp.screens.news

import android.content.Intent
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mobilecomputing.newsapp.SecondMainActivity
import com.mobilecomputing.newsapp.component.ArticleItem
import com.mobilecomputing.newsapp.model.news.Article

//@Composable
//fun SearchScreen(viewModel: NewsViewModel = hiltViewModel()) {
//    val searchQuery = remember { mutableStateOf("") }
//
//    val context = LocalContext.current // Get the current context
//    val focusManager = LocalFocusManager.current // Get the current focus manager
//
//    val startActivityLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
//        // Handle the result of the activity here
//    }
//    viewModel.searchedNewsArticles.value = mutableListOf() // Clear the list of news articles
//
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(8.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        OutlinedTextField(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp),
//            value = searchQuery.value,
//            onValueChange = { newValue -> searchQuery.value = newValue },
//            label = { Text("Search") },
//            singleLine = true
//        )
//
//        Button(
//            onClick = {
//                // Call the API to search for news
//                viewModel.getNewsData(isTopHeadline = false, q = searchQuery.value, country = "in", category = "general", sortBy = "publishedAt")
//                focusManager.clearFocus() // Clear the focus
//            },
//            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
//        ) {
//            Text("Submit")
//        }
//
//        LazyColumn {
//            items(getArticlesByNewsType(viewModel, "search")) { article ->
//                Box(modifier = Modifier.clickable {
//                    // Create an Intent to start the new activity
//                    val intent = Intent(context, SecondMainActivity::class.java)
//
//                    intent.putExtra("title", article.title)
//                    intent.putExtra("author", article.author)
//                    intent.putExtra("publishedAt", article.publishedAt)
//                    intent.putExtra("source", article.source.name)
//                    intent.putExtra("url", article.url)
//                    intent.putExtra("urlToImage", article.urlToImage)
//                    intent.putExtra("content", article.content)
//                    intent.putExtra("description", article.description)
//                    // Use the launcher to start the activity
//                    startActivityLauncher.launch(intent)
//                }){
//                    ArticleItem(article)
//                }
//            }
//        }
//    }
//}

@Composable
fun SearchScreen(viewModel: NewsViewModel = hiltViewModel()) {
    val searchQuery = remember { mutableStateOf("") }
    val showDialog = remember { mutableStateOf(false) } // State variable to control dialog visibility

    val context = LocalContext.current
    val focusManager = LocalFocusManager.current

    val startActivityLauncher = rememberLauncherForActivityResult(contract = ActivityResultContracts.StartActivityForResult()) { result ->
        // Handle the result of the activity here
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            value = searchQuery.value,
            onValueChange = { newValue -> searchQuery.value = newValue },
            label = { Text("Search") },
            singleLine = true
        )

        Button(
            onClick = {
                viewModel.getNewsData(isTopHeadline = false, q = searchQuery.value, country = "in", category = "general", sortBy = "publishedAt")
                focusManager.clearFocus()

                // If no articles are found, show the dialog
                if (getArticlesByNewsType(viewModel, "search").isEmpty()) {
                    showDialog.value = true
                }
            },
            modifier = Modifier.padding(top = 8.dp, bottom = 16.dp)
        ) {
            Text("Submit")
        }

        // Show the dialog when showDialog is true
        if (showDialog.value) {
            AlertDialog(
                onDismissRequest = { showDialog.value = false },
                title = { Text(text = "No Results Found") },
                text = { Text(text = "No articles were found for your search.") },
                confirmButton = {
                    Button(onClick = { showDialog.value = false }) {
                        Text("OK")
                    }
                }
            )
        }

        LazyColumn {
            items(getArticlesByNewsType(viewModel, "search")) { article ->
                Box(modifier = Modifier.clickable {
                    val intent = Intent(context, SecondMainActivity::class.java)

                    intent.putExtra("title", article.title)
                    intent.putExtra("author", article.author)
                    intent.putExtra("publishedAt", article.publishedAt)
                    intent.putExtra("source", article.source.name)
                    intent.putExtra("url", article.url)
                    intent.putExtra("urlToImage", article.urlToImage)
                    intent.putExtra("content", article.content)
                    intent.putExtra("description", article.description)

                    startActivityLauncher.launch(intent)
                }){
                    ArticleItem(article)
                }
            }
        }
    }
}