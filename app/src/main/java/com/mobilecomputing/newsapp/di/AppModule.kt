package com.mobilecomputing.newsapp.di

import com.mobilecomputing.newsapp.network.LocationAPI
import com.mobilecomputing.newsapp.repository.LocationRepository
import com.mobilecomputing.newsapp.utils.Constants.Location_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideLocationRepository(api: LocationAPI): LocationRepository = LocationRepository(api)

    @Singleton
    @Provides
    fun provideLocationAPI(): LocationAPI {
        return Retrofit.Builder()
            .baseUrl(Location_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(LocationAPI::class.java)
    }



}