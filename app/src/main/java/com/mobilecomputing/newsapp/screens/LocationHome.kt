package com.mobilecomputing.newsapp.screens

import android.util.Log
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun LocationHome(lat: String, lon: String, viewModel: LocationViewModel = hiltViewModel()) {
    DisplayLocationData(viewModel)
    viewModel.getLocationData(lat, lon)
}

@Composable
fun DisplayLocationData(viewModel: LocationViewModel) {
    // Display the location data

    val resultsfetched = viewModel.data.value.data?.toMutableList()
    if(viewModel.data.value.loading == true) {
        CircularProgressIndicator()
        Log.d("LOADING", "DisplayLocationData: Loading")
    }
    else {
        Log.d("RESULT", "DisplayLocationData: $resultsfetched")
        Text(text = "Location Data: ${resultsfetched?.get(0)?.name}, ${resultsfetched?.get(0)?.state},  ${resultsfetched?.get(0)?.country}")
    }

}
