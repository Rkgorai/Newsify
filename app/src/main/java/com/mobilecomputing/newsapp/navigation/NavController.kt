package com.mobilecomputing.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mobilecomputing.newsapp.MainActivity
import com.mobilecomputing.newsapp.MyAppFirst
import com.mobilecomputing.newsapp.screens.news.DetailScreen

@Composable
fun MyApp(mainActivity: MainActivity) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") { MyAppFirst(mainActivity,navController = navController) }
        composable("detail/{articleId}") { backStackEntry ->
            val articleId = backStackEntry.arguments?.getString("articleId")
            DetailScreen(navController = navController, articleId = articleId)
        }
    }
}