package com.mobilecomputing.newsapp.screens.locations

import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.model.LatLng
import com.mobilecomputing.newsapp.locationupdates.LocationUpdates

@Composable
fun LocationScreen(context: Context, currentLocation: LatLng,  locationUpdates: LocationUpdates) {
    val launchMultiplePermissions = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionMaps ->
        val areGranted = permissionMaps.values.reduce { acc, next -> acc && next }

        if (areGranted) {
            locationUpdates.locationRequired = true
            locationUpdates.startLocationUpdates()
            Toast.makeText(context, "Permissions Granted", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, "Permissions Denied", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(key1 = true) {
        if (locationUpdates.permissions.all {
                ContextCompat.checkSelfPermission(context, it) == PackageManager.PERMISSION_GRANTED
            }) {
            // Get location info
            locationUpdates.startLocationUpdates()
        } else {
            launchMultiplePermissions.launch(locationUpdates.permissions)
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //Text(text = "Your Location Info: ${currentLocation.latitude}, ${currentLocation.longitude}")
        }
    }
}