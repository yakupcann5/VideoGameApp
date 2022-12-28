package com.yakupcan.videogameapp.di

import com.yakupcan.videogameapp.common.Constants
import com.yakupcan.videogameapp.data.repository.AllGameRepositoryImpl
import com.yakupcan.videogameapp.data.Service
import com.yakupcan.videogameapp.data.repository.SingleGameRepositoryImpl
import com.yakupcan.videogameapp.domain.repository.AllGameRepository
import com.yakupcan.videogameapp.domain.repository.SingleGameRepository
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
    fun providerGameRepository(service: Service): AllGameRepository {
        return AllGameRepositoryImpl(service)
    }

    @Singleton
    @Provides
    fun provideSingleGameRepository(service: Service): SingleGameRepository {
        return SingleGameRepositoryImpl(service)
    }
}