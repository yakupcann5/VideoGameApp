package com.yakupcan.videogameapp.di

import com.yakupcan.videogameapp.common.Constants
import com.yakupcan.videogameapp.data.Service
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideServicesApi(): Service {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(Service::class.java)
    }
}