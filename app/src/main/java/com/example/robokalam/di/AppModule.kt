package com.example.robokalam.di

import android.content.Context
import androidx.room.Room
import com.example.robokalam.SharedPref
import com.example.robokalam.data.local.AppDatabase
import com.example.robokalam.data.local.PortfolioDao
import com.example.robokalam.data.remote.ApiService
import com.example.robokalam.repository.MainRepository
import com.google.gson.GsonBuilder
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
class AppModule {

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl("https://zenquotes.io/api/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideSharedPref(@ApplicationContext context: Context): SharedPref {
        return SharedPref(context)
    }

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService, portfolioDao: PortfolioDao) : MainRepository {
        return MainRepository(apiService, portfolioDao)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "robokalam_db"
        ).build()
    }

    @Provides
    @Singleton
    fun providePortfolioDao(db: AppDatabase): PortfolioDao {
        return db.portfolioDao()
    }

}