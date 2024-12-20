package com.mobilecomputing.newsapp.network

import com.mobilecomputing.newsapp.model.locations.LocationData
import com.mobilecomputing.newsapp.utils.Constant.LOCATION_API_KEY
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface LocationAPI {
    @GET("reverse?limit=5&appid=${LOCATION_API_KEY}")
    suspend fun getLocationData(
        @Query("lat") lat: String,
        @Query("lon") lon: String
    ): LocationData
}