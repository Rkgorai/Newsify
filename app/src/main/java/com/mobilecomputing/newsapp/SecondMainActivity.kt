package com.mobilecomputing.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mobilecomputing.newsapp.screens.news.SecondMainActivityScreen

class SecondMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent // Get the intent
        setContent {
            SecondMainActivityScreen(intent) // Pass the intent to the composable function
        }
    }
}


