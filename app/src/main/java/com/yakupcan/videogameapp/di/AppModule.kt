package com.yakupcan.videogameapp.di

import android.content.Context
import androidx.room.Room
import com.yakupcan.videogameapp.common.Constants
import com.yakupcan.videogameapp.data.repository.AllGameRepositoryImpl
import com.yakupcan.videogameapp.data.Service
import com.yakupcan.videogameapp.data.repository.SingleGameRepositoryImpl
import com.yakupcan.videogameapp.db.Database
import com.yakupcan.videogameapp.db.dao.FavGameDAO
import com.yakupcan.videogameapp.db.dao.GameDAO
import com.yakupcan.videogameapp.domain.repository.AllGameRepository
import com.yakupcan.videogameapp.domain.repository.SingleGameRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(context, Database::class.java, Constants.DATABASE_NAME).build()
    }

    @Singleton
    @Provides
    fun provideRocketDao(database: Database): GameDAO {
        return database.getGameDao()
    }

    @Singleton
    @Provides
    fun provideFavoriteDao(database: Database): FavGameDAO {
        return database.getFavGameDao()
    }
}