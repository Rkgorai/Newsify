package com.mobilecomputing.newsapp.repository

import android.util.Log
import com.mobilecomputing.newsapp.data.DataOrException
import com.mobilecomputing.newsapp.model.locations.LocationDataItem
import com.mobilecomputing.newsapp.network.LocationAPI
import javax.inject.Inject

class LocationRepository @Inject constructor(private val api: LocationAPI) {
    private val dataOrException = DataOrException<ArrayList<LocationDataItem>, Boolean, Exception>()

    suspend fun getLocationData(lat: String, lon: String): DataOrException<ArrayList<LocationDataItem>, Boolean, Exception> {
        try {
            dataOrException.loading = true
            dataOrException.data = api.getLocationData(lat, lon)
            if (dataOrException.data.toString().isNotEmpty()) {
                dataOrException.loading = false
            }
        }
        catch (e: Exception) {
            dataOrException.e = e
            Log.d("EXCEPTION", "getLocationData: ${e.message}")
        }
        return dataOrException
    }


}