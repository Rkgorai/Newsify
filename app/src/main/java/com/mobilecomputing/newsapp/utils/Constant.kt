package com.mobilecomputing.newsapp.utils

import androidx.compose.runtime.mutableStateOf

object Constant {
    // https://api.openweathermap.org/geo/1.0/reverse?lat=28.5439841&lon=77.2724624&limit=5
//      &appid=0d05b7f83c770898b89f033ae07be9fd
    const val Location_BASE_URL = "https://api.openweathermap.org/geo/1.0/"
    const val LOCATION_API_KEY = "0d05b7f83c770898b89f033ae07be9fd"


    //https://newsapi.org/v2/top-headlines?country=us&apiKey=943ce6b7bb7442d2856a0a335043f422

    //https://newsapi.org/v2/everything?q=Apple&from=2024-04-10&sortBy=popularity&apiKey=943ce6b7bb7442d2856a0a335043f422

    //https://newsapi.org/v2/everything?q=bitcoin&apiKey=943ce6b7bb7442d2856a0a335043f422

    //https://newsapi.org/v2/everything?q=new%20delhi&apiKey=943ce6b7bb7442d2856a0a335043f422

    //https://newsapi.org/v2/top-headlines?country=in&category=sports&apiKey=943ce6b7bb7442d2856a0a335043f422

    const val NEWS_BASE_URL = "https://newsapi.org/v2/"
    const val NEWS_API_KEY = "943ce6b7bb7442d2856a0a335043f422"
//const val NEWS_API_KEY= "2b859da6065945fa993fc49d86e68580"


    val isTopHeadline = mutableStateOf(false)

    val state_location = mutableStateOf("")
}