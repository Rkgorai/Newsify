package com.mobilecomputing.newsapp.screens.locations

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mobilecomputing.newsapp.data.DataOrException
import com.mobilecomputing.newsapp.model.locations.LocationDataItem
import com.mobilecomputing.newsapp.repository.LocationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(private val repository: LocationRepository) : ViewModel() {
    val data : MutableState<DataOrException<ArrayList<LocationDataItem>, Boolean, Exception>> =
        mutableStateOf(DataOrException(null, true, Exception("")))

//    init {
//        getLocationData("28.5473", "77.2734")
//    }

    fun getLocationData(lat: String, lon: String) {
        // Call the repository to get the data
        viewModelScope.launch {
            data.value.loading = true
            data.value = repository.getLocationData(lat, lon)
            if (data.value.data.toString().isNotEmpty()) {
                data.value.loading = false
            }
        }
    }
}