package com.mobilecomputing.newsapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.model.LatLng
import com.mobilecomputing.newsapp.locationupdates.LocationUpdates
import com.mobilecomputing.newsapp.screens.locations.LocationHome
//import com.mobilecomputing.newsapp.screens.LocationHome
import com.mobilecomputing.newsapp.screens.locations.LocationScreen
import com.mobilecomputing.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var locationUpdates: LocationUpdates

    override fun onResume() {
        super.onResume()
        if (locationUpdates.locationRequired) {
            // Start location updates
            locationUpdates.startLocationUpdates()
        }
    }

    override fun onPause() {
        super.onPause()
        locationUpdates.stopLocationUpdates()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        locationUpdates = LocationUpdates(this)

        setContent {
            var currentLocation by remember {
                mutableStateOf(LatLng(0.0, 0.0))
            }

            locationUpdates.locationCallback = object : LocationCallback() {
                override fun onLocationResult(p0: LocationResult) {
                    super.onLocationResult(p0)
                    for (location in p0.locations) {
                        currentLocation = LatLng(location.latitude, location.longitude)
                    }
                }
            }

            NewsAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LocationScreen(this@MainActivity ,currentLocation, locationUpdates)
                    Log.d("CompletedCoordinates", "coordinates: $currentLocation")
                    if(currentLocation.latitude != 0.0 && currentLocation.longitude != 0.0) {
                        LocationHome(currentLocation.latitude.toString(), currentLocation.longitude.toString())
                    }
                }
            }
        }
    }
}