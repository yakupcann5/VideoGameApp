package com.yakupcan.videogameapp.di

import com.yakupcan.videogameapp.common.Constants
import com.yakupcan.videogameapp.data.RepositoryImpl
import com.yakupcan.videogameapp.data.Service
import com.yakupcan.videogameapp.domain.repository.GameRepository
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
    fun provideServicesApi(): Service {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Service::class.java)
    }

    @Singleton
    @Provides
    fun providerGameRepository(service: Service): GameRepository {
        return RepositoryImpl(service)
    }
}